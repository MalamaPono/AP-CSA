
/**
 * Write a description of Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main {
    public static void main(String[] args){
        
        Person p1 = new Person("umair",24);
        Person p2 = new Person("ali", 55);
        Person p3 = new Person("uzair",23);
        Person p4 = new Person("adam",34);
        Person p5 = new Person("john",18);
        
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(p1);
        bst.insert(p2);
        bst.insert(p3);
        bst.insert(p4);
        bst.insert(p5);

        bst.showAll(bst.findNode("umair"));
        
        Person p = bst.getData(bst.findParent("ali"));
        System.out.println(p.toString());
        
    }
}
