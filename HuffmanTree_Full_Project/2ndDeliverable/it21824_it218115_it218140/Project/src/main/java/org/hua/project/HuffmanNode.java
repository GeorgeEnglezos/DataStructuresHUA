package org.hua.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class HuffmanNode implements Serializable {
    private int freq, ch;
    private HuffmanNode left, right;

    HuffmanNode(int ch, int freq, HuffmanNode left, HuffmanNode right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }


    public static void sortArrayList(ArrayList<HuffmanNode> hNodes) {
        hNodes.sort(Comparator.comparing(o -> o.freq)); //Lambda Expression for sorting the ArrayList based on the frequency variable

    }


    public int getAscii() {
        return ch;
    }

    public int getFreq() {
        return freq;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    public void setLeftNode(HuffmanNode newNode){
        this.left=newNode;
    }

    public void setRightNode(HuffmanNode newNode){
        this.right=newNode;
    }

    public Boolean isLeaf(){
        return this.getRight()==null && this.getLeft()==null;
    }

}
