import java.util.NoSuchElementException;
///can you absorb the else if statement into the else statement in deleteBack()?
public class LinkedIntList {
	//can I call size method in the reverse method? is this okay?
	public class ListNode {
		int data;
		ListNode next;
		public ListNode(int data) {
			this.data = data;
			this.next = null;
		}
		public ListNode(int data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}

	private ListNode front;
	public LinkedIntList() {
		front = null;
	}

	// Returns value in list at given index.
	// Precondition: 0 <= index < size()
	public int get(int index) {
		ListNode current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.data;
	}

	// Adds the given value to the end of the list.
	public void add(int value) {
		if (front == null) {
			// adding to an empty list
			front = new ListNode(value);
		} else {
			// adding to the end of an existing list
			ListNode current = front;
			while (current.next != null) {
				current = current.next;
			}
			current.next = new ListNode(value);
		}
	}

	// Inserts the given value at the given index.
	// Precondition: 0 <= index <= size()
	public void add(int index, int value) {
		if (index == 0) {
			// adding to an empty list
			front = new ListNode(value, front);
		} else {
			// inserting into an existing list
			ListNode current = front;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			current.next = new ListNode(value, current.next);
		}
	}

	// Removes value at given index from list.
	// Precondition: 0 <= index < size()
	public void remove(int index) {
		if (index == 0) {
			// special case: removing first element
			front = front.next;
		} else {
			// removing from elsewhere in the list
			ListNode current = front;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			current.next = current.next.next;
		}
	}

	// Adds given value to list in sorted order.
	// Precondition: Existing elements are sorted
	public void addSorted(int value) {
		if (front == null || value <= front.data) {
			// insert at front of list
			front = new ListNode(value, front);
		} else {
			// insert in middle of list
			ListNode current = front;
			while (current.next != null && 
					current.next.data < value) {
				current = current.next;
			}
		}
	}
	
	// Prints the list.
	public void print() {
		ListNode current = front;
		while (current != null) {
		    System.out.println(current.data);
		    current = current.next;  // move to next node
		}
	}
	// Returns the number of nodes in the linked list. 
	public int size() {
		ListNode current = front;
		int count = 0;
		while(current != null) {	
			count++;
			current = current.next;
		}
		return count;

	}
	
	// Returns the values in the linked list with a space in between the nodes, as a string. 
	public String toString() {
		String str = "";
		ListNode current = front;
		
		while(current != null) {
			str = str + current.data + " ";
			current = current.next;
		}
		return str;
	}

	//TODO: Implement missing methods:

	/**
	 * public boolean contains(int value) (1 pt)
	 * Write a method contains that accepts a value and returns true if the value exists
	 * in the linked list, otherwise it returns false
	 */
	public boolean contains(int value) {
		ListNode current = front;
		
		while (current != null) {
			if (current.data == value) {
				return true;
			} else {
				current = current.next;
			}
		}
		return false;
	}

	/**
	 * public void set(int index, int value) (1 pt)
	 * Write a method set that accepts an index and a value and sets the list's element at that index 
	 * to have the given value. 
	 * You may assume that the index is between 0 (inclusive) and the size of the list (exclusive).--no adding elements
	 */
	public void set(int index, int value ) {
		ListNode current = front;
		int count = 0;
		
		while (current != null) {
			if (count == index) {
				current.data = value;
			}
			count++;
			current = current.next;
		}
	}


	/**
	 * public boolean isSorted() (1 pt)
	 * Write a method isSorted that returns true if the list is in sorted (nondecreasing) order and 
	 * returns false otherwise. 
	 * An empty list is considered to be sorted.
	 */
	public boolean isSorted() {
		ListNode current = front;
		
		if (current != null) {
			while (current.next != null) {
				if (current.data > current.next.data) {
					return false;
				}
				current = current.next;
			}
		}
		return true;
	}

	/**
	 * public int deleteBack() (1 pt)
	 * Write a method deleteBack that deletes the last value (the value at the back of the list) and 
	 * returns the deleted value. 
	 * If the list is empty, your method should throw a NoSuchElementException.
	 */
	
