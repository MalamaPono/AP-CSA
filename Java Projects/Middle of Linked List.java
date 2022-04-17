import java.util.*;
/*
1) How do you find middle element of a linked list in a single pass?
*/

public class MyClass{
    public static void main(String[] args){
        
        
        
    }
    
    public static Node returnMiddle(Node head){
        int size = 0;
        
        if(head == null){
            return head;
        }
        
        Node temp = head;
        
        while(temp != null){
            temp = temp.next;
            size++;
        }
        
        int mid = size/2;
        
        Node mid = head
        for(int i = 0; i < mid; i++){
            mid = mid.next;
        }
        
        return mid;
        
    }

}
