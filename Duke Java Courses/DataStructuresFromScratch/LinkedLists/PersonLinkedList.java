
/**
 * Write a description of LinkedList here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PersonLinkedList {
    
    private Node head;
    
    public PersonLinkedList(Person person){
        head = new Node();
        head.person = person;
        head.link = null;
        
    }

    public boolean insertItem(Person item){ //inserting node right after head
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
        add.person = item;
        add.link = null;
        new_node.link = add;
        return true;
        /*
         * Inerting at the end
        
        
        
         */
        
    }
    
    public boolean deleteItem(String name){
        if(name.equals(head.person.getName())){
            head = head.link;
            return true;
        }else{
            Node x = head;
            Node y = head.link;
            while(y != null && !(y.person.getName().equals(name))){
                x = y;
                y = y.link;
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
        Node start = head;
        while(start!=null){
            System.out.println(start.person.toString()); 
            start = start.link;
        }
    }
    
    /*
     * This sort method per person can be definied in any way by you. By alphabetical order, length of strings, and more.
    public void sortList(){
        int c = 0;
        Node a = head.link;
        while(a.link != null){
            Node b = head.link;
            while(b.link != null){
                if(b.person < b.link.person){
                    c = b.person;
                    b.person = b.link.person;
                    b.link.person = c;
                }
                b = b.link;
            }
            a = a.link;
        }
    }
    */
    
    class Node{ //child class of intlinkedlist class
        private Person person;
        private Node link;
    }

}
