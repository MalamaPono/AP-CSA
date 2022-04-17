
/**
 * Write a description of Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main {

    public static void main(String[] args){
        /*
        IntQueue q = new IntQueue();
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(6);
        
        q.showAll();
         
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        */
       
        PersonQueue q = new PersonQueue();
        Person p1 = new Person("koa","123");
        Person p2 = new Person("kocha","62763");
        q.enqueue(p1);
        q.enqueue(p2);
        
        q.showAll();
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    }
    
}
