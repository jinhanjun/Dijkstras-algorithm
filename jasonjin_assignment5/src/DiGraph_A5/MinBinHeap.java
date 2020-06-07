package DiGraph_A5;

public class MinBinHeap implements Heap_Interface {
	  private EntryPair[] array; //load this array
	  private int size = 0;
	  private static final int arraySize = 10000; //Everything in the array will initially 
	                                              //be null. This is ok! Just build out 
	                                              //from array[1]

	  public MinBinHeap() {
	    this.array = new EntryPair[arraySize];
	    array[0] = new EntryPair(null, -100000);
//	    this.size = 0;
	    										//0th will be unused for simplicity 
	                                             //of child/parent computations...
	                                             //the book/animation page both do this.
	  }
	    
	  //Please do not remove or modify this method! Used to test your entire Heap.
	  @Override
	  public EntryPair[] getHeap() { 
	    return this.array;
	  }

	@Override
	public void insert(EntryPair entry) {
		int locate = size +1;
		array[locate] = entry; 
		while(array[locate].getPriority() < array[locate / 2].getPriority()) { //continue while location priority is still less than parent priority
			// swap the locations and halve the location
			EntryPair temp = array[locate]; 
			array[locate] = array[locate/2];
			array[locate/2] = temp;
			locate = locate/2;
		}	
		//increment size
		size++;
	}

	@Override
	public void delMin() {
//		EntryPair temp = array[size]; //temp is the last item
//		if (array[1] == null) {
//			//stop is the heap is empty
//			return;
//		}
//		array[size] = null; //remove last item
//		array[1] = temp; //root value is now the last item
//		long tempPriority = temp.getPriority(); //priority value of the temp
//		int locate = 1;
//		while (array[locate*2] != null || array[(locate*2)+1] != null) { //while the left and right children are NOT null
//			if (array[locate*2] == null) { //if left child is null
//				if (tempPriority > array[(locate*2)+1].getPriority()) {
//					//if priority is still greater, bubble down
//					array[locate] = array[(locate*2)+1]; //entry at that location is now the right child
//					locate = (locate*2)+1; //new location is now right child
//				} else {
//					array[locate] = null; 
//					array[locate] = temp; //current location is now temp, end loop
//					break;
//				}
//			} else if (array[(locate*2)+1] == null) { //if right child is null
//				if (tempPriority > array[locate*2].getPriority()) {
//					//if priority greater, bubble down
//					array[locate] = array[locate*2];  //entry at locate is now left child
//					locate = locate*2; //location is now left child
//				} else {
//					array[locate] = null;
//					array[locate] = temp; //current location is now temp, end loop
//					break;
//				}
//			} else if (array[locate*2].getPriority() < array[(locate*2)+1].getPriority()) { //if the left priority is less than right priority
//				if (tempPriority > array[locate*2].getPriority()) { //scan if temp priority is greater than left priority
//					array[locate] = array[locate*2]; //entry at locate is now left child
//					locate = locate*2; //current location is now left
//				} else {
//					array[locate] = null;
//					array[locate] = temp; //current locate is assigned temp, end loop
//					break;
//				}
//			} else if (array[locate*2].getPriority() > array[(locate*2)+1].getPriority()) { //if left priority greater than right priority
//				if (tempPriority > array[(locate*2)+1].getPriority()) { //scan if temp priority is greater than right priority
//					array[locate] = array[(locate*2)+1]; //current locate is assigned right child
//					locate = (locate*2)+1; //locate is now right child
//				} else {
//					array[locate] = null;
//					array[locate] = temp; //current locate is assigned temp, end loop
//					break;
//				}
//			} 
//			if (array[locate*2] == null) {
//				if (array[(locate*2)+1] == null) {
//					//if both children are null
//					array[locate] = null;
//					array[locate] = temp; //current locate assigned temp, end loop
//					break;
//				}
//			}
//		}
//		size--;
		int start = 1;
		if (array[start] != null) {
            if (array[start +1] == null) {
            	array[start] = null;
            }
            array[start] = array[size()];
            array[size()] = null;
            if (array[start +1] != null) {
            	bubbleDown(1);
            }
            size--;
        }

	}

	@Override
	public EntryPair getMin() {
		if (size == 0) {
			return null;
		} else {
			return array[1];
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void build(EntryPair[] entries) {
		for (int i = 0; i < entries.length; i++) {
			this.array[i+1]= entries[i];
			size++;
		}
		
		for (int i = 0; i < entries.length ; i++) { 
			int locate = size - i; //traverse from end of heap
			while(array[locate/2].getPriority() > array[locate].getPriority()) { //while the priority of the parent is greater than that of child
				//swap the child and the parent
				EntryPair temp = array[locate];
				array[locate] = array[locate/2];
				array[locate/2] = temp;
				//bubble down
				if (array[locate*2] == null && array[(locate*2)+1] == null) { 
					break; //break if both children are null
				} else if (array[locate*2] == null) {
					//if left is null, go right
					locate = (locate*2)+1;
				} else if (array[(locate*2)+1] == null) {
					//if right is null, go left
					locate = locate * 2;
				} else if (array[locate*2].getPriority() > array[(locate*2)+1].getPriority()) {
					//if left priority > right priority, go right
					locate  = (locate*2) +1;
				} else {
					//if left priority < right priority, go left
					locate = locate *2;
				}
			}
		}
	}
	
	private void bubbleDown(int i) {
        int newI = i*2;
        if (array[(i*2) +1] != null && array[(i*2)+1].getPriority() < array[newI].getPriority()) {
        	newI = (i*2)+1;
        }
        if (array[i].getPriority() > array[newI].getPriority()) {
            EntryPair tmp = array[newI];
            array[newI] = array[i];
            array[i] = tmp;
            if (array[(newI*2)] != null) {
            	bubbleDown(newI);
            }
        }
    }



	}