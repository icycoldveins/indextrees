package index;

import java.io.*;
import java.util.Scanner;

// Your class. Notice how it has no generics.
// This is because we use generics when we have no idea what kind of data we are getting
// Here we know we are getting two pieces of data:  a string and a line number
public class IndexTree {
	// This is your root
	// again, your root does not use generics because you know your nodes
	// hold strings, an int, and a list of integers
	private IndexNode root;

	// Make your constructor
	// It doesn't need to do anything

	// complete the methods below

	// this is your wrapper method
	// it takes in two pieces of data rather than one
	// call your recursive add method
	public IndexTree() {

	}

	public void add(String word, int lineNumber) {
		root = add(root, word, lineNumber);
	}

	// your recursive method for add
	// Think about how this is slightly different the the regular add method
	// When you add the word to the index, if it already exists,
	// you want to add it to the IndexNode that already exists
	// otherwise make a new indexNode
	private IndexNode add(IndexNode root, String word, int lineNumber) {
		if (root == null) {
			return root = new IndexNode(word, lineNumber);
		}

		if (word.toLowerCase().compareTo(root.word) < 0) {
			root.left = add(root.left, word, lineNumber);
		} else if (word.toLowerCase().compareTo(root.word) > 0) {
			root.right = add(root.right, word, lineNumber);
		} else {
			if (!root.list.contains(lineNumber)) {
				root.occurences++;
				root.list.add(lineNumber);
			}
		}
		return root;
	}

	// returns true if the word is in the index
	public boolean contains(String word) {
		if (root == null) {
			return false;
		}
		IndexNode temp = root;
		while (temp != null) {
			if (temp.word.toLowerCase().equalsIgnoreCase(word)) {
				return true;
			} else if (word.toLowerCase().compareTo(temp.word) < 0) {
				temp = temp.left;
			} else {
				temp = temp.right;
			}
		}
		return false;
	}

	// call your recursive method
	// use book as guide
	public void delete(String word) {
		if(root==null){
			System.out.println("tree is empty");

		}
		else if(contains(word)){
			root=delete(root,word);
			System.out.println(word+"word deleted from tree successfully");
		}
		else{
			System.out.println("there was no word to delete");
		}
	}

	// your recursive case
	// remove the word and all the entries for the word
	// This should be no different than the regular technique.
	private IndexNode delete(IndexNode root, String word) {
		IndexNode equalscase,elsecase;

		if(word.toLowerCase().equals(root.word)){
			IndexNode left,right;
			left=root.left;
			right=root.right;
		
		if(left==null&& right==null){
			return null;
		}
		else if(left==null&& right!=null){
			return right;
		}
		else if(right==null&&left!=null){
			return left;
		}
		else{
			equalscase=left;
			while(equalscase.right!=null){
				equalscase=equalscase.right;
			}
			equalscase.right=right;
			return left;
		}

	}
	else if(word.toLowerCase().compareTo(root.word)<0){
		elsecase=delete(root.left,word);
		root.left=elsecase;
	}
	else{
		elsecase=delete(root.right,word);
		root.right=elsecase;
	}
	return root;
}
			
		

		

	// prints all the words in the index in inorder order
	// To successfully print it out
	// this should print out each word followed by the number of occurrences and the
	// list of all occurrences
	// each word and its data gets its own line
	public void printIndex(){
		printIndex(root);
	}
	public void printIndex(IndexNode root) {
		if(root==null){
			return;
		}
		printIndex(root.left);
		System.out.println(root);
		printIndex(root.right); 
	}


	public static void main(String[] args) {
		// add all the words to the tree
		int count=0;
		IndexTree index=new IndexTree();
		// print out the index
		String fileName = "pg100.txt";
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
				count++;
                String line = scanner.nextLine();
                System.out.println(line);
                String[] words = line.split("\\s+");
                for (String word : words) {
					word=word.replaceAll("\\p{Punct}", "");
					index.add(word,count);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

		// test removing a word from the index
		index.printIndex();
    }


}

