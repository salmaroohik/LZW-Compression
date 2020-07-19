
//Salma Roohi Khayum 801168027

package com.Compression;

import com.Util.FileUtil; //To import Utility program
import java.util.*;
import java.nio.*;
public class LZW {

    public static final String SPACEDELIMITER = " "; // Used in formatting the contents in Dictionary
    public static final String DOTDELIMITER = "\\."; // Used in filename format
    public static final int BASE = 2; // Used to calculate the  bit length
    public static final String ENCODINGTYPEUTF8 = "UTF-8";
    public static final String ENCODINGTYPEUTF16 = "UTF-16BE";
    public static final String NULLCHARACTER = "\\u0000";
    public static int maxTableSize = 0;
    public static int FIRSTINDEX = 0;

    //Dictionary lookup for the characters already defined in the ascii-codes.txt
    public static int dictionaryLookup(String[] dictionary, String value){
        int key = -1;
        for(int i=0; i<dictionary.length; i++){
            if(dictionary[i] != null && dictionary[i].equals(value)){
                key = i;
                break;
            }
        }
        return key;
    }
//Encoding Program
    public static void Encode(String inputFileName, int bitLength){
        int dictionaryIndex = 256;
        String string = "";
        List<Integer> output = new ArrayList<>();
        maxTableSize = (int)Math.pow(BASE, bitLength);
        String[] asciiDictionary = FileUtil.readASCIIDictionaryToIntArray(maxTableSize); //To create an array list
        String inputData = FileUtil.readFile(inputFileName);// To read the input file input1.txt

        for (int i = 0; i < inputData.length(); i++){
            String symbol = "" + inputData.charAt(i);
            int key = dictionaryLookup(asciiDictionary, string + symbol);// Calls for the key if it is already existing in ascii-codes.txt
            if(key > -1){
                string+= symbol;
            }
            else{
                output.add(dictionaryLookup(asciiDictionary, string));
                if(dictionaryIndex < maxTableSize){
                    asciiDictionary[dictionaryIndex++] = string + symbol;// New key and value is written if the table has occupancy
                }
                string = symbol;
            }
        }
        output.add(dictionaryLookup(asciiDictionary, string));
        FileUtil.writeFile(inputFileName.split(DOTDELIMITER)[0] + ".lzw", output, ENCODINGTYPEUTF16); //Output file with encoded key values is written
    }

    public static void Decode(String inputFileName, int bitLength){
        try {
            int dictionaryLength = 256;
            String string = "", output = "", newString = "";
            maxTableSize = (int) Math.pow(BASE, bitLength);// To define the maximum number of table entries
            String[] asciiDictionary = FileUtil.readASCIIDictionaryToIntArray(maxTableSize);
            List<Integer> inputData = FileUtil.readLZW(inputFileName);
            for (int i : inputData) {
                System.out.println(i);
            }


            //String[] inputData = FileUtil.readFile(inputFileName).split(SPACEDELIMITER);
            //int code = Integer.parseInt(inputData[FIRSTINDEX].replaceAll(NULLCHARACTER, "").trim());
            int code = inputData.get(0);
            string = asciiDictionary[code];
            output += string;

            for (int i = 1; i < inputData.size(); i++) {
                //code = Integer.parseInt(inputData[i].replaceAll(NULLCHARACTER, "").trim());
                code = inputData.get(i);
                if (asciiDictionary[code] == null) {
                    newString = string + string.charAt(FIRSTINDEX);// To check if decoder has the code
                } else {
                    newString = asciiDictionary[code];
                }
                output += newString;

                if (dictionaryLength < maxTableSize) {
                    asciiDictionary[dictionaryLength++] = string + newString.charAt(FIRSTINDEX);
                }
                string = newString;
            }
            FileUtil.writeFile(inputFileName.split(DOTDELIMITER)[0] + "_decoded.txt", output, ENCODINGTYPEUTF8);//Output file with decoded text is written
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}