	public int deleteBack() {
		ListNode current = front;
		int result = 0;
		
		if (current == null) {
			throw new NoSuchElementException();
		} else if (current.next == null) {
			result= current.data;
			front = null;
		} else {
			while (current.next.next != null) {
					current= current.next;
			}
			result= current.next.data;
			current.next= current.next.next;
		} 
		return result;
	}

	/**
	 * public int lastIndexOf(int value) (1 pt)
	 * Write a method lastIndexOf that accepts an integer value as a parameter and that returns the index 
	 * in the list of the last occurrence of that value, or -1 if the value is not found in the list. 
	 * For example, if a variable list stores the following sequence of values, then the call of 
	 * list.lastIndexOf(18) should return 6 because that is the index of the last occurrence of 18:
	 * [1, 18, 2, 7, 18, 39, 18, 40]
	 * If the call had instead been list.lastIndexOf(3), 
	 * the method would return -1 because 3 does not appear in the list. 
	 * You may not call any other methods of the class to solve this problem.
	 */
	
	public int lastIndexOf(int value) {
		ListNode current= front;
		int lastIndex = -1;
		int index= 0;
		
		while (current != null) {
			if (current.data == value) {
				lastIndex= index;
			}
			index++;
			current = current.next;
		}
		return lastIndex;
	}

	/**
	 * public int kthFromTheEnd(int index) (2 pt)
	 * Write a method kthFromTheEnd that accepts an index as a parameter and that returns the value of
	 * kth element from the end of the list.
	 * For example, if a variable list stores the following sequence of values, then 
	 * [1, 18, 2, 7, 18, 39, 18, 40]
	 * the call of list.kthFromTheEnd(3) should return 39 because that is the value of the 3rd element from the end 
	 * You may not call any other methods of the class to solve this problem.
	 */	
	public int kthFromTheEnd(int index) {
		//if index = 1, return last element
		//if index = list size, return first element
		ListNode current = front;
		ListNode k = front;
		int count = 1;
	
		while (current != null) {
			count++;
			current= current.next;
		} 
		if (index > count-1) {// if index doesn't exist
			throw new NoSuchElementException();
		} else {
			for (int i= 1; i < count - index; i++) {
				k=k.next;
			}
		}
		return k.data;
	}
	
	/*while (current != null) {//doesn't throw exception for indexes out of bounds
		if (index < count) {
			k= k.next;
		} 
		count++;
		current = current.next;
	}
	return k.data;

	/**
	 * public void rotateLeft() (2 pt)
	 * Write a method rotateLeft that moves the value at the front of a list of integers to the end of the list. 
	 * For example, if a variable called list stores the following sequence of values:
	 * [1, 2, 3, 6, 8, 10]
	 * list.rotateLeft() should move the value 1 from the front of the list to 
	 * the back of the list, yielding this sequence of values [2, 3, 6, 8, 10, 1]
	 * The other values in the list should retain the same order as in the original list. 
	 * If the method is called for a list of 0 or 1 elements it should have no effect on the list. 
	 * You are not allowed to construct any new nodes to solve this problem and 
	 * you are not allowed to change any of the integer values stored in the nodes. 
	 * You must solve the problem by rearranging the links of the list.
	 */
	public void rotateLeft() {
		ListNode current = front;
		
		if (current != null) {
			while (current.next != null) {
				current = current.next;
			}
			current.next = front;
			front = front.next;
			current.next.next = null;
			
		}
	}

