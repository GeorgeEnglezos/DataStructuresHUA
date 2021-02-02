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
        for (int i=0;i<freq.length;i++)
        {
            freq[i]=0;
        }
        First.ExtractText(freq);

        //Second Deliverable
        Second.secondDeliverable();
    }

}
