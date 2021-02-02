/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.project;

import java.io.*;
/**
 *
 * @author geggl
 */
public class Text {

    static String[] fileNames= new String[3];
    static private String currentLine;

    public static void ExtractText(int[] freq) throws IOException {

        fileNames[0]="First_File";
        fileNames[1]="Second_File";
        fileNames[2]="Third_File";

        for (int i=0;i<3;i++) // for each file
        {
            BufferedReader inputStream= getText(fileNames[i]);
            if (inputStream==null && i<2)//if one of the files is missing skip this loop
            {
                continue; //To skip the rest of the loop
            }
            else if (inputStream==null && i==2) //if the last one is missing stop the loop
            {
                break; //To exit the for loop
            }
            else
            {

                while ((currentLine = inputStream.readLine()) != null) {
                    char[] arr;
                    if (currentLine != "") {
                        arr = extractTextFromLine(currentLine); //To get the characters from the Line
                        makeTheArray(arr, freq);
                    }
                }
            }
            inputStream.close();
        }
        //showFrequencies(freq);
        makeDatFile(freq);

    }
    private static char[] extractTextFromLine(String line) // get an character array from each line
    {
        char[] arr = new char[line.length()];
        for (int i=0;i<line.length();i++)
        {
            char temp=line.charAt(i);
            if ( temp==' ') //I don't know if that's needed or not
            {
                System.out.println((int) temp);
            }
            arr[i] = line.charAt(i);

        }
        return arr;
    }

    private static void makeTheArray(char[] arr, int[] freq) // I make the array with the frequencies here
    {
        for (int i=0;i<arr.length;i++)
        {
            int asciiOfA = (int) arr[i]; // i cast the char to int to get the ascii value
            if (asciiOfA<128) //0-127
            {
                freq[asciiOfA]++; //how many times the character appeared
            }
        }
    }
    private static void makeDatFile(int[] freq) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frequencies.dat")));
        for (int i=0;i<freq.length;i++) //to print all the values from 0-127
        {
            out.write(i+":"+freq[i]+",");
        }
        out.close();
        System.out.println("Frequencies.dat is ready in the project folder!");
    }

    private static void showFrequencies(int[] freq) // To show at the console all the values
    {
        for (int i=0;i<freq.length;i++)
        {
            System.out.println("The ascii value of Ascii: "+(char) i +" is "+i+" and showed "+freq[i]);
        }
    }

    private static BufferedReader getText(String fileName) throws FileNotFoundException// Check if all the files exist
    {
        try {
            BufferedReader inputStream = new BufferedReader(new FileReader(fileName+".txt"));
            return inputStream; //returns the whole txt file as a bufferedReader
        }
        catch (FileNotFoundException ex) { //If one or more files aren't in the project folder
            System.out.println("file " + fileName + " was not found.");
            return null;
        }
    }
}
