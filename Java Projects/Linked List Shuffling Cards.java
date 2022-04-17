import java.util.LinkedList;
import java.util.ArrayList;
public class MyClass{
    public static void main(String[] args){
        
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        
        shuffleList(list);
        System.out.println(list);
        
        
    }
    
    //if they give a reference to the LinkedList
    public static void shuffleList(LinkedList<Integer> list){
        int halfIndex = list.size()/2-1;
        ArrayList<Integer> order = new ArrayList<Integer>();
        for(int i = 0; i <= halfIndex; i++){
            order.add(list.get(halfIndex+i+1));
            order.add(list.get(i));
        }
        
        for(int j = 0; j < list.size(); j++){
            list.set(j,order.get(j));
        }
        
    }
    
    //if they give only the head element of the LinkedList you will need to use Linked list properties of data, next, and prev(if you are a doubly linked list)
    //to rearange the nodes into the shuffled deck pattern and then return the head of the new rearranged linked list so that the user can refer to the head of the linked
    //list and essentially have the power and use of the entire linked list. The tail of a linked list is basically the last node in the Linked list who has a next pointer of null.
    //When given only the head node of the linked list, when we are rearanging nodes, we must keep in mind the references and pointers to make sure we don't lose any nodes and we actually
    //get it in the right order.
    
}
