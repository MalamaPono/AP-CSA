
import java.util.*;
/*
1) How do you find the 3rd element from last in a single pass?
*/

public class MyClass{
    public static void main(String[] args){
        
        
        
    }
    
    
    public Node thirdElementFromLast(Node head){
        
        if(head == null){
            return null;
        }
        
        int size = 0;
        //find size of linked list
        
        if(size < 4){
            return null;
        }
        
        
        Node temp = head;
        while(temp != null){
            temp = temp.next;
            size++;
        }
        
        int thirdFromLast = size - 4;
        Node answer = head;
        for(int i = 0; i < thirdFromLast; i++){
            answer = answer.next;
        }
        
        return answer;
        
    }

}
