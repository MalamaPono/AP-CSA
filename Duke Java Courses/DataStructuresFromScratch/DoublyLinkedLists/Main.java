
/**
 * Write a description of Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main {
    public static void main(String[] args){
        IntDoubleLinkedList list = new IntDoubleLinkedList(2);
        list.insertItem(3);
        list.insertItem(6);
        list.printList();
        System.out.println();
        list.deleteItem(3);
        list.printList();
    }
}
