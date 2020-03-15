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

        //set this node equal to main root 
        currentNode = super.root;

        //get (int) value of T value for comparision
        int valueInInt = (int) value;

        //boolean used for while loop to run while we haven't inserted new value yet
        boolean currentNodeIsSet = false;


        /**
         * This is what needs to be worked on in order to pass the testInsertToEmptyTree
        */

        // //check if first main root is null, if it is set the data in it and break the function
        // if (currentNode == null){
        //     //Create new node with value and set it to the right node of current root
        //     BinaryNode<T> node = new BinaryNode<T>(value);

        //     currentNodeIsSet = true;
        //     return;
        // }



        //Only loops if currentNode (main root) is not null
        while (!currentNodeIsSet){

            //get (int) value of data in current node for comparision
            int nodeInInt = (int) currentNode.getData();

            //check if T value is bigger than T value in current root
            if (valueInInt > nodeInInt){

                //do nothing and stop function if it's a duplicate value
                if (currentNode.getData() == value){
                    //do nothing
                    return;
                }

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

    }







}