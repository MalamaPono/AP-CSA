
/**
 * Write a description of Person here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Person {
    
    private String name;
    private int age;
    
    public Person(String nm, int a){
        name = nm;
        age = a;
    }
    
    public String getName(){
        return name;
    }
    
    public int getAge(){
        return age;
    }
    
    public void setAge(int a){
        age = a;
    }
    
    public String toString(){
        return "\n >> Name: " + name + ",Age: " + age;
    }

}
