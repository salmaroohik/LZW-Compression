
//Salma Roohi Khayum 801168027


package com.Algorithms;

import com.Compression.LZW;
import java.util.*;
public class Main {
    public static final int OPERATIONINDEX = 0;// Argument to set the operation code
    public static final int FILENAMEINDEX = 1;// Argument to get the input file respective to the operation code
    public static final int BITLENGTHINDEX = 2;// argument to pass value for the bitlength
    public static final String ENCODER = "Encoder";// Operation code for Encoding
    public static final String DECODER = "Decoder";// Operation code for Decoding

    public static void main(String[] args) {
        if(args[OPERATIONINDEX].equals(ENCODER)){
            LZW.Encode(args[FILENAMEINDEX], Integer.parseInt(args[BITLENGTHINDEX]));//Call for encoding program
        }else if(args[OPERATIONINDEX].equals(DECODER)) {
            LZW.Decode(args[FILENAMEINDEX], Integer.parseInt(args[BITLENGTHINDEX]));//Call for Decoding program
        }else{
            System.out.println("Wrong Operation Code");
        }
    }
}
