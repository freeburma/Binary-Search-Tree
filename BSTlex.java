import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 	Project Name 		: 	Binary Search Tree (Assignment 3)
 
 	Date 		 		:  20-May-2015
 	Project Description :  This BSTlex class will read the files and put the data 
						 	in the Binary Search Tree. The BST class inherit the Comparable 
						 	Class form Java.Util.Comparable and Comparator. 
						 	The insertion will perform by in this way. 
						 	1). The program will check the is data in the BST, if it 
						 		was found will delete it. 
						 	2). Otherwise, it will insert to BST. By the end of the file, 
						 		there were only odd words which appear odd number of times.
						 		
						 	3). It will display In-order Traversal by traversing the BST. 	 
 */

public class BSTlex <E extends Comparable <E>>
					extends AbstractTree <E>
{
	protected BST <E> root; 	// The root of BST

	
	private Comparator <E> comparator; 	// The method to compare 
	
	/*
	 	Default Constructor
	 */
	public BSTlex ()
	{
		root = null; 
		comparator = null; 
	}
	
	/*
 		Constructor	- will create the object by using Comparator Class
 		@param comparator - the object to comparator
	 */
	public BSTlex (Comparator <E> comparator)
	{
		root = null; 
		this.comparator = comparator; 
	}
	
	/*
	 	Reading the file from Harddisk by its name.
	 	This method will throw to exception by Input and Output
	 	Erros such as FileNotFoundException
	 	@param fileName - the given name input by user 
	 */
	public void readFile (String fileName) throws IOException
	{
		FileReader reader = new FileReader (fileName); 		// Opening the file name
		BufferedReader br = new BufferedReader (reader);	// Connecting to  
		
		String line = br.readLine(); 			// Reading the first line form the file
		
		while (line != null)
		{
			// Reading the file;
			//String p  = "\\w+" ; 
			String p  = "[a-zA-Z0-9]+" ; 	// Will accept all the chars except special chars
			
			// Lookingfor pattern 
			Pattern pattern = Pattern.compile(p); 
			Matcher matcher = pattern.matcher(line); 
			
			// Separating the lines to the individual words 
			while (matcher.find())
			{
				 String word = matcher.group(0); 
				 String insertWord = word.toLowerCase();
				
				// Inserting word to the binary tree;	
				//if (isInTree ((E)insertWord))
				if (search ((E)insertWord))
				{
					delete ((E) insertWord);	// deleting the words
				}
				else 
				{
					insert ((E) insertWord);	// inserting a word
				}				
			}
			 
			// Reading the next line
			line = br.readLine(); 
		}
		
		
	}// End readFile

	
	
	/*
	 	Searching for the nodes whether it is in the BST or not
	 	@see Tree#search(java.lang.Comparable)
	 */
	@Override 
	public boolean search (E word)
	{			
		BST <E> tmp = root; 	// Creating the obj 
		
		// Looking through the BST
		while (tmp != null)
		{
			if (word.compareTo(tmp.word) < 0)	// Going to left child
			{
				tmp = tmp.leftChild; 
			}
			else if(word.compareTo(tmp.word) > 0) // Going to right child
			{
				tmp = tmp.rightChild; 
			}
			else 
			{
				// Word is found 
				return true; 
			}
		}// End while
			
		// Word not found	
		return false; 
	}
	
	/*
	 	This insert method will insert the given word. 
	 	This method did not choose data types because
	 	I am using generic. Any data types are welcome to p
	 	put in BST. 
	 	The comparable class will compare the data and put put 
	 	them in the correct places. 
	 	
	 	@param word - data to insert
	 	
	 */
	@Override
	public void insert (E word)
	{
		// Inserting at the root
		if (root == null)
		{
			root = new BST<E> (word); 
			System.out.print (root.word + " ADDED\n"); 
			return; 
		}
		
		// If the word is found, do nothing
		if (root.word.equals (word))
		{
			return ; 
		}
			
		BST <E> current = root; 		// Creating an object
		System.out.print(current.word + " "); 	// Printing the root object
		
		// Checking the words 
		while (!current.word.equals(word))
		{
			// Going to left child
			if (word.compareTo(current.word) < 0)
			{
				if (current.leftChild != null)
				{
					current = current.leftChild;
					System.out.print(current.word + " "); 
				}
				else 	// Inserting the new node as left child
				{
					current.leftChild = new BST <E> (word);
					 
				}
			}
			else 
			{
				// Going to right child
				if (current.rightChild != null)
				{
					current = current.rightChild;
					System.out.print(current.word + " "); 
				}
				else	// Inserting the new node as right child
					current.rightChild = new BST <E> (word); 
			}
						
		}// End while
			
	
		System.out.print(" " + "ADDED \n"); 
	
		return;
	}// End search
	
	
	/*
	  	In-order traversal from the root and printing the data
	 */
	protected void inOrder (BST <E> root)
	{
		// Receiving the null nodes by the end of the treaversing
		if (root == null)
		{	
			return; 
		}
		inOrder (root.leftChild); 
		System.out.println(root.word ); 
		inOrder (root.rightChild); 
		
	}// End inOrder
	
	/*
	 Calling in-order method to print out.
	 * @see AbstractTree#inOrder()
	 */
	@Override 
	public void inOrder ()
	{
		inOrder (root); 
	}// End inOrder
		
	
	// If the tree is empty, return true
	public boolean isEmpty ()
	{
		return (root == null); 
	}

	
	
	/*
 		Deleting Nodes 
 		@param word - the words or data types to be delete
	 */
	public void delete (E word)
	{
		root = delete (root, word); 
	}
	
	
	/*
		Looking the nodes to delete
		@node  		- node to delete  
		@param word - the words or data types to be delete
		
	 */
	private BST<E> delete (BST<E> node, E word)
	{
		// Checking empty node
		if (node == null)
			throw new RuntimeException ("Can't delete"); 
		else if (compare (word, node.word) < 0)	// Going to the left child
		{
			System.out.print(node.word + " "); 
			node.leftChild = delete (node.leftChild, word);  // Deleting left child
			
		}
		else if (compare (word, node.word) > 0) // Going to the right child
		{
			System.out.print(node.word + " "); 
			node.rightChild = delete (node.rightChild, word); // Deleting right child
		}
		else 
		{
			if (node.leftChild == null) 	// No Left child 
			{
				return node.rightChild; 	// Right child becomes parent node
			}
			else if (node.rightChild == null) // No Right child 
			{
				return node.leftChild; 		// Left child becomes parent node
			}
			else  // Parent with both childs
			{
				node.word = retrieveData (node.leftChild); 
				node.leftChild = delete (node.leftChild, node.word);
				
			}
			System.out.print(" " + "REMOVED\n");
		}
		
		 
		return node; 
		
	}
	
	/*
	 	Getting the Right Most Children and 
	 */
	private E retrieveData (BST <E> p)
	{
		while (p.rightChild != null)
			p = p.rightChild; 
		
		return p.word; 
	}
	
	/*
	 	This method will return the compare 2 objects 
	 	Return the bigger the objects.
	 */
	private int compare (E x, E y)
	{
		if (comparator == null)
			return x.compareTo(y); 
		else 
			return comparator.compare (x, y); 
	}
	
	/*
	  Counting the height of the BST tree. 
	  It will compare both left and right, then 
	  return the maximum height. 
	 */
	private int height (BST <E> root)
	{
		int height = 0; 
		
		if (root == null)
			return 0; 
		else 
		{
			int rightSide = 0;
			int leftSide = 0;
			
			rightSide += height (root.rightChild); 
			leftSide += height (root.leftChild);
			
			return height = 1 + Math.max(leftSide, rightSide); 
		}
	}// End Height
	
	/*
	  Calling the height and counting recursively.
	 */
	public int height ()
	{
		return height (root); 
	}
	
	/*
	 	BSTNode inner class will create an object 
	 	depends on root, right and left child. 
	 	This class inherits form java.lang.Comparable inherit
	 	class. 
	 */
	protected class BST <E extends Comparable<E>> 
	{
		protected E word; 					// Data
		protected BST <E> rightChild; 	// Right child
		protected BST <E> leftChild; 	// Left child
	 
		/*
		 	Constructor will receive and data and create the object
		 */
		public BST (E word)
		{
			this.word = word; 
		}		
	}



	

}
