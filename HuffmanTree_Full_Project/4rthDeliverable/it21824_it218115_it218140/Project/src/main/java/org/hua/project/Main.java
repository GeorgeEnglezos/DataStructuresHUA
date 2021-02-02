/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.project;

/**
 *
 * @author geggl
 *
 * Deliverable 1->Deliverable 2: removed an unnecesary if statement to get the "spaces" in the array as well
 */
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main
{
    private static int[] freq= new int[128];

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //First Deliverable
        for (int i = 0; i < freq.length; i++) {
            freq[i] = 0;
        }
        First.ExtractText(freq);

        //Second Deliverable
        Second.secondDeliverable();

        //Third Deliverable
        Third.thirdDeliverable();

        //Fourth Deliverable
        if (args.length == 2){ //If i get the two file names we proceed to the next step
            Fourth.fourthDeliverable(args[0], args[1]);
        }else{
            System.out.println("Expected two words(file for encoding and name of decoded file)");
        }

    }



}
