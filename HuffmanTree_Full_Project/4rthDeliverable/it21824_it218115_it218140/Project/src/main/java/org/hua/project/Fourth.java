package org.hua.project;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Fourth {

    static private String currentLine;
    private static ArrayList<code> codeList = new ArrayList<>();
    static StringBuilder encodedText = new StringBuilder();
    static ArrayList<Character> notEncodedList = new ArrayList<>();


    public static void fourthDeliverable(String inputFile, String outputFile) throws IOException {
        //Extract the codes
        loadCodeList();

        //Read and encode the input file
        encodedText = encodeInputFile(inputFile);

        //make a byte array
        //determine the size of the array
        int size = encodedText.length() / 8;
        if (encodedText.length() % 8 != 0) {
            size = encodedText.length() / 8 + 1;
        }

        //Fill the array
        byte[] byteArr = new byte[size];
        getBytes(byteArr, encodedText.toString());

        //make the encoded file
        makeEncodedFile(outputFile, byteArr);

        //Those characters weren't encoded
        if (notEncodedList.size() > 0) {
            String tmp = "Those characters couldn't be encoded:";
            for (Character c : notEncodedList) {
                tmp += "Char: " + c + " ";
            }
            System.out.println(tmp);
        }
    }

    /**
     * Read the code.dat file and make an Arraylist<code>
     */
    private static void loadCodeList() throws IOException {
        BufferedReader inputCodesStream = readFile("codes.dat");
        if (inputCodesStream == null) {
            System.out.println("Didn't find codes.dat, exiting now!");
            System.exit(0);
            return;
        }
        while ((currentLine = inputCodesStream.readLine()) != null) {
            String tmp;
            String c = null;
            StringTokenizer st2 = new StringTokenizer(currentLine);
            int index = 0;
            while (st2.hasMoreTokens()) {
                tmp = st2.nextToken(":");
                if (index == 0) {
                    index++;
                    c = tmp;
                } else {
                    codeList.add(new code(Integer.parseInt(c), tmp));
                }
            }
        }
    }

    /**
     * Encode all the chars from every line of the text you got from the readFile method
     */
    private static StringBuilder encodeInputFile(String inputFile) throws IOException {
        BufferedReader inputFileStream = readFile(inputFile);
        if (inputFileStream == null) { //If
            System.out.println("inputFile doesn't exist");
            System.exit(0);
        }
        while ((currentLine = inputFileStream.readLine()) != null) {
            char[] arr;
            arr = currentLine.toCharArray();//To get the characters from the Line
            for (int i = 0; i < arr.length; i++) {
                String code = getCode(codeList, arr[i]); //get the code of the character
                encodedText.append(code);
            }
        }
        encodedText.append(codeList.get(codeList.size() - 1).getCode()); //EOF
        return encodedText;
    }

    /**
     * Read the file with a buffered reader
     */
    private static BufferedReader readFile(String fileName) {
        try {
            BufferedReader inputStream = new BufferedReader(new FileReader(fileName));
            return inputStream; //returns the whole file as a bufferedReader
        } catch (FileNotFoundException ex) { //If one or more files aren't in the project folder
            System.out.println("file " + fileName + " was not found.");
            return null;
        }
    }

    /**
     * Search the list of codes, by comparing chars
     */
    private static String getCode(ArrayList<code> codeList, char c) {
        String code = "";
        boolean found = false;
        for (code current : codeList) {
            if ((int)current.getC() ==(int) c) {
                code = current.getCode();
                found = true;
                break;
            }
        }
        if (!found) {
            boolean inList = false;
            for (Character ch : notEncodedList) {
                if (ch == c) {
                    inList = true;
                }
            }
            if (!inList) {
                notEncodedList.add(c);
            }
        }
        return code;
    }

    /**
     * while the String s has more than 7 bits
     * i break the String to part i need now (the 8 bits) and the rest String!
     * Then i break the String to an array and with bitwise ops i get the byte
     * (Decimal from signed 2's complement).
     * If after the while loop there are bits left in the s String i have to
     * add zeros untill it reaches 8 bits and then i save it in the array.
     */
    private static void getBytes(byte[] byteArr, String s) {

        int counter = 0;
        while (s.length() > 7) {
            String currentByte = s.substring(0, 8);
            s = s.substring(8);
            byte mybyte = 0;
            char[] myInput = currentByte.toCharArray();

            for (int j = 0; j < myInput.length; j++) {
                if (myInput[j] == '1') {
                    mybyte |= 1 << 7 - j;//"<<" shifts a bit pattern to the left, | operator performs a bitwise inclusive OR
                }
            }
            byteArr[counter++] = mybyte;
        }

        if (s.length() > 0) {
            byte myByte = 0;
            for (int i = s.length() - 1; i < 8; i++) {
                s += 0;
            }
            char[] myInput = s.toCharArray();
            for (int j = 0; j < myInput.length; j++) {
                if (myInput[j] == '1') {
                    myByte |= 1 << 7 - j;
                }
            }
            byteArr[counter++] = myByte;
        }
    }

    /**
     * Write the bytes in the file
     */
    private static void makeEncodedFile(String outputName, byte[] byteList) throws IOException {

        OutputStream outputStream = new FileOutputStream(outputName);
        outputStream.write(byteList);
        outputStream.close();
        System.out.println(outputName+" is ready in the project folder!");
    }

}