
/**
 * Write a description of Student here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Student {
    
    private String name;
    private String roll_no;
    private double cgpa;
    
    public Student(String name, String roll_no, double cgpa){
        this.name = name;
        this.roll_no = roll_no;
        this.cgpa = cgpa;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setRollno(String roll_no){
        this.roll_no = roll_no;
    }
    
    public String getRollno(){
        return roll_no;
    }
    
    public void setCGPA(double cgpa){
        this.cgpa = cgpa;
    }
    
    public double getCGPA(){
        return cgpa;
    }
    
    public String toString(){
        return "Name: " + name + " Roll no: " + roll_no + " CGPA: " + cgpa;
    }

}
