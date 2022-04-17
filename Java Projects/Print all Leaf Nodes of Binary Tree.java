import java.util.*;

/*

Print out all leaf nodes of a binary tree
Leaf Node - a node with no decendents

*/


public class MyClass{
    public static void main(String[] args){
        
    }
    
    public static void printAllLeafs(TreeNode root){
        if(root == null){
            return;
        }
        
        if(root.right == null && root.left == null){
            System.out.println(root.data);
        }else{
            printAllLeafs(root.left);
            printAllLeafs(root.right);
        }
        
        
    }
    
}

