
public class StackPriorQueue <E> implements Stack<E>{
	
	private int ind;
	private UnsortedPriorityQueue<Integer,E> upq;
	
	
	
	public StackPriorQueue() {
		upq=new UnsortedPriorityQueue<>(new DefaultComparator<Integer>());
	}
	
	@Override
	public int size() {
		return upq.size();
	}

	@Override
	public boolean isEmpty() {
		return upq.isEmpty();
	}

	@Override
	public void push(E e) {
		upq.insert(ind, e);
		ind++;
	}

	@Override
	public E top() {
		return upq.min().getValue();
	}

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
