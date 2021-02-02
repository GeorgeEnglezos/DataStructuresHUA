package org.hua.project;

import java.io.*;
import java.util.*;


public class Second {
    static private String currentLine;
    private static ArrayList<HuffmanNode> hNodes = new ArrayList<>();

    public static void secondDeliverable() throws IOException{

        makeArrayList();
        if (hNodes.isEmpty()){
            System.out.println("There were no frequencies to make the tree");
        }else{
            HuffmanNode.sortArrayList(hNodes);
            HuffmanNode finalNode = HuffmanTree.buildTree(hNodes); // I get the final tree

            makeTreeDat(finalNode); //Makes the tree.dat file
        }


    }

    private static BufferedReader readFrequenciesDat()// Read the whole frequencies.dat file
    {
        try {
            BufferedReader inputStream = new BufferedReader(new FileReader("frequencies.dat"));
            return inputStream; //returns the whole txt file as a bufferedReader
        } catch (FileNotFoundException ex) { //If the file isn't in the project folder
            System.out.println("frequencies.dat not found.");
            return null;
        }
    }

    private static void makeArrayList() throws IOException {

        String temp2;
        int asciiNumber = 0;
        BufferedReader file = readFrequenciesDat();


        while ((currentLine = file.readLine()) != null) {

            StringTokenizer st = new StringTokenizer(currentLine);
            while (st.hasMoreTokens()) { //To break all the frequencies
                String temp = st.nextToken(",");

                int index = 0;

                StringTokenizer st2 = new StringTokenizer(temp);
                while (st2.hasMoreTokens()) {
                    temp2 = st2.nextToken(":"); //To separate the frequency from the ascii number

                    if (index == 0) {
                        asciiNumber = Integer.parseInt(temp2);
                    }
                    if (index == 1) //skip this to get only the frequencies (frequencies.dat format was asciiNum:Frequency)
                    {
                        if (Integer.parseInt(temp2) != 0) {
                            hNodes.add(new HuffmanNode(asciiNumber, Integer.parseInt(temp2), null, null));
                        }
                    }
                    index++;
                }
            }

        }
    }

    private static void printFrequencies() {
        for (HuffmanNode h : hNodes) {
            System.out.println((char) h.getAscii() + ":" + h.getFreq());
        }

    }



    private static void makeTreeDat(HuffmanNode tree) throws IOException {
        FileOutputStream fos = new FileOutputStream("tree.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(tree);
        oos.close();
        fos.close();
        System.out.println("tree.dat is ready in the Project Folder!");
    }



}