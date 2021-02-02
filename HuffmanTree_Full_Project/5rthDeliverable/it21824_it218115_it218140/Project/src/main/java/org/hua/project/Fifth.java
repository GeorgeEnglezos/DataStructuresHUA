package org.hua.project;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Fifth {
    private static byte[] byteArr;
    private static char[] bits;

    public static void main(String[] args) throws IOException {
        if (args.length == 2) { //If i get the two file names we proceed to the next step
            try {
                //Read the bytes from the file
                readEncodedFile(args[0]);

                //Convert all the bytes to a String of 0 and 1
                bytesToBits();

                //Read the tree from tree.dat
                HuffmanNode hNode = readTreeDat();

                //Decode the bits using the Huffman Tree
                System.out.println("Decoding now...");
                String decodedText = decode(hNode);

                //Make the new output file
                makeDecodedFile(decodedText, args[1]);

                System.out.println("File " + args[1] + " is Ready");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Expected two words(file for decoding and name for decoded file)");
        }
    }

    //Read the file and get the bytes in the array
    private static void readEncodedFile(String encodedFile) {

        try {
            InputStream inputStream = new FileInputStream(encodedFile);
            byteArr = inputStream.readAllBytes();
            inputStream.close();
            System.out.println("Finished reading " + encodedFile + "!");
        } catch (IOException e) {
            System.out.println("File "+encodedFile +" not found, exiting now!");
            System.exit(0);
        }
    }

    /**
     * Convert the bytes in beats
     * http://www.java2s.com/Tutorials/Java/Data_Type/Array_Convert/Convert_byte_array_to_bit_string_in_Java.htm
     *
     * @return
     */
    private static void bytesToBits() {
        bits = new char[8 * byteArr.length];
        for (int i = 0; i < byteArr.length; i++) {
            final byte byteval = byteArr[i];
            int bytei = i << 3;
            int mask = 0x1;
            for (int j = 7; j >= 0; j--) {
                final int bitval = byteval & mask;
                if (bitval == 0) {
                    bits[bytei + j] = '0';
                } else {
                    bits[bytei + j] = '1';
                }
                mask <<= 1;
            }
        }
    }

    /**
     * Read the tree.dat file from the second deliverable
     */
    private static HuffmanNode readTreeDat() { //Reads the tree.dat file from the root folder
        try { // Java Object Serialization
            FileInputStream fis = new FileInputStream("tree.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);

            HuffmanNode tree = (HuffmanNode) ois.readObject();

            ois.close(); //Closing the Streams
            fis.close();
            return tree;
        } catch (FileNotFoundException e) { // If the file is not found print this
            System.out.println("File tree.dat not found, exiting now!");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static String decode(HuffmanNode root) throws IOException {
        HuffmanNode currentNode = root;
        String decoded = "";
        for (int i = 0; i < bits.length; i++) {
            if (!currentNode.isLeaf()) {
                if (bits[i] == '0') {
                    currentNode = currentNode.getLeft();
                } else if (bits[i] == '1') {
                    currentNode = currentNode.getRight();
                } else {
                    System.out.println("error");
                }
            }
            if (currentNode.isLeaf()) {
                if (currentNode.getAscii() == -1) {
                    System.out.println("Finished Decoding!");
                    break;
                } else {

                    decoded += (char) currentNode.getAscii();
                    currentNode = root;
                }
            }
        }
        return decoded;
    }

    /**
     * Make and write in the file
     */

    private static void makeDecodedFile(String file, String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(fileName));
        writer.print(file);
        writer.close();
    }

}
