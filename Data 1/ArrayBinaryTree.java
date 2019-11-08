import java.util.Iterator;

//The array binary tree class

public class ArrayBinaryTree<E> extends AbstractBinaryTree<E>
{
	private Position [] arr;     //Array of positions
	private int capacity;		 // Capacity
	private int size=0;			//Size initially 0
	
	
	//Private Node Class
	private class Node<E> implements Position <E>{
		private int index;
		private E val;
		
		public Node(int i,E e) {
			this.index=i;
			this.val=e;
		}
		
		public void setVal(E e) {
			this.val=e;
		}
		public void setIndex(int i) {
			this.index=i;
		}
		
		public E getVal() {
			return this.val;
		}
		
		public int getIndex() {
			return this.index;
		}
		
		@Override
		public E getElement() throws IllegalStateException {
			return this.val;
		}
		
		
	}
	
	//No Arg Connstructor
	
	public ArrayBinaryTree() {
		arr=new Node[7];
		this.capacity=7;
	}
	
	//Constructor with an int parameter
	public ArrayBinaryTree(int cap) {
		arr=new Node[cap];
		this.capacity=cap;
	}
	
	
	//Get the left position of the given position 
	@Override
	public Position left(Position p) throws IllegalArgumentException {
		//Checking if it's a node instance
		 if (!(p instanceof Node))
		      throw new IllegalArgumentException("Not valid position type");
		 Node<E> node = (Node<E>) p;
		 if(2*node.getIndex()+1>=capacity){  //Check if left exists
			 return null;
		 }
		 else {
		 return arr[2*node.getIndex()+1];  //return the left child of the given position
		 }
	}
	
	//Get the right child position of the given position
	@Override
	public Position right(Position p) throws IllegalArgumentException {
		//same checking as in the previous case
		 if (!(p instanceof Node))
		      throw new IllegalArgumentException("Not valid position type");
		 Node<E> node = (Node<E>) p;
		 if(2*node.getIndex()+2>=capacity){
			 return null;
		 }
		 else {
		 return arr[2*node.getIndex()+2];
		 }
	}
	
	//returns the root of the tree
	@Override
	public Position root() {
		return arr[0];
	}
	
	
	//Returns the parent of the given position
	@Override
	public Position parent(Position p) throws IllegalArgumentException {
		 if (!(p instanceof Node))
		      throw new IllegalArgumentException("Not valid position type");
		 Node<E> node = (Node<E>) p;
		 
		 int ind=node.getIndex();
		 return arr[(ind-1)/2];
	}
	//returns the height of the tree
	public int heightOfTree() {
		return height(arr[0]);
	}
	
	//Function to add root
	public Position <E> addRoot(E e) {
		if(arr[0]==null) {				//Checking if root is not null
			arr[0]=new Node(0,e);		//And creating a new root
			size=1;						//Incrementing the size
			return arr[0];				//Returning the new position of the root
		}
		else {
			System.out.println("The root already exists");
			return null;
		}
	}
	
	//Adding the left child of the given position 
	public Position <E> addLeft(Position p,E e)throws IllegalArgumentException {
		 if(size+1>=capacity) {					//Checking whether tree is full or not
			 System.out.println("Tree is full");
			 return null;
		 }
		 //Checking the given paramater
		 if (!(p instanceof Node))
		      throw new IllegalArgumentException("Not valid position type");
		 Node<E> node = (Node<E>) p;
		
		 int leftInd=2*node.getIndex()+1;		
		 
		 if(arr[leftInd]==null) {				//If the left of the given 	
			 arr[leftInd]=new Node(leftInd,e);	//Position doesn't exist
			 size++;							//Create the left of it
			 return arr[leftInd];				//Increment the size and return the new position of left
		 }
		 else {
			 System.out.println("Left node already exists");
			 return null;
		 }
	}
	
