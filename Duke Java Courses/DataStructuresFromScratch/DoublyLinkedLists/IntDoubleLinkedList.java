
/**
 * Write a description of LinkedList here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntDoubleLinkedList {
    
    private Node head;
    //can also make tail node to insert items at end
    
    public IntDoubleLinkedList(int item){
        head = new Node();
        head.value = item;
        head.next = null;
        head.prev = null;
        
    }

    public boolean insertItem(int item){ //inserting node right after head
        Node n = new Node();
        n.value = item;
        n.prev = null;
        head.prev = n;
        n.next = head;
        head = n;
        return true;
    }
    
    public boolean deleteItem(int item){
        if(head.value == item){
            head = head.next;
            return true;
        }else{
            Node x = head;
            Node y = head.next;
            while(true){
                if(y == null || y.value == item){
                    break;
                }else{
                    x = y;
                    y = y.next;
                }
            }
            if(y!=null){
                 x.next = y.next;
                 return true;
            }else{
                 return false;
            }
            
        }
    }
    
    public void printList(){
        Node start = head;
        while(start!=null){
            System.out.println(start.value); 
            start = start.next;
        }
    }
    
    class Node{ //child class of intlinkedlist class
        private Node prev;
        private int value;
        private Node next;
    }

}
