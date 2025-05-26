/*
 * Construct Tree from the given Level-order and In-order.
 Compute the Depth and Sum of the Deepest nodes in the Binary tree
 
 Input Format:
 -------------
 An integer N representing the number of nodes.
 A space-separated list of N integers representing the in-order traversal.
 A space-separated list of N integers representing the level-order traversal.
 
 Output Format:
 --------------
 Print two values:
 ->The maximum number of levels.
 ->The sum of all node values at the deepest level.
 
 Example:
 -------------
 Input:
 11
 7 8 4 2 5 9 11 10 1 6 3
 1 2 3 4 5 6 7 9 8 10 11
 
 Output:
 6 11
 
 Explanation:
 The binary tree structure:
            1
          /   \
        2       3
       / \     /
      4   5   6
     /     \   
    7       9
     \       \
      8      10
             /
           11
 Maximum Depth: 6
 nodes at the Deepest Level (6): 11
 Sum of nodes at Level 6: 11
 
 */
import java.util.*;
 class Solution{
     public static void main(String[] args){
         Scanner sc = new Scanner(System.in);
         int n = sc.nextInt();
         int[] inorder = new int[n];
         int[] level = new int[n];
         for(int i=0; i<n; i++){
             inorder[i] = sc.nextInt();
         }
         for(int i=0; i<n; i++){
             level[i] = sc.nextInt();
         }
         HashMap<Integer,Integer> hm = new HashMap<>();
         for(int i=0; i<inorder.length; i++){
             hm.put(num, i);
         }
         Node root = build(inorder,level, hm);
     }
     static Node build(int[] inorder, int[] level, HashMap<Integer, Integer> hm){
         Queue<Node> q = new LinkedList<>();
         Node root = new Node(level[0]);
         q.add(root);
         int index = 1;
         while(!q.isEmpty() && index<inorder.length){
             Node node = q.poll();
             int pos = hm.get(node.val);
             int leftindex = -1, rightindex=-1;
             int curr = node.val;
             int index = 0;
             for(int i=0; i<inorder.length; i++){
                 if(inorder[i]==curr){
                     index = i;
                     break;
                 }
             }
             if(index<pos){
                 leftindex = index;
             }
             if(index>pos){
                 rightindex = index;
             }
             if(leftindex!=-1){
                 root.left = new Node(inorder[leftindex]);
                 q.add(root.left);
             }
             if(rightindex!=-1){
                 root.right = new Node(inorder[rightindex]);
                 q.add(root.right);
             }
         }
     }
 }
 class Node{
     int val;
     Node left;
     Node right;
     Node(int val){
         this.val =val;
         this.left = null;
         this.right = null;
     }
 }