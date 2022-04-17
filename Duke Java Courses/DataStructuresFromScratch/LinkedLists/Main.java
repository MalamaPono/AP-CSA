
/**
 * Write a description of Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main {

    public static void main(String[] args){
        /*
        IntLinkedList list = new IntLinkedList();
        System.out.println("******");
        list.insertItem(5);
        list.insertItem(2);
        list.insertItem(5);
        list.insertItem(8);
        list.printList();
        list.sortList();
        list.printList();
        */
        
        Person p1 = new Person("kocha","123");
        Person p2 = new Person("kocskssa","1234787");
        
        PersonLinkedList list = new PersonLinkedList(p1);
        list.insertItem(p2);
        list.printList();
       
    }
    
}
