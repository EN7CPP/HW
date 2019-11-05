import java.util.Iterator;

public class ArrayBinaryTree<E> extends AbstractBinaryTree<E>
{
	private Position [] arr;
	private int capacity;
	private int size=0;
	
	
	
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
	
	public ArrayBinaryTree() {
		arr=new Node[7];
		this.capacity=7;
	}
	public ArrayBinaryTree(int cap) {
		arr=new Node[cap];
		this.capacity=cap;
	}
	
	@Override
	public Position left(Position p) throws IllegalArgumentException {
		 if (!(p instanceof Node))
		      throw new IllegalArgumentException("Not valid position type");
		 Node<E> node = (Node<E>) p;
		 if(2*node.getIndex()+1>=capacity){
			 return null;
		 }
		 else {
		 return arr[2*node.getIndex()+1];
		 }
	}

	@Override
	public Position right(Position p) throws IllegalArgumentException {
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

	@Override
	public Position root() {
		return arr[0];
	}

	@Override
	public Position parent(Position p) throws IllegalArgumentException {
		 if (!(p instanceof Node))
		      throw new IllegalArgumentException("Not valid position type");
		 Node<E> node = (Node<E>) p;
		 
		 int ind=node.getIndex();
		 return arr[(ind-1)/2];
	}
	
	public int heightOfTree() {
		return height(arr[0]);
	}
	
	
	public Position <E> addRoot(E e) {
		if(arr[0]==null) {
			arr[0]=new Node(0,e);
			size=1;
			return arr[0];
		}
		else {
			System.out.println("The root already exists");
			return null;
		}
	}
	
	
	public Position <E> addLeft(Position p,E e)throws IllegalArgumentException {
		 if(size+1>=capacity) {
			 System.out.println("Tree is full");
			 return null;
		 }
		 if (!(p instanceof Node))
		      throw new IllegalArgumentException("Not valid position type");
		 Node<E> node = (Node<E>) p;
		
		 int leftInd=2*node.getIndex()+1;
		 
		 if(arr[leftInd]==null) {
			 arr[leftInd]=new Node(leftInd,e);
			 size++;
			 return arr[leftInd];
		 }
		 else {
			 System.out.println("Left node already exists");
			 return null;
		 }
	}
	public Position <E> addRight(Position p,E e)throws IllegalArgumentException {
		 if(size+1>=capacity) {
			 System.out.println("Tree is full");
			 return null;
		 }
		 if (!(p instanceof Node))
		      throw new IllegalArgumentException("Not valid position type");
		 Node<E> node = (Node<E>) p;
		 
		 int rightInd=2*node.getIndex()+2;
		 
		 if(arr[rightInd]==null) {
			 arr[rightInd]=new Node(rightInd,e);
			 size++;
			 return arr[rightInd];
		 }
		 else {
			 System.out.println("Right node already exists");
			 return null;
		 }
	}
	
	  public E remove(Position<E> p) throws IllegalArgumentException {
		  if (!(p instanceof Node))
		      throw new IllegalArgumentException("Not valid position type");
		  
		  if (numChildren(p) == 2)
		      throw new IllegalArgumentException("p has two children");
		 
		  
		  Node<E> node = (Node<E>) p;
		  if(isExternal(p)) {
			  arr[node.getIndex()]=null;
			  return node.getElement();
		  }
		  Node<E> child = (left(node) != null ?(Node <E>)left(node) : (Node<E>)right(node) );
		   
		  arr[node.getIndex()]=child;
		      	
	      Queue<Position<E>> fringe = new LinkedQueue<>();
	      fringe.enqueue(child);                 // start with the node
	      while (!fringe.isEmpty()) {
	    	
	    	
	        Node<E> parent = (Node<E>)fringe.dequeue();     // remove from front of the queue
	        
	        for (Position<E> c : children(parent)) {
	        	
	        	fringe.enqueue(c);
	        }
	        if(parent==child) {
	        	if(isExternal(parent))arr[parent.getIndex()]=null;
	        	parent.setIndex(node.getIndex());
	        }
	        else {
	        	
	        	int index=((parent.getIndex()-1)/2);
	        	int newInd=((Node<E>)arr[index]).getIndex();
	        	if(parent.getIndex()%2==1) {
	        		arr[2*newInd+1]=parent;
	        		if(isExternal(parent))arr[parent.getIndex()]=null;
	        		parent.setIndex(2*newInd+1);
	        	}
	        	else {
	        		arr[2*newInd+2]=parent;
	        		if(isExternal(parent))arr[parent.getIndex()]=null;
	        		parent.setIndex(2*newInd+2);
	        	}
	        }
	        
	      }
	      size--;
    
		  return node.getElement();
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
