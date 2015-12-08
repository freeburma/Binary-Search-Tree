import java.io.IOException;

/*
	Project Name 		: 	Binary Search Tree (Assignment 3)

	Date 		 		:  20-May-2015
	Project Description :  
	
	java OddWords testFile.txt
*/
public class OddWords 
{
	
	public static void main(String[] args) throws IOException 
	{
		if (args.length != 1)
		{
			System.out.println("Enter the file name : \nExample : <file1.txt>"); 			
		}
		else 
		{
		
			// Create a BST
			BSTlex <String> tree = new BSTlex <String> (); 
			
			//String fileName = "sample.txt";
			
			String fileName = args [0];
			String word = "lexicon:";
			
			// Reading the file from harddisk
			tree.readFile(fileName);
			
			// Changing the word to upper case
			System.out.println("\n\n" + word.toUpperCase());
			
			// Traversing the list for printing
			tree.inOrder(); 
			
			System.out.println("\n\nThe Height of Binary Tree : " + tree.height()); 
			
			
	
			System.out.println("\nProgram Finished"); 
		}
	}

}
