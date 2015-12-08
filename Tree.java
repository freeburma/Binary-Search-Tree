/*
	Project Name 		: 	Binary Search Tree (Assignment 3)

	Date 		 		:  20-May-2015
	Project Description :  
*/

/*
 	The Tree interface will give the blue print for traversing the method.
 */
public interface Tree <E extends Comparable <E>> 
{
	// Return true if the element is in the tree
	public boolean search (E e); 
	
	/*
	  	Insert the element into the binary search tree
	  	Retrun true if the element is inserted successfully.
	 */
	public void insert (E e); 
	
	/*
	 	Deleting teh specified node form the tree.
	 	Return true if the element is deleted successfully
	 */
	public void delete (E e); 
	
	
	// Inorder travesral from the root
	public void inOrder (); 
	
	// Post order traversal
	public void postOrder (); 
	
	// Preorder
	public void preOrder (); 
	
	
	
	// If the tree is empty, return true
	public boolean isEmpty (); 
	
	
	
}
