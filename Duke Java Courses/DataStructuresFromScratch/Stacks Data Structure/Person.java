
/**
 * Write a description of Person here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Person {
    
    private String name;
    private String rollNumber;
    
    public Person(String name, String rollNumber){
        this.name = name;
        this.rollNumber = rollNumber;
    }
    
    public String toString(){
        return "Name: " + this.name + " roll number: " + this.rollNumber;
    }

}
