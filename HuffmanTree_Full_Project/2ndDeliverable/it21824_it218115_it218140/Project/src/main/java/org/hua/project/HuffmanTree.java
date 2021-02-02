package org.hua.project;

import java.io.Serializable;
import java.util.ArrayList;

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

}
