import java.util.*;

/**
* HW5
* class LinkedList Which is a reimplementation of Java's LinkedList class
* inner class Node Which is a reimplementation of Java's Node class
* inner class Iterator Which is a reimplementation of Java's Iterator class

* @author Ricardo Garay, Luis Cenci Ribeiro 
* @version February 14, 2020 
*/

public class SinglyLinkedList<T> implements Iterable<T> {

	private String listName;
	private Node head;
	private Node tail;
	private int size;
	
	public SinglyLinkedList(String listName) {
		this.listName = listName;
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	/**
    * Add method which will add a node to the end of the LinkedList
    * @param    T               element
    * @custom.require       Element must be the same type as the LinkedList
    */

    public void add(T element) {

        Node node = new Node (element);

        if (size == 0) {
            this.head = node;
            this.tail = node;
            
        }
        else {
             
            this.tail.setNextNode(node);
            this.tail = node;
        }

        size++;
    }

	
	/**
	* Adds an element to the list at a specified index.
	*
	* @param 	int 	index
	* @param	int 	element
	*/
	public void insertAt(T element, int index) {
		
		int counter = 0;
        Node newNode = new Node(element);
        Node current = this.head;

        if (index >= size){
            add(element);
            return;
        }

        if (index == 0){
            newNode.setNextNode(current);
            this.head = newNode;
            this.size++;
            return;
        }

        while (current != null ) {
            if (counter == index - 1)
                break;
            current = current.getNextNode();
            counter++;
        }
        if (current.getNextNode().data == null){
            this.tail = newNode;
        }
        newNode.setNextNode(current.getNextNode());
        current.setNextNode(newNode);
        this.size++;
    }


	/**
	* Removes an element from the list.
	*
	* @param	T	element
	*/
	public void remove(T element) {
		Node current = this.head;
		int counter = 0;
		if (this.head != null && this.head.data == element) {
			this.head = this.head.next;
			this.size--;
			return;
		} else {
			while(current != null && current.data != element) {
				current = current.next;
				counter++;
            }
            
			//If element was found, then it is at the current node.  Remove it.
			if (current != null) {
				current = this.head;
				for (int i = 0; i < (counter - 1); i++){
					current = current.next;
				}
				current.next = current.next.next;
				this.size--;
				return;
            }

			//If element was not found.
			if (current == null){
                throw new NoSuchElementException("Element not found.");
            }

		} 		
	}
	
	public void clear() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	public boolean isEmpty() {
		if (this.size == 0){
			return true;
		}
		return false;
	}
	
	/**
    * Size method which will return the size of the LinkedList
    * @return   int             size
    */
    public int size() {
        return this.size;
    }
	
	public T getNthFromFirst(int n) {
		if (n > this.size - 1){
			throw new IndexOutOfBoundsException("Index too large");
		}
		
        int counter = 0;
        Node current = this.head;
        while (current != null ) {
            if (counter == n){
                return current.getData();
            }

            current = current.getNextNode();
            counter++;
        }

        return null;
    }
	
	
    public T getNthFromLast(int n) { 
	
	if (n > this.size - 1){ 
		throw new IndexOutOfBoundsException("Index too large");
	}
        int counter = 0;
	int nFromFirst = this.size - n;
		
        Node current = this.head;
        while (current != null ) {
            if (counter == nFromFirst){
                return current.getData();
            }
            current = current.getNextNode();
            counter++;
        }

        return null;
    }

	/**
    * GetIterator method which will return an iterator for the list calling this method.
    * @return   Iterator<T>     Iterator<T>(this)
    *
    */
    
    public SinglyLinkedListIterator iterator() {

        SinglyLinkedListIterator newIterator = new SinglyLinkedListIterator(this);

        return newIterator;
    }
	
	@Override
	public String toString() {
		String list = "LinkedList: ";
		Node current = this.head;  
   
        while (current != null) {  
            list = list + current.data + " "; 
            current = current.next; 
        }
		return list + "\n";
	}
    
    // INNDER CLASS NODE

	class Node {
    
        private T data;
        private Node next;
        
        public Node(T data) {
            this.next = null;
            this.data = data;
        }
    
        /**
        * Method which will return this node's data
        * @return   T       this.data
        */
        public T getData() {
            return this.data;
        }
    
        /**
        * Method which will return this node's next node
        * @return   Node        itsNext
        */
        public Node getNextNode() {
            return this.next;
        }

        /**
        * Method which will set this node's next pointer to specified node "next"
        * @param   Node         next
        */
        public void setNextNode(Node next) {
            this.next = next;
        }

        public String toString() {
            return data.toString();
        }
    
    }  // end of Node

    class SinglyLinkedListIterator implements Iterator<T>{


        SinglyLinkedList<T> list;
        Node currentNode;

        public SinglyLinkedListIterator (SinglyLinkedList<T> list){
            this.list = list;
            currentNode = list.head;

        }

        public boolean hasNext(){

            if (currentNode != null){
                return true;
            }

            return false;
        }

        public T next(){

            if (!this.hasNext()){
                throw new NoSuchElementException();
            }

            T data = currentNode.getData();
            currentNode = currentNode.getNextNode();
            return data;

        }

        public void remove(){



            throw new UnsupportedOperationException("Remove operation is not supported by this iterator");
        }

    }

}


   

