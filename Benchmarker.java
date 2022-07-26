/**
 * 
 */
package hw3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

/**
 * @author Josh
 *
 */
public class Benchmarker {
    private String inputFileName;
    
    private String text;
    
    private MyLinkedList<String> listOfWords;
    
    private MyBinarySearchTree<String> treeOfWords;
    
    private MyBinarySearchTree<String> balancedTreeOfWords;
    
    private int words;
    
    public Benchmarker(String theInputFile) {
        inputFileName = theInputFile;
        words = 0;
        readInputFile();
        listOfWords = new MyLinkedList<String>();
        treeOfWords = new MyBinarySearchTree<String>();
        balancedTreeOfWords = new MyBinarySearchTree<String>(true);
        loadList();
        loadTree();
        loadBalancedTree();
    }
    
    private void readInputFile() {
        long startTime = System.currentTimeMillis();
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            int theChar = reader.read();
            while ((Integer) theChar > -1) {
                    result.append((char) theChar);
                    theChar = reader.read();
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("An error has occured.");
        }
        text = result.toString();
        System.out.println("Reading input file \"" + inputFileName + "\"... " + result.length() + " characters in " + Math.round(System.currentTimeMillis() - startTime) + " milliseconds.");   
    }
    
     private void loadList() {
        long startTime = System.currentTimeMillis();
        String regex = "\s+|[^a-zA-Z0-9']+";
        Scanner textScan = new Scanner(text);
        textScan.useDelimiter(regex);
        while (textScan.hasNext()) {
            String word = textScan.next();
            if (!listOfWords.contains(word)) {
                listOfWords.add(word);
            }
        }
        textScan.close();
        System.out.println("Adding unique words to a linked list... in " + Math.round(System.currentTimeMillis() - startTime) + " milliseconds.");
        System.out.println("The linked list made " + listOfWords.getComparisons() + " comparisons.");   // FIX COMPARISON COUNT IN MLL
        System.out.println("The linked list has a length of " + listOfWords.size() + ".");
    } 
    
    private void loadTree() {
        long startTime = System.currentTimeMillis();
        String regex = "\s+|[^a-zA-Z0-9']+";
        Scanner textScan = new Scanner(text);
        textScan.useDelimiter(regex);
        while (textScan.hasNext()) {
            String word = textScan.next();
            words++;
            if (!treeOfWords.contains(word)) {
                treeOfWords.add(word);
            }
        }
        textScan.close();
        System.out.println("Adding unique words to a binary search tree... in " + Math.round(System.currentTimeMillis() - startTime) + " milliseconds.");
        System.out.println("The binary search tree made " + treeOfWords.getComparisons() + " comparisons.");
        System.out.println("The binary search tree has a depth of " + (treeOfWords.height() - 1) + ".");
    } 
    
    private void loadBalancedTree() {
        long startTime = System.currentTimeMillis();
        String regex = "\s+|[^a-zA-Z0-9']+";
        Scanner textScan = new Scanner(text);
        textScan.useDelimiter(regex);
        while (textScan.hasNext()) {
            String word = textScan.next();
            if (!balancedTreeOfWords.contains(word)) {
                balancedTreeOfWords.add(word);
            }
        }
        textScan.close();
        System.out.println("Adding unique words to an AVL tree... in " + Math.round(System.currentTimeMillis() - startTime) + " milliseconds.");
        System.out.println("The AVL tree made " + balancedTreeOfWords.getComparisons() + " comparisons.");
        System.out.println("The AVL tree has a depth of " + (balancedTreeOfWords.height() - 1) + ".");
        System.out.println("The AVL tree made " + balancedTreeOfWords.getRotations() + " rotations.");
    }
    
    public String toString() {
        String result = balancedTreeOfWords.size() - 1 + " unique words. \n";
        return result + words + " total words.";
    }
}
