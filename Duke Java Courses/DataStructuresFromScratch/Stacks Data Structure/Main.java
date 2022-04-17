
/**
 * Write a description of Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main {
    
    public static void main(String[] args){
       
        IntStack stack = new IntStack();
        if(!stack.isFull()){
            stack.push(2);
            stack.push(4);
            stack.push(6);
        }
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        
        
        /*
        Person p1 = new Person("koa","123");
        Person p2 = new Person("kocha","62763");
        
        PersonStack stack = new PersonStack();
        stack.push(p1);
        stack.push(p2);
        System.out.println(stack.pop().toString());
        System.out.println(stack.pop().toString());
        */
    }

}
