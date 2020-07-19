# LZW-Compression

Programming Language and Compiler Version:
Java 13.0.2
Operating System: Windows
Breakdown of files: ascii-codes.txt, FileUtil.java, LZW.java, Main.java, input1.txt, input1.lzw, input1_decoded.txt
Input files for the encoder: input1.txt
Output file of the encoder: input1.lzw
Input file for the decoder: input1.lzw
Output file of the Decoder: input1_decoded.txt
Source files: ascii-codes.txt, FileUtil.java, LZW.java, Main.java

Data Structure:
I have used Array list as the data structure to implement this project.

Program Compilation and Execution:
1.	Type cmd in the home search or open command window.
2.	Change the directory cd “path where the project is stored/skhayum” here skhayum is the folder containing all of the project contents.
3.	To Compile, javac com\Algorithms\Main.java
4.	To run the Encoder, execute the command: java com.Algorithms.Main Encoder input1.txt 9
5.	To run the Decoder, execute the command: java com.Algorithms.Main Decoder input1.lzw 9
In the commands of execution, first argument is the operation code, 2nd argument is the input file respective to the operation code and the third argument is the Bitlength index.
Output file is generated in the same directory and rewritten on repeated execution.

6.	To infer the correctness of the program input1.txt and input1_decoded.txt should display the same content
Program design and description:
The main function calls for the Encoding and Decoding program based on the type of argument received for the operation code. Input1.txt file is encoded as input1.lzw format by using LZW algorithm in order to reduce the file size. In order to retrieve the original file, input1.lzw is decoded into input1_decoded.txt based on the dictionary values computed and compared. The same algorithm is also used in Unix compression Utility.
