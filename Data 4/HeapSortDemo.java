
import java.util.Comparator;

public class HeapSortDemo {

	private static class HeapEntry<K,V> implements Entry<K,V>{
		K key;
		V val;
		
		public HeapEntry(K i,V v) {
			this.key=i;
			this.val=v;
		}
		
		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return this.key;
		}
		
		
		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return this.val;
		}
		
		
	} 
	
	
	public static <K,V> boolean  isHeap (HeapEntry<K,V>[] arr,Comparator<K> comp) {
		
		boolean b=true;				//Boolean to check if it's still a heap
		//Check for max heap
		for(int i=arr.length-1;i>=1;i--) {
			int parent=(i-1)/2;
			if(b) {
				
				if(comp.compare(arr[parent].getKey(), arr[i].getKey())==-1) {//If parent is less than the child
					b=false;												 //Make the boolean false
				}
			}
			else break;
			
			
		}
		if(b)return b;    //If boolean stays true return true
		
		b=true;
		//Check for the min heap
		for(int i=arr.length-1;i>=1;i--) {
			int parent=(i-1)/2;
			if(b) {
				
				if(comp.compare(arr[parent].getKey(), arr[i].getKey())==1) {//In case parent is greater than child return false
					b=false;
					return b;
				}
			}
			else break;
			
			
		}
		
		return b;
		
	}

	
	
	public static void main(String [] args) {
		HeapEntry<Integer,String> []arr=new HeapEntry[5];
		
		arr[0]=new HeapEntry<>(1,"ah");
		arr[1]=new HeapEntry<>(2,"yo");
		arr[2]=new HeapEntry<>(3,"huhu");
		arr[3]=new HeapEntry<>(4,"ahah");
		arr[4]=new HeapEntry<>(5,"yoyohah");
		
		System.out.println(isHeap(arr,new DefaultComparator()));
		
		for(HeapEntry<Integer,String>he:arr ) {
			System.out.println(he.getKey()+" "+ he.getValue());
		}
		
	} 
	
}
