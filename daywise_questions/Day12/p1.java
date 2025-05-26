/*
 * Imagine you’re decoding a secret message that outlines the hierarchical structure 
 of a covert spy network. The message is a string composed of numbers and parentheses. 
 Here’s how the code works:
 
 - The string always starts with an agent’s identification number, this is the 
   leader of the network.
 - After the leader’s ID, there can be zero, one, or two segments enclosed in 
   parentheses. Each segment represents the complete structure of one subordinate 
   network.
 - If two subordinate networks are present, the one enclosed in the first (leftmost) 
   pair of parentheses represents the left branch, and the second (rightmost) 
   represents the right branch.
 
 Your mission is to reconstruct the entire spy network hierarchy based on this 
 coded message.
 
 Example 1:
 Input: 4(2(3)(1))(6(5))
 Output: [4, 2, 6, 3, 1, 5]
 
 Spy network:
         4
        / \
       2   6
      / \  /
     3   1 5
 
 Explanation:
 Agent 4 is the leader.
 Agent 2 (with its own subordinates 3 and 1) is the left branch.
 Agent 6 (with subordinate 5) is the right branch.
 
 Example 2:
 Input: 4(2(3)(1))(6(5)(7))
 Output: [4, 2, 6, 3, 1, 5, 7]
 
 Spy network:
          4
        /   \
       2     6
      / \   / \
     3   1 5   7
 
 Explanation:
 Agent 4 leads the network.
 Agent 2 with subordinates 3 and 1 forms the left branch.
 Agent 6 with subordinates 5 and 7 forms the right branch.
 
 */

 import java.util.*;
 public class p1{
     public static void main(String[] args){
         Scanner sc = new Scanner(System.in);
         String s = sc.nextLine();
         Node root = build(s, 0, s.length()-1);
         List<Integer> ans = new ArrayList<>(); 
         levelorder(root, ans);
         System.out.println(ans);
     }
     
     static void levelorder(Node root, List<Integer> ans){
         Queue<Node> q = new LinkedList<>();
         q.add(root);
         while(!q.isEmpty()){
             int size = q.size();
             for(int i= 0 ; i<size; i++){
                 Node node = q.poll();
                 if(node.left!=null){
                     q.add(node.left);
                 }
                 if(node.right!=null){
                     q.add(node.right);
                 }
                 ans.add(node.val);
             }
         }
     }
     
     static Node build(String s, int l, int r){
         if(l>r){
             return null;
         }
         int index = l;
         boolean isNeg = false;
         if(s.charAt(index)=='-'){
             isNeg = true;
             index++;
         }
         while(index<=r && Character.isDigit(s.charAt(index))){
             index++;
         }
         int v = Integer.parseInt(s.substring(l,index));
         if(isNeg){
             v=-1*v;
         }
         Node root = new Node(v);
         if(index<=r && s.charAt(index)=='('){
             int newr = findRight(s, index);
             root.left = build(s, index+1, newr-1);
             if(newr+2<=r){
                 root.right = build(s, newr+2, r-1);
             }
             
         }
         
         return root;
     }
     static int findRight(String s, int l){
         Stack<Character> st = new Stack<>();
         for(int i = l; i<s.length(); i++){
             if(s.charAt(i)=='('){
                 st.push('(');
             }
             else if(s.charAt(i)==')'){
                 st.pop();
                 if(st.isEmpty()){
                     return i;
                 }
             }
             
         }
         return -1;
     }
 }
 class Node{
     int val;
     Node left;
     Node right;
     Node(int val){
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }

 //do it using single stack<Node> where the current number if the stack.peek has left then make it as right else make it as left.
//when encounter ) then pop. just look at the ending bracket than the open bracket