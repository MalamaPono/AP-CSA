package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		size = 0;
		head.next = tail;
		tail.prev = head;
		
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		
		if(element == null) {
			throw new NullPointerException();
		}
		
		add(size, element);
		return true;
		
		//THIS IS THE CODE IF YOU JUST ALWAYS WANTED TO SIMPLY ADD ELEMENTS TO THE 
		//BACK OF THE LIST. HOWEVER, SINCE WE ALREADY HAVE A METHOD THAT CAN ADD AT ANY 
		//VALID INDEX, WE CAN JUST UTILIZE THAT METHOD BY CALLING IT FROM HERE. 
		
//		LLNode<E> addition = new LLNode(element);
//		
//		
//		//if we are calling add on an empty list then tail.prev would just refer to the sentinel head node
//		//and we would simply add in this element between the head and tail sentinel nodes. Sentinel nodes
//		//aren't really there, but are just used to make life easier.
//		
//		LLNode<E> last = tail.prev;
//		last.next = addition;
//		addition.prev = last;
//		addition.next = tail;
//		tail.prev = addition;
//		size++;
//		
//		return true;
		
		
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> getIndex = head.next;
		for(int i = 0; i < index; i++) {
			getIndex = getIndex.next;
		}
		
		// TODO: Implement this method.
		return getIndex.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if(element == null) {
			throw new NullPointerException();
		}
		
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> addition = new LLNode<E>(element);
		LLNode<E> oldIndex = head.next;
		
		for(int i = 0; i < index; i++) {
			oldIndex = oldIndex.next;
		}
		
		LLNode<E> nodeBeforeOldIndex = oldIndex.prev;
		
		nodeBeforeOldIndex.next = addition;
		addition.prev = nodeBeforeOldIndex;
		addition.next = oldIndex;
		oldIndex.prev = addition;
		size++;
		
		// TODO: Implement this method
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		
		if(index < 0 || index >= size || size == 0) {
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> removeNode = head.next; 
		for(int i = 0; i < index; i++) {
			removeNode = removeNode.next;
		}
		
		LLNode<E> beforeRemoveNode = removeNode.prev;
		LLNode<E> afterRemoveNode = removeNode.next;
		
		beforeRemoveNode.next = afterRemoveNode;
		afterRemoveNode.prev = beforeRemoveNode;
		size--;
		
		return removeNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		
		if(element == null) {
			throw new NullPointerException();
		}
		
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> change = head.next;
		for(int i = 0; i < index; i++) {
			change = change.next;
		}
		
		E oldData = change.data;
		change.data = element;
		
		// TODO: Implement this method
		return oldData;
	}
	
	public String toString() {
		
		String ret = "";
		
		LLNode<E> start = head.next;
		for(int i = 0; i < size; i++) {
			ret += start.data.toString();
			start = start.next;
		}
	return ret;
	}
	
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
