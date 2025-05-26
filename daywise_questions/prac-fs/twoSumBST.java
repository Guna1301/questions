
/*
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
} 
*/
import java.util.*;
class Solution {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if(root1==null || root2==null){
            return false;
        }
        int data = root1.val+root2.val;
        if(data==target){
            return true;
        }
        boolean ans = false;
        if(data>target){
                ans = ans | twoSumBSTs(root1.left,root2, target);
            
                ans = ans | twoSumBSTs(root1,root2.left, target);
            
        }else{
                ans = ans | twoSumBSTs(root1.right,root2, target);

                ans = ans | twoSumBSTs(root1,root2.right, target);
            
        }
        return ans;
        
    }   
    
}