	/**
	 * public void rotateRight() (2 pt)
	 * Write a method rotateRight that moves the value at the end of a list of integers to the front of the list. 
	 * For example, if a variable called list stores the following sequence of values:
	 * [1, 2, 3, 6, 8, 10]
	 * list.rotateRight() should move the value 10 from the back of the list to 
	 * the front of the list, yielding this sequence of values [10, 1, 2, 3, 6, 8]
	 * The other values in the list should retain the same order as in the original list. 
	 * If the method is called for a list of 0 or 1 elements it should have no effect on the list. 
	 * You are not allowed to construct any new nodes to solve this problem and 
	 * you are not allowed to change any of the integer values stored in the nodes. 
	 * You must solve the problem by rearranging the links of the list.
	 */
	public void rotateRight() {//10, 20, 30, 40-> 40, 10, 20, 30
		ListNode current = front; //= 10
		ListNode first = front;//= 10
		if (current != null && current.next != null) {
			while (current.next.next != null) {
				current = current.next;
			}// current = 30
			front = current.next; // = 40
			front.next = first; //= 10, 20, 30.. ,so list: 40, + 10, 20, 30, 40, 10...
			current.next = null; //list: 40, 10, 20, 30
		}	
			
	}
	/**
	 * public void removeDuplicates() (2 pt)
	 * Write a method removeDuplicates that removes the duplicates in a sorted list. 
	 * The list will be in sorted order, so all of the duplicates will be grouped together. 
	 * For example, if a variable list stores the sequence of values below,
	 * [1, 1, 1, 3, 3, 4, 5, 5, 9, 9, 12, 12, 12, 15]
	 * After removeDuplicates() is called:
	 * [1, 3, 4, 5, 9, 12, 15]
	 */
	public void removeDuplicates() {
		ListNode current = front;
		
		if (current != null) {
			while (current.next != null) {
				if (current.data == current.next.data) {
					//current = current.next.next; //not changing next field of current (not skipping past the next node)
					current.next = current.next.next;
				} else {
					current = current.next;
				}
			}
		}
	}

	/**
	 * public void reverse()  (2 pt)
	 * Write a method reverse that reverses the order of the elements in the list. 
	 * For example, if the variable list initially stores this sequence of integers:
	 * [1, 2, 5, 6, 8]
	 * After reverse is called:
	 * [8, 6, 5, 2, 1]
	 */
	public void reverse() { // 10, 20, 30, 40, -> 40, 30, 20, 10
		ListNode current = front; 
		ListNode prev = null; 
		ListNode next = null; 
		
		while (current != null) {
			next = current.next; 
			current.next = prev; 
			prev = current; 
			current = next; 
		}
		front = prev;
	}

	public static void main(String[] args) {
		LinkedIntList list = new LinkedIntList();
		
		
		list.add(10);
		//list.add(10);
		list.add(20);
		//list.add(20);
		//list.add(20);
		//list.add(20);
		list.add(30);
		list.add(40);//counts as sorted? yes--nondecreasing
		//list.add(40);
		//list.add(40);
		//list.add(40);
		
		
		
		/*System.out.println(list.contains(70));//false
		System.out.println(list.contains(10));//true
		System.out.println(list.contains(20));//true
		System.out.println(list.contains(30));//true
		System.out.println(list.contains(40));//true
		System.out.println(list.contains(80));//false
		*/
		
		/*list.set(0,5);
		list.set(1,10);
		list.set(2,15);
		list.set(3,20);
		list.set(4,25);//no 4th node exists to set the value to--this is how it should be done?
		*/
		
		//System.out.println(list.isSorted());
		
		//System.out.println("DeleteBack method: " + list.deleteBack());
		
		/*System.out.println(list.lastIndexOf(10));
		System.out.println(list.lastIndexOf(20));
		System.out.println(list.lastIndexOf(30));
		System.out.println(list.lastIndexOf(40));
		System.out.println(list.lastIndexOf(0));
		System.out.println(list.lastIndexOf(10));
		*/
		
		/*System.out.println(list.kthFromTheEnd(1));
		System.out.println(list.kthFromTheEnd(2));
		System.out.println(list.kthFromTheEnd(3));
		System.out.println(list.kthFromTheEnd(4));
		System.out.println(list.kthFromTheEnd(5));*/
		
		//Print and check the list
		list.print();
		
		/*list.rotateLeft();
		System.out.println();
		System.out.println("After rotateLeft: ");
		list.print();*/
		
		/*list.rotateRight();
		System.out.println();
		System.out.println("After rotateRight: ");
		list.print();*/
		
		/*list.removeDuplicates();
		System.out.println("After removeDuplicates: ");
		list.print();*/
		
		/*list.reverse();
		System.out.println("After reverse: ");
		list.print();*/
	}

}

