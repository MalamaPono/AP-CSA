import java.util.*;
/*
1) Finding the max depth of a Binary Tree
*/

public class MyClass{
    public static void main(String[] args){
        
        
        
    }
    
    //Depth of a tree is highest at the leafs(at the bottom of the tree) and height is smallest here as well
    //Height of the tree is highest at the root (towards the top of the tree) and depth is the smallest here as well
    
    //will need to define a class, or use Java built in TreeNode class to represent a node in the Binary Tree which holds a data value, a left pointer, and a right pointer.
    public static int maxDepth(TreeNode root){
        
        if(root == null){
            return 0;-=
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left,right) + 1;
        
        //instead of using the Math.max function you can implement your own max function as well
        /*
        
        if(left > right){
            return left+1;
        }else if (right > left){
            return right+1;
        }else{
            return left+1;
        }
        
        */
        
        
    }

}
