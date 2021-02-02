package org.hua.project;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

public class HuffmanTree {

    public static HuffmanNode buildTree(ArrayList<HuffmanNode> hNodes) {

        while (hNodes.size() > 1) {
            HuffmanNode.sortArrayList(hNodes);

            HuffmanNode left = hNodes.get(0);
            hNodes.remove(0);

            HuffmanNode right = hNodes.get(0);
            hNodes.remove(0);

            HuffmanNode parent = new HuffmanNode(-1, left.getFreq() + right.getFreq(), left, right);
            hNodes.add(parent);

        }
        System.out.println("The tree is ready!");
        return hNodes.get(0);
    }
    public static ArrayList<code> getTheCodes(HuffmanNode tree){// 0 1
        ArrayDeque<String> codeTillNow = new ArrayDeque<>(); //An ArrayQueue to save the code of each ascii character
        ArrayList<code> codeList=new ArrayList<>();
        makeCodes(codeList,tree,codeTillNow,null);
        return codeList;
    }


    /** makeCodes() explanation
     * It repeats the same process again and again by calling itself (the same method),
     * passing the current queue as a parameter and the correct bit if it goes left or right.
     *
     * By calling it twice for both left and right you pass the whole tree.
     *
     * Every time we return from the method , to the method before, it has to remove the first bit.
     *
     * The method basically ends once it reaches a leaf which is checked by the HuffmanNode.isLeaf() method.
     * Once it reaches a leaf i save the code from the queue in a String with the use of an iterator.
     *
     * The codes are saved in an ArrayList of Objects of the class code which is at the end of this file
     * and has just the two variables c(char) and the Code(String)
     */
    public static void makeCodes(ArrayList<code> codeList,HuffmanNode node,ArrayDeque<String> codeTillNow,String bit) {
        if(bit!=null){
            codeTillNow.push(bit);
        }
        if (!node.isLeaf()){
            makeCodes(codeList,node.getLeft(),codeTillNow,"0"); //Could have simply used a StringBuffer or StringBuilder instead of ArrayQueue and just concat a 0 or 1
            codeTillNow.removeFirst();
            makeCodes(codeList,node.getRight(),codeTillNow,"1");
            codeTillNow.removeFirst();
        }else{
            String tmp="";

            for(Iterator itr=codeTillNow.descendingIterator(); itr.hasNext();)
            {
                tmp=tmp+itr.next().toString();
            }
            codeList.add(new code((char)node.getAscii(),tmp));
        }
    }

    static class code { //For saving the final codes
        char c;
        String code;

        public code(char c, String code) {
            this.c = c;
            this.code = code;
        }

        public char getC() {
            return c;
        }

        public String getCode() {
            return code;
        }

        public void setC(char c) {
            this.c = c;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

}
