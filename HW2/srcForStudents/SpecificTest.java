import java.util.*;

public class SpecificTest{

    public static void main (String[] args){

		String nullSymbol = "0";
		String[] seq = {"A", "B", "C", "D", "0", "0", "G", "H", "I", "0", "0", "0", "0","0", "J"};
		BinaryTree<String> tree = new BinaryTree<String>(seq, nullSymbol);

        tree.width();
    }
}