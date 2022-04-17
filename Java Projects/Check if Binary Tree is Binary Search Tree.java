import java.util.*


/*
Write a method in Java to check if a binary tree is a binary search tree or not
The idea behind this is to do an inOrder traversal which will give the nodes in the binary tree in proper order. If it is a binary Search Tree, then the inOrder traversal
should list the nodes from least to greatest. If the inOrder traversal doesn't give the values in least to greatest order, than we know that the binary tree is not
in order and thus is not a proper Binary Search Tree.
*/

public class MyClass{
    public static void main(String[] args){
        
    }
    
    
    public static void inOrder(TreeNode node, ArrayList<Integer> num){
        
        if(node == null){
            return;
        }
        
        
        //stores the value of each node in order into the ArrayList that is passed it. Depending on what the type of data Node stores, whether it be a String, Integer,
        //or Double we can change the type of object that the ArrayList stores, and this code will work exactly the same.
        inOrder(node.left);
        num.add(node.data);
        inOrder(node.right);
        
    }
    
    
    public static boolean isbinarySearch(TreeNode root){
        
        if(root == null){
            return false;
        }
        
        ArrayList<Integer> num = new ArrayList<Integer>();
        
        inOrder(root,num);
        
        int min = num[0];
        for(int x : num){
            if(x < min){
                return false;
            }
        }
        
        return true;
        
    }
    
}
