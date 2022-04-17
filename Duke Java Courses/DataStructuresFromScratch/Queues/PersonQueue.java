
/**
 * Write a description of Queue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PersonQueue {
    
    private Person[] q;
    private int size;
    private int total;
    private int front;
    private int rear;
    
    public PersonQueue(){
        size = 100;
        total = 0;
        front = 0;
        rear = 0;
        q = new Person[size];
    }
    
    public PersonQueue(int size){
        this.size = 100;
        total = 0;
        front = 0;
        rear = 0;
        q = new Person[this.size];
    }
    
    public boolean enqueue(Person item){
        if(isFull()){
            return false;
        }else{
            total++;
            q[rear] = item;
            rear = (rear+1) % size; //this just makes sure you go back to first/0 index after going and dequeuing entire queue
            return true;
        }
    }
    
    public Person dequeue(){
        Person item = q[front];
        total--;
        front = (front + 1) % size; //this just makes sure you go back to first/0 index after going and dequeuing entire queue
        return item;
    }
    
    public boolean isFull(){
        if(size == total){
            return true;
        }else{
            return false; 
        }
    }
    
    public boolean isEmpty(){
        if(total == 0){
            return true;
        }else{
            return false;
        }
    }
    
    public void showAll(){
        int f = front;
        if(!isEmpty()){
            for(int i = 0; i < total; i++){
                System.out.println(q[f].toString());
                f = (f+1) % size;
            }
        }
    }

}
