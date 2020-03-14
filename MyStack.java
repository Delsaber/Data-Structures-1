/**
* A class that models a Stack using our own SinglyLinkedList.
*
* @name		Luis Cenci Ribeiro / Ricardo Garay
* @version	02/13/2020
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
		
        myStack.insertAt(item,0);
	}
	
    public boolean isEmpty(){
        
        return myStack.isEmpty();
    }

    public String infixToPostfix(String infix){
		
		MyStack opStack = new MyStack();
	    
        String postFix = "";
        int length = infix.length();
        char c;
		char existingOp = 'x';
	
        for (int i = 0; i < length; i++){

            c = infix.charAt(i);
			
			//If c is a digit or letter, post to output
            if (Character.isLetter(c) || Character.isDigit(c)){
                postFix = postFix + c;
            }
			
			//Otherwise, if it's + or - do this stuff, push op when done.
            else if (c == '+' || c == '-'){
				while (!opStack.isEmpty()){
					if (existingOp == '('){
						break;
					}else{
						postFix = postFix + (char)opStack.pop();
						if (!opStack.isEmpty()){
							existingOp = (char)opStack.pop();
							opStack.push(existingOp);
						}
					}
				}
				
			opStack.push(c);
		    existingOp = c;
			}
			
			//Otherwise, if it's * or / do this stuff, push op when done.
			else if (c == '*' || c == '/'){
				while (!opStack.isEmpty()){
					if (existingOp == '*' || existingOp == '/'){
						postFix = postFix + (char)opStack.pop();
						if (!opStack.isEmpty()){
							existingOp = (char)opStack.pop();
							opStack.push(existingOp);
						}
					} else {
						break;
					}
				}
				
			opStack.push(c);
		    existingOp = c;
			}
			
			//Otherwise, if it's (, push it and make it the existing op.
			else if (c == '('){
				opStack.push(c);
				existingOp = c;
			}
			
			/*Otherwise, and hopefully the only option barring some user input errors
			  do this stuff. Popping until ( is found.*/
			else if (c == ')'){
				while (!opStack.isEmpty() && existingOp != '('){
					postFix = postFix + (char)opStack.pop();
					if (!opStack.isEmpty()){
						existingOp = (char)opStack.pop();
						opStack.push(existingOp);
					}
				}
				
				/*If stack is not empty, existingOp must be '('
				  Therefore, make existingOp the next item in stack if not empty.*/	
				if (!opStack.isEmpty()){
					existingOp = (char)opStack.pop(); // gets rid of the Open Paranthesis
					existingOp = (char)opStack.pop();
					opStack.push(existingOp);
				}
			}
        }
		
		//Now string has been read, pop remaining ops in the stack.
		while (!opStack.isEmpty()){
			postFix = postFix + (char)opStack.pop();
		}

		//Hopefully return the correct expression.
        return postFix;
    }
}
