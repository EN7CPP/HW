import java.util.Comparator;


//Here I'm doing the same steps as in the heap sort exercise
//Except I do it only k times 
//Which generates the k larrgest numbers and puts them at the back of array

public class KLargestValues {
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
	
	
	public static <K,V> void heapify(HeapEntry<K,V>[]arr,int n,int r,Comparator<K> c) {
		
		int max=r;
		int lc=2*r+1;
		int rc=2*r+2;
		
		if(lc<n) {
			if(c.compare(arr[lc].getKey(), arr[max].getKey())==1)
			max=lc;
		}
		
		if(rc<n) {
			if(c.compare(arr[rc].getKey(), arr[max].getKey())==1)
			max=rc;
		}
		
		if(max!=r) {
			swap(arr,max,r);
			heapify(arr,n,max,c);
		}
		
				
	}
	
	public static <K,V> HeapEntry<K,V>[]  KLargestValues(HeapEntry<K,V>[] arr,int k,Comparator<K> c) {
		
		int lastParent=(arr.length-1)/2;
		HeapEntry<K,V>[]tbr=new HeapEntry[k];
		
		
		for(int i=lastParent;i>=0;i--) {
			heapify(arr,arr.length,i,c);
		}
		
		int counter=0;
		for(int i=arr.length-1;i>=0;i--) {
			swap(arr,i,0);
			heapify(arr,i,0,c);
			tbr[counter]=arr[i];
			counter++;
			if(counter==k) {
				return tbr;
			}
			
		}
		
		return null; //it's never reached
		
	}
	
	public static<K,V> void swap(HeapEntry<K,V>[] arr,int a,int b) {
		HeapEntry<K,V> temp=arr[a];
		arr[a]=arr[b];
		arr[b]=temp;
	}
	
	
	public static void main(String [] args) {
		HeapEntry<Integer,String> []arr=new HeapEntry[5];
		
		arr[0]=new HeapEntry<>(4,"ah");
		arr[1]=new HeapEntry<>(3,"yo");
		arr[2]=new HeapEntry<>(9,"huhu");
		arr[3]=new HeapEntry<>(7,"ahah");
		arr[4]=new HeapEntry<>(18,"yoyohah");
		
		
		
		for(HeapEntry<Integer,String>he:KLargestValues(arr,3,new DefaultComparator()) ) {
			System.out.println(he.getKey()+" "+ he.getValue());
		}
		
	} 
	
	
	
	
}
