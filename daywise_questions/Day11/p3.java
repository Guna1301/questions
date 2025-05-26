package Day11;
/*
 * Imagine you are designing a network of secret corridors in an ancient castle. 
 Each chamber in the castle leads to at most two other chambers through 
 hidden passageways, forming a branching layout. 
 The castle’s "longest secret route" is defined as the maximum number of corridors 
 you must traverse to get from one chamber to another (without repeating the corridor). 
 This route may or may not pass through the main entry chamber.
 
 Your task is to compute the length of longest secret route between 
 two chambers which is represented by the number of corridors between them.
 
 Example 1
 input=
 1 2 3 4 5 
 output=
 3
 
 Structure:
        1
       / \
      2   3
     / \
    4   5
 
 Explanation:
 The longest secret route from chamber 4 to chamber 3 
 (alternatively, from chamber 5 to chamber 3) along the route:
 4 → 2 → 1 → 3
 This path has 3 corridors (4–2, 2–1, 1–3), so the length is 3.
 
 Example 2:
 input=
 1 -1 2 3 4
 output=
 2
 
 Structure:
    1
     \
      2
     / \
    3   4
 
 Explanation:
 The longest secret route from chamber 3 to chamber 4 
 (alternatively, from chamber 1 to chamber 4) along the route:
 3 → 2 → 4
 This path has 2 corridors (3–2, 2–4), so the length is 2.
 
 
 
 */

import java.util.*;
public class p3{
    static int maxi = Integer.MIN_VALUE; 
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int[] arr = new int[s.length];
        for(int i=0; i<arr.length; i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        Node root = insert(arr);
        find(root);
        System.out.println(maxi);
        sc.close();
    }
    static int find(Node root){
        if(root==null){
            return 0;
        }
        int l = find(root.left);
        int r = find(root.right);
        maxi = Math.max(l+r, maxi);
        return Math.max(l,r)+1;
        
    }
    static Node insert(int[] arr){
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