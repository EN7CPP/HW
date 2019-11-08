
public class StackPriorQueue <E> implements Stack<E>{
	
	private int ind;
	private UnsortedPriorityQueue<Integer,E> upq;
	
	
	//Construct the priority queue with already changed default constructor
	public StackPriorQueue() {
		upq=new UnsortedPriorityQueue<>(new DefaultComparator<Integer>());
	}
	//Get the size
	@Override
	public int size() {
		return upq.size();
	}
	//Check if stack is empty
	@Override
	public boolean isEmpty() {
		return upq.isEmpty();
	}
	//Push to the stack that is call the insert method on priority queue
	@Override
	public void push(E e) {
		upq.insert(ind, e);
		ind++;
	}
	//Get the top of the stack 
	@Override
	public E top() {
		return upq.min().getValue();
	}
	//Pop the top of the stack
	@Override
	public E pop() {
		return upq.removeMin().getValue();
	}

	public static void main(String [] args) {
		StackPriorQueue<Integer> st= new StackPriorQueue<>();
		st.push(4);
		st.push(6);
		st.push(9);
		st.pop();
		System.out.print(st.size());
	}
	
	
}