	//Adding the right child of the given position
	public Position <E> addRight(Position p,E e)throws IllegalArgumentException {
		
		 if(size+1>=capacity) {					//Checking whether tree is full or not
			 System.out.println("Tree is full");
			 return null;
		 }
		 //Checking given parameter
		 if (!(p instanceof Node))
		      throw new IllegalArgumentException("Not valid position type");
		 Node<E> node = (Node<E>) p;
		 
		 int rightInd=2*node.getIndex()+2;
		 
		 if(arr[rightInd]==null) {					//If the right child doesn't exist
			 arr[rightInd]=new Node(rightInd,e);	//Create the right child
			 size++;								//Increment the size
			 return arr[rightInd];					//Return the new Position of the right child
		 }
		 else {
			 System.out.println("Right node already exists");
			 return null;
		 }
	}
	  //Removing the Position
	  public E remove(Position<E> p) throws IllegalArgumentException {
		  //Check the given parameter
		  if (!(p instanceof Node))
		      throw new IllegalArgumentException("Not valid position type");
		  //Check whether it has 2 children or not
		  if (numChildren(p) == 2)
		      throw new IllegalArgumentException("p has two children");
		 
		  
		  Node<E> node = (Node<E>) p;
		  
		  if(isExternal(p)) {				//If the given parameter is external
			  arr[node.getIndex()]=null;	//Make the index null
			  return node.getElement();		//return the value of the given parameter
		  }
		  Node<E> child = (left(node) != null ?(Node <E>)left(node) : (Node<E>)right(node) );
		   
		  arr[node.getIndex()]=child;
		      	
	      Queue<Position<E>> fringe = new LinkedQueue<>();
	      fringe.enqueue(child);                 // start with the node
	      while (!fringe.isEmpty()) {
	    	
	    	
	        Node<E> parent = (Node<E>)fringe.dequeue();     // remove from front of the queue
	        
	        for (Position<E> c : children(parent)) {	//Iterating over the children
	        											//Enqueing them 
	        	fringe.enqueue(c);
	        }
	        if(parent==child) {
	        	if(isExternal(parent))arr[parent.getIndex()]=null;	//If parent is a leaf make the index null 
	        	parent.setIndex(node.getIndex());					//Set the new index for the parent
	        }
	        else {
	        	
	        	int index=((parent.getIndex()-1)/2);			
	        	int newInd=((Node<E>)arr[index]).getIndex();
	        	//Check whether it's a left child or right child
	        	if(parent.getIndex()%2==1) {
	        		arr[2*newInd+1]=parent;    //Shift the parent to the new Index	
	        		if(isExternal(parent))arr[parent.getIndex()]=null;	//If the parent is a leaf make the old index null
	        		parent.setIndex(2*newInd+1);    //Set the new index of the parent
	        	}
	        	else {
	        		arr[2*newInd+2]=parent;				//Same steps as in the previous case
	        		if(isExternal(parent))arr[parent.getIndex()]=null;	//Except the new index is right child index
	        		parent.setIndex(2*newInd+2);
	        	}
	        }
	        
	      }
	      size--;   //Decrement the size
    
		  return node.getElement();	//Return the Element that was removed
	}
	@Override
	public int size() {
		return this.size;
	}
	
	
	public static void main(String [] args) {
		ArrayBinaryTree <Integer>abt=new ArrayBinaryTree<Integer>(30); 
		Position <Integer> p1=abt.addRoot(14);
		Position <Integer> p2=abt.addLeft(p1, 34);
		Position <Integer> p3=abt.addRight(p1, 55);
		Position <Integer> p4=abt.addLeft(p2, 17);
		Position <Integer> p7=abt.addLeft(p4, 44);
		Position <Integer> p8=abt.addRight(p4, 88);
		Position <Integer> p5=abt.addLeft(p3, 13);
		Position <Integer> p6=abt.addRight(p3, 19);
		Position <Integer> p15=abt.addLeft(p7, 129);
		abt.remove(p7);
		
		
		for(Position <Integer> p:abt.breadthfirst()) {
			System.out.println(p.getElement());
		}
		
		
		
		
	}
	
	
}
