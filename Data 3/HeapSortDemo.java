
public class HeapSortDemo {

	private static class HeapEntry<Integer,V> implements Entry<Integer,V>{
		Integer key;
		V val;
		
		public HeapEntry(Integer i,V v) {
			this.key=i;
			this.val=v;
		}
		
		@Override
		public Integer getKey() {
			// TODO Auto-generated method stub
			return this.key;
		}
		
		
		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return this.val;
		}
		
		
	} 
	
	
	public static <V> void heapify(HeapEntry<Integer,V>[]arr,int n,int r) {
					
		int max=r;				//Set maximum the root index
		int lc=2*r+1;			//Left of the root
		int rc=2*r+2;			//Right of the root
		
		if(lc<n&&arr[lc].getKey()>arr[max].getKey()) { //If the key of the left is greater than
			max=lc;									   //That of the max make the new max the left
		}
		
		if(rc<n&&arr[rc].getKey()>arr[max].getKey()) {	//If the key of the right is greater than key of the max
			max=rc;										//Make the new max the right one			
		}
		
		if(max!=r) {									//If max was changed
			swap(arr,max,r);							//swap with the root
			heapify(arr,n,max);							//Do heapify
		}
		
				
	}
	
	public static <V> void  heapSort (HeapEntry<Integer,V>[] arr) {
		//Get the index of the last parent
		int lastParent=(arr.length-1)/2;
		//Starting from that index heapify 
		for(int i=lastParent;i>=0;i--) {
			heapify(arr,arr.length,i);
		}
		
		//starting from the last index change the places with the root and heapify 
		for(int i=arr.length-1;i>=0;i--) {
			swap(arr,i,0);
			heapify(arr,i,0);
			
		}
		
		
	}
	//Swap the given indices
	public static<V> void swap(HeapEntry<Integer,V>[] arr,int a,int b) {
		HeapEntry<Integer,V> temp=arr[a];
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
		
		heapSort(arr);
		
		for(HeapEntry<Integer,String>he:arr ) {
			System.out.println(he.getKey()+" "+ he.getValue());
		}
		
	} 
	
}
