import org.junit.*;

import static org.junit.Assert.*;

import java.beans.Transient;

public class Testing{

    //List to be Tested
    SinglyLinkedList list = new SinglyLinkedList ("Tester");

    //Elements
    String class1, class2, class3, class4;
    
    @Before
    public void setUp(){

        class1 = "Data Structures";
        class2 = "Systems";
        class3 = "Technical Writting";
        class4 = "Computer Organization and Design";


        list.add(class1);
        list.add(class2);
        list.add(class3);
        list.add(class4);
    }


    @Test
    public void testSomething(){
    }

    @Test
    public void testSomethingElse(){
        
    }



}