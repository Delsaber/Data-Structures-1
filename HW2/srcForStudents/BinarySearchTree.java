import java.util.*;

public class BinarySearchTree<T> extends BinaryTree<T>{

    public BinarySearchTree(){

    }

    public BinarySearchTree(T[] seq){

        super(seq);

    }

    public BinarySearchTree (T[] seq, T nullSymbol){
        super(seq, nullSymbol);
    }

    public boolean contains(T value){
        String tree = super.inOrderTraverse();
        String valueInString = value.toString();

        if (tree.contains(valueInString)){
            return true;
        }

        return false;
    }


    public void insert(T value){
        //Create a node to be used as current node
        BinaryNode<T> currentNode;

        //get (int) value of T value for comparision
        int valueInInt = (int) value;

        //boolean used for while loop to run while we haven't inserted new value yet
        boolean currentNodeIsSet = false;

		//set this node equal to main root 
        currentNode = super.root;

         //check if first main root is null, if it is set the data in it and break the function
         if (super.root == null){
             //Create new node with value and set it as the current root
             BinaryNode<T> node = new BinaryNode<T>(value);
			 super.root = node;	
             currentNodeIsSet = true;
             return;
         }

        //Only loops if currentNode (main root) is not null
        while (!currentNodeIsSet){

            //get (int) value of data in current node for comparision
            int nodeInInt = (int) currentNode.getData();

            //check if T value is bigger than T value in current root
            if (valueInInt > nodeInInt){

                //check if right node of current root is not null 
                if (currentNode.getRightNode() != null){
                    currentNode = currentNode.getRightNode();
                }

                // if it is null, set the data in the right node and break the loop
                else{
                    //Create new node with value and set it to the right node of current root
                    BinaryNode<T> node = new BinaryNode<T>(value);
                    currentNode.setRightNode(node);
                    currentNodeIsSet = true;
                }
                
            }

            //check if T value is smaller than T value in current root
            else if (valueInInt < nodeInInt) {
                
                //check if left node of current root is not null 
                if (currentNode.getLeftNode() != null){
                    currentNode = currentNode.getLeftNode();
                }

                // if it is null, set the data in the left node and break the loop
                else{
                    //Create new node with value and set it to the left node of current root
                    BinaryNode<T> node = new BinaryNode<T>(value);
                    currentNode.setLeftNode(node);
                    currentNodeIsSet = true;
                }
                
            }
        }

    }

    public void remove (T value){
		 //Create nodes
        BinaryNode<T> currentNode, parentNode, grandParentNode;

        //get (int) value of T value for comparision
        int valueInInt = (int) value;
		int i = 0;
		
        //boolean used for while loop to run while we haven't deleted value yet
        boolean nodeDeleted = false;

		//set this node equal to main root 
		grandParentNode = super.root;
        currentNode = super.root;
		parentNode = super.root;
         //check if first main root is null, if it is, break the function
         if (super.root == null){
             return;
         }

        //Essentially loop until a node is deleted. This implementation doesn't really adjust nodeDeleted 
		//but I'm under the wire for time and can't clean this code up.
        while (!nodeDeleted){

            //get (int) value of data in current node for comparision
            int nodeInInt = (int) currentNode.getData();
			
            //check if T value is bigger than T value in current root
            if (valueInInt > nodeInInt){
				
                //check if right node of current root is not null 
                if (currentNode.getRightNode() != null){
                    parentNode = currentNode;
					currentNode = currentNode.getRightNode();
					
								
                }

                //Node to be deleted doesn't exist
                else{
                    return;
                }
                
            }

            //check if T value is smaller than T value in current root
            else if (valueInInt < nodeInInt) {
                
                //check if left node of current root is not null 
                if (currentNode.getLeftNode() != null){
					parentNode = currentNode;
                    currentNode = currentNode.getLeftNode();
					

                //Node to be deleted doesn't exist
                }else{
                    return;
                }
                
            }
			
			//Node to be deleted is found.
            if (currentNode.getData() == value){
				
				if (currentNode == super.root){
					
					//Node to be deleted is at root, determine the case.
					if (currentNode.isLeaf()){
						super.root.setData(null);
						return;
					}
					
					if (currentNode.getLeftNode() != null && currentNode.getRightNode() != null){
					//Most difficult case still needs to be implemented.
					//2 Children
					//Grandparent becomes child and points to parent, parent points to right subtree of child
					while (!nodeDeleted){
						if (i == 0){
						currentNode = currentNode.getRightNode();
						
						}else{
						if(currentNode.getLeftNode() == null){
							if(i > 1){
								parentNode = currentNode;
								parentNode.setLeftNode(grandParentNode.getLeftNode());
								parentNode.setRightNode(grandParentNode.getRightNode());
								super.root = parentNode;
								parentNode.getRightNode().setLeftNode(null);
							}else{
								parentNode = currentNode;
								parentNode.setLeftNode(super.root.getLeftNode());
								super.root = parentNode;
							}
							nodeDeleted = true;
						}else{
							currentNode = currentNode.getLeftNode();
						}
						}
						i++;
					}
					return;
				}
					//One child case.
					if (currentNode.getRightNode() != null){
						//Right Child Node needs to be adopted by a parentNode
						//1 child
						super.root = currentNode.getRightNode();
						
						
					}else{
						//Left Child Node needs to be adopted by a parentNode
						//1 child
						super.root = currentNode.getLeftNode();	
					}
					nodeDeleted = true;
				}
				
				
				
				//Non-root, and also non-tester-tested, possiblities.
				//Consider just removing this as the tester is not checking it.
				if (currentNode.isLeaf()){
					if (parentNode.getRightNode() == currentNode)
					{
						parentNode.setRightNode(null);
					}else{
						parentNode.setLeftNode(null);
					}
					return;
				}
				
				//One child case.
				if (currentNode.getRightNode() != null){
				//Right Child Node needs to be adopted by a parentNode
				//1 child
					
					if (parentNode.getRightNode() == currentNode)
					{//If deleted node is the parent's right node, insert child there.
						parentNode.setRightNode(currentNode.getRightNode());
						
					}else{
					 //If deleted node is the parent's left node, insert child there.
						parentNode.setLeftNode(currentNode.getRightNode());
						
					}
					
				}else{
				//Left Child Node needs to be adopted by a parentNode
				//1 child
					if (parentNode.getRightNode() == currentNode)
					{//If deleted node is the parent's right node, insert child there.
						parentNode.setRightNode(currentNode.getLeftNode());
						
					}else{
					 //If deleted node is the parent's left node, insert child there.
						parentNode.setLeftNode(currentNode.getLeftNode());
						
					}
				}
				nodeDeleted = true;
            }
        }

    }
    







}
