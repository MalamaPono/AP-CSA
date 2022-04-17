import java.util.*;

public class MyClass {
    public static void main(String[] args){
        
        
        String[] things = {"apples", "noobs", "pwnge", "bacon", "goATS"};
        List<String> list1 = new LinkedList<String>(); //linked list inherits from string so you can declare a linkedlist like this just like in classes with super classes and subclasses. For example: superclass named foods, and subclass that inherits from foods named apples. You can create a object for apples like this: foods nameHere = new apples(); This is because apples inherits from foods so it is okay to declare a new apples object through the foods class. 
        for(String thing: things){
            list1.add(thing);
        }
        
        String[] things2 = {"sausage", "bacon", "goats", "harrypotter"};
        List<String> list2 = new LinkedList<String>();
        for(String thing: things2){
            list2.add(thing);
        }
            
        list1.addAll(list2); //this method adds everything from list2 to list1
        list2 = null;
        
        printMe(list1);
        removeStuff(list1, 2,5);
        printMe(list1);
        reverseMe(list1);
}
        
        //printMe method
        private static void printMe(List<String> list){ //need to be static to you can just call the method instead of having to make an instance of the method.
            for(String x: list){
                System.out.printf("%s ", x);
            }
            System.out.println();
        }
        
        //removeStuff method
        private static void removeStuff(List<String> list, int from, int to){
            list.subList(from, to).clear(); //subList built in java method for Lists that will return the things in the list from a certain index to a certain index. Once we grab that sublist, we use the .clear built in method to remove that and be left with the rest of the LinekdList. 
            
        }
        //reverseMe method 
        private static void reverseMe(List<String> l){
            ListIterator<String> bob = l.listIterator(l.size());
            while(bob.hasPrevious()){
                System.out.printf("%s ", bob.previous());
            }
        }
        
}
