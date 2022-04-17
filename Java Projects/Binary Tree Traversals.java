import java.util.*;
/*

All these tree traversals are only for binary trees which must have 2 children or less.


1) In order Traversal of a Tree (Depth-First search) Stack
    - Visit all of your left subtree
    - Visit yourself
    - Visit all of your right subtree
2) Pre - order traversal of a Tree (Depth-First search) Stack
    - Visit yourself
    - Visit all of your left subtree
    - Visit all of your right subtree
3) Post order traversal of a Tree (Depth-First search) Stack
    - Visit all of your left subtree
    - Visit all of your right subtree
    - Visit yourself
4) Level order traversal of a Tree (BFS breadth first search) Queue
    - Go in order of visiting each level by level of tree
    - Start from head and add children to a queue and each time remove the next thing from the front queue and add its left and right children to the back. Keep repeating this in a loop until queue is empty.
    - Once you have searched everything and the queue is empty your search is finished. Depending on the goal of your search and what you want to accomplish you 
                            can choose whether you want to add null children into the queue and visit them, or just simply only add non-null nodes into the queue.
                            
//Depending on what you are trying to do with your traversal you can define a function to do what you want when you "visit" a particular node. This could be as 
//simple as just prinintng out the value of the node you are at, or more complex like calculating 

*/

public class MyClass{
    public static void main(String[] args){
        
        
        
    }
    
    public static void visit(TreeNode node){
        //this visit function can do whatever you want it to when you traverse through each node in the binary tree, but for now we will just have the visit function print the nodes data value.
        System.out.println(node.data);
    }
    
    public static void inOrder(TreeNode root){
        
        if(root == null){
            return
        }
        
        inOrder(root.left);
        visit(root);
        inOrder(root.right);
        
    }
    
    public static void postOrder(TreeNode root){
        
        if(root == null){
            return
        }
        
        inOrder(root.left);
        inOrder(root.right);
        visit(root);
        
    }
    
    public static void pre-Order(TreeNode root){
        
        if(root == null){
            return;
        } 
      
        visit(root);
        inOrder(root.left);
        inOrder(root.right);
        
        
    }
    
    public static void levelOrder(TreeNode root){
        
        if(root == null){
            return;
        }
        
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        while(queue.size() > 0){
            TreeNode node = queue.remove(0);
            visit(node);
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
        
    }

}
