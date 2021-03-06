import java.util.Scanner;

public class Huffman {
    /** Get Huffman codes for the characters
    * This method is called once after a Huffman tree is built
    */
    public static String[] getCode(Tree.Node root, int x) {
        if (root == null) return null;
        String[] codes = new String[(int)Math.pow(x, 2)];
        assignCode(root, codes);
        return codes;
    }

    /* Recursively get codes to the leaf node */
    private static void assignCode(Tree.Node root, String[] codes) {
        if (root.left != null) {
            root.left.code = root.code + "0";
            assignCode(root.left, codes);

            root.right.code = root.code + "1";
            assignCode(root.right, codes);
        }
        else {
            if(root.element.length() > 1){
                int a = (int)root.element.charAt(0)-97;
                int b = (int)root.element.charAt(1)-97;
                int c = (a*4) + b;
                codes[c] = root.code;
            }
            else{
                int c = (int)root.element.charAt(0)-97;
                codes[c] = root.code;
            }
        }
    }

    /** Get a Huffman tree from the codes */
    public static Tree getHuffmanTree(int[] counts, String[] symbols) {
        // Create a heap to hold trees
        Heap<Tree> heap = new Heap<Tree>();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0){
                heap.add(new Tree(counts[i], symbols[i])); // A leaf node tree
            }
        }

        while (heap.getSize() > 1) {
            Tree t1 = heap.remove(); // Remove the smallest weight tree
            Tree t2 = heap.remove(); // Remove the next smallest weight
            heap.add(new Tree(t1, t2)); // Combine two trees
        }

        return heap.remove(); // The final tree
    }

    public static void printCode(String[] codes, int[] counts, double[] probs, String[] symbols){
        System.out.printf("%-15s%-15s%-15s%-15s\n",
        "Character", "Weight", "Frequency", "Code");
        for (int i = 0; i < symbols.length; i++){
            if (counts[i] != 0){ // (char)i is not in text if counts[i] is 0
                System.out.printf("%-15s%-15.3f%-15d%-15s\n",
                symbols[i] + "", probs[i], counts[i], codes[i]);
            }
        }
    }

    /** Define a Huffman coding tree */
    public static class Tree implements Comparable<Tree> {
        Node root; // The root of the tree

        /** Create a tree with two subtrees */
        public Tree(Tree t1, Tree t2) {
            root = new Node();
            root.left = t1.root;
            root.right = t2.root;
            root.weight = t1.root.weight + t2.root.weight;
        }

        /** Create a tree containing a leaf node */
        public Tree(int weight, String element) {
            root = new Node(weight, element);
        }

        @Override /** Compare trees based on their weights */
        public int compareTo(Tree t) {
            if (root.weight < t.root.weight) // Purposely reverse the order
                return 1;
            else if (root.weight == t.root.weight)
                return 0;
            else
                return -1;
        }

        public class Node {
            String element; // Stores the character for a leaf node
            int weight; // weight of the subtree rooted at this node
            Node left; // Reference to the left subtree
            Node right; // Reference to the right subtree
            String code = ""; // The code of this node from the root

            /** Create an empty node */
            public Node() {
            }

            /** Create a node with the specified weight and character */
            public Node(int weight, String element) {
                this.weight = weight;
                this.element = element;
            }
        }
    }
}
