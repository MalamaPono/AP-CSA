
/**
 * Write a description of LinkedList here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntLinkedList {
    
    private Node head;
    
    public IntLinkedList(){
        head = new Node();
        head.value = 0;
        head.link = null;
        
    }

    public boolean insertItem(int item){ //inserting node right after head
        /*
         * Inserting at beggining
        Node add = new Node();
        add.value = item;
        add.link = head;
        head = add;
        return true;
        */
        
        Node add = new Node();
        Node new_node = head;
        while(new_node.link != null){
            new_node = new_node.link; //after this, this node will contain final value in list
        }
        add.value = item;
        add.link = null;
        new_node.link = add;
        return true;
        /*
         * Inerting at the end
        
        
        
         */
        
    }
    
    public boolean deleteItem(int item){
        if(head.value == item){
            head = head.link;
            return true;
        }else{
            Node x = head;
            Node y = head.link;
            while(true){
                if(y == null || y.value == item){
                    break;
                }else{
                    x = y;
                    y = y.link;
                }
            }
            if(y!=null){
                 x.link = y.link;
                 return true;
            }else{
                 return false;
            }
            
        }
    }
    
    public void printList(){
        Node start = head.link;
        while(start!=null){
            System.out.println(start.value); 
            start = start.link;
        }
    }
    
    public void sortList(){
        int c = 0;
        Node a = head.link;
        while(a.link != null){
            Node b = head.link;
            while(b.link != null){
                if(b.value < b.link.value){
                    c = b.value;
                    b.value = b.link.value;
                    b.link.value = c;
                }
                b = b.link;
            }
            a = a.link;
        }
    }
    
    class Node{ //child class of intlinkedlist class
        private int value;
        private Node link;
    }

}
