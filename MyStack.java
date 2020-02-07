/**
* A class that models a Stack using our own SinglyLinkedList.
*
* @name		Luis Cenci Ribeiro / Ricardo Garya
* @version	02/06/2020
*/

import java.util.*;

public class MyStack<T> {

    private SinglyLinkedList<T> myStack;
	
	public MyStack() {
		myStack = new SinglyLinkedList<T>("My Stack");
	}
    
    /**
	* Pops off the item on the top of the stack.
	*
	* @return	T 	
	*/
	public T pop() {
        
        if (myStack.isEmpty()){
            throw new NoSuchElementException("Stack is empty, cannot pop!");
        }

        T temporary = myStack.getNthFromFirst(0);

        myStack.remove(myStack.getNthFromFirst(0));
        
        return temporary;
	}

	/**
	* Pushes an item to the top of the stack.
	*
	* @param	T 	item
    */
    
	public void push(T item) {
		
        myStack.add(item);
	}
	
    public boolean isEmpty(){
        
        return myStack.isEmpty();
    }

    public String infixToPostfix(String infix){

        String postFix = "";
        int length = infix.length();
        char c;

        for (int i = 0; i < length; i++){

            c = infix.charAt(i);

            if (Character.isLetter(c)){
                postFix = postFix + c;
            }

            else if (c == '+' || c == '-'){

                if (this.isEmpty()){
                    
                    this.push(5);
                }
            }

        }


        return postFix;
    }
}
