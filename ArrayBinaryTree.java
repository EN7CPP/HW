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
		  }
		  Node<E> child = (left(node) != null ?(Node <E>)left(node) : (Node<E>)right(node) );
		   
		    if (node == root()) {
		    	arr[0]=child;
		    	
		    }
		      	
	      Queue<Position<E>> fringe = new LinkedQueue<>();
	      fringe.enqueue(child);                 // start with the node
	      while (!fringe.isEmpty()) {
	    	  
	        Position<E> parent = fringe.dequeue();     // remove from front of the queue
	        Node<E> grandparent=(Node <E>)parent(parent);
	        for (Position<E> c : children(parent)) {
	          if(left(parent)==c) {
	        	  int index=grandparent.getIndex();
	        	  Node <E> ch=(Node<E>)c;
	        	  arr[2*index+1]=c;
	        	  fringe.enqueue(c);				// add children to back of queue
	          }
	          else {
	        	  int index=grandparent.getIndex();
	        	  Node <E> ch=(Node<E>)c;
	        	  arr[2*index+2]=c;
	        	  fringe.enqueue(c);				// add children to back of queue
	          }
	        }  	
	        
	      }
	      size--;
    
		    return node.getElement();
		}  
	
	public static void main(String [] args) {
		ArrayBinaryTree <Integer>abt=new ArrayBinaryTree<Integer>(120); 
		Position <Integer> p1=abt.addRoot(14);
		Position <Integer> p2=abt.addLeft(p1, 34);
		Position <Integer> p3=abt.addRight(p1, 55);
		Position <Integer> p4=abt.addLeft(p2, 17);
		Position <Integer> p7=abt.addLeft(p4, 44);
		Position <Integer> p8=abt.addRight(p4, 88);
		Position <Integer> p5=abt.addLeft(p3, 13);
		Position <Integer> p6=abt.addRight(p3, 19);
		abt.remove(p2);
		
	
		for(Position <Integer> p:abt.breadthfirst()) {
			System.out.println(p.getElement());
		}
		
		
		
		
	}
	@Override
	public int size() {
		return this.size;
	}
	
	
}
