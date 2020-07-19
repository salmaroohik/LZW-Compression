//Salma Roohi Khayum 801168027

package com.Util;

import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.nio.charset.Charset;

public class FileUtil {
    public static final String DICTIONARYPATH = "./Dictionary/";
    public static final String ASCIIFILENAME = "ascii-codes.txt";
    public static final String TABDELIMITER = "\t"; //Used to set the format for the dictionary separates keys from values
    public static final String NEWLINEDELIMITER = "\n";
    public static final String SCANNERDELIMITER = "\\Z";
    public static final String ZEROWIDTHNOBREAKSPACE = "\uFEFF";
    public static final String ENCODINGTYPEUTF8 = "UTF-8";
    public static final int KEY = 0;
    public static final int VALUE = 1;
// Function for reading the contents from an input file
    public static String readFile(String fileName){
        String fileContents = "";
        try{
            System.out.println(new File(".").getAbsoluteFile());// To take the input file from the current directory
            File file = new File(fileName);
            fileContents = new Scanner(file,ENCODINGTYPEUTF8).useDelimiter(SCANNERDELIMITER).next();
            fileContents = fileContents.replaceAll(ZEROWIDTHNOBREAKSPACE, "");
        }catch(IOException ex){
            System.out.println(ex);
        }
        return fileContents;
    }

    public static List<Integer> readLZW(String FileName)throws IOException{
        InputStream inputStream = new FileInputStream(FileName);
        List<Integer> res = new ArrayList<>();
        try(InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-16BE"))){
            int data = inputStreamReader.read();
            while(data!=-1){
                res.add(data);
                data = inputStreamReader.read();
            }
        }finally{
            inputStream.close();
        }
        return res;
    }
// To get the ascii dictionary values into an array. Same function is called while encoding and decoding to get the keys and values.
    public static String[] readASCIIDictionaryToIntArray(int allocSize){
        String[] asciiDictionary = new String[allocSize];
        try{
            String[] tokens = readFile(DICTIONARYPATH + ASCIIFILENAME).split(NEWLINEDELIMITER);
            for(int i=0; i<tokens.length; i++){
                if(tokens[i] != ""){
                    String[] keyPair = tokens[i].split(TABDELIMITER);
                    int key = Integer.parseInt(keyPair[KEY].trim());
                    asciiDictionary[key] = keyPair[VALUE];
                }
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
        return asciiDictionary;
    }
// Function to write the contents in the output file
    public static void writeFile(String fileName, List<Integer> fileContent, String encoding){
        try {
            File fileDir = new File(fileName);
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), "UTF-16BE"));
            for(int i:fileContent)
                writer.write(i);
            writer.flush();
            writer.close();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
