package org.hua.project;

import java.io.*;
import java.util.ArrayList;

public class Third {
    static HuffmanNode hTree;

    public static void thirdDeliverable() throws IOException {
        hTree=readTreeDat();
        ArrayList<code> CodeList=HuffmanTree.getTheCodes(hTree);
        makeCodesDatFile(CodeList);
    }


    //Για το τρίτο παραδοτέο
    private static HuffmanNode readTreeDat(){ //Reads the tree.dat file from the root folder
        try { // Java Object Serialization
            FileInputStream fis = new FileInputStream("tree.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);


            HuffmanNode tree = (HuffmanNode) ois.readObject();

            ois.close(); //Closing the Streams
            fis.close();
            return tree;
        } catch (FileNotFoundException e) { // If the file is not found print this
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    // To save the codes in a asciiNumber:CodeInBinary Format
    private static void makeCodesDatFile(ArrayList<code> arr) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("codes.dat")));
        for (int i=0;i<arr.size();i++)//To print all the characters
        {
            out.println((int)arr.get(i).getC()+":"+arr.get(i).getCode()); //to print all the codes in separate lines
        }
        out.close(); //To close the Buffered Writer
        System.out.println("codes.dat is ready in the project folder!");
    }
}
