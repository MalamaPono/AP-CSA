/*

Inverting Binary Tree, flipping it over its body, essentially flipping every single left and right subtree of every single node in the binary tree 

*/

public class MyClass {
    public static void main(String args[]) {
        
        
        
    }
    
    //if want to return the root of the newly inverted binary tree, which will be the same root, now just a reference to a inverted tree.
    public static TreeNode invertBinary(TreeNode root){
        if(root == null){
            return;
        }
        
        TreeNode left = invertBinary(root.left);
        TreeNode right = invertBinary(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
    
    //if just want to return nothing and have that same root node be a reference to a tree which is inverted compared to the original tree.
    public static void invertBinary(TreeNode root){
        
        if(root == null){
            return;
        }
        
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;
        invertBinrary(root.left);
        invertBinary(root.right);
    }
    
}