/*
 * Imagine a company where each employee has a performance score, and 
 the organizational chart is structured as a binary tree with the CEO at the top. 
 An employee is considered "outstanding" if, along the chain of command from the 
 CEO down to that employee, no one has a performance score higher than that 
 employee's score. Your task is to determine the total number of outstanding 
 employees in the company.
 
 Example 1:
 Input: 3 1 4 3 -1 1 5
 Output: 4
 
 Chart formed:
          3
         / \
        1   4
       /   / \
      3   1   5
 
 Explanation:
 - The CEO (score 3) is automatically outstanding.
 - The employee with score 4, whose chain is [3,4], is outstanding because 4 
   is higher than 3.
 - The employee with score 5, following the path [3,4,5], is outstanding as 5 
   is the highest so far.
 - The subordinate with score 3, along the path [3,1,3], is outstanding because 
   none of the managers in that chain have a score exceeding 3.
 
 Example 2:
 Input: 3 3 -1 4 2
 Output: 3
 
 Chart formed:
        3
       / 
      3
     / \
    4   2
 
 Explanation:
 - The CEO (score 3) is outstanding.
 - The subordinate with score 3 (chain: [3,3]) is outstanding.
 - The employee with score 2 (chain: [3,3,2]) is not outstanding because the 
   managers have higher scores.
 
 */
import java.util.*;
public class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int[] arr = new int[s.length];
        for(int i=0; i<arr.length; i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        Node root = insert(arr);
        find(root, Integer.MIN_VALUE);
        System.out.println(ans);
        
    }
    
    static int ans = 0;
    static void find(Node root, int out){
        if(root==null){
            return;
        }
        if(root.val >= out){
            ans++;
            out = root.val;
        }
        find(root.left, out);
        find(root.right, out);
    }
    
    static Node insert(int[] arr){
        if(arr[0]==-1){
            return null;
        }
        Queue<Node> q = new LinkedList<>();
        Node root= new Node(arr[0]);
        q.add(root);
        int i = 1;
        while(!q.isEmpty() && i<arr.length){
            Node node = q.poll();
            if(arr[i]!=-1){
                node.left = new Node(arr[i]);
                q.add(node.left);
            }
            i++;
            if(i<arr.length && arr[i]!=-1){
                node.right = new Node(arr[i]);
                q.add(node.right);
            }
            i++;
        }
        return root;
    }
}
class Node{
    int val;
    Node left;
    Node right;
    Node(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }
}