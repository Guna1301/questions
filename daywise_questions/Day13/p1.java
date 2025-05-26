package Day13;
/*
 * Imagine you are navigating a maze where each path you take has a section with a 
 code. The maze is structured as a series of interconnected rooms, 
 represented as a tree structure. Each room in the maze has a code (integral value)
 associated with it, and you are trying to check if a given sequence of code 
 matches a valid path from the entrance to an exit. 
 
 You are provide with the maze structure, where you have to build the maze and then,
 you are provided with a series of space seperated digits, representing a journey 
 starting from the entrance and passing through the rooms along the way. 
 The task is to verify whether the path corresponding to this array of codes 
 exists in the maze.
 
 Example 1:
 Input:
 0 1 0 0 1 0 -1 -1 1 0 0         //maze structure
 0 1 0 1                         //path to verify
 
 Output: true
 
 Explanation:
                0
              /   \
             1     0
            / \    /
           0   1  0
            \  / \
             1 0  0
 
 The given path 0 → 1 → 0 → 1 is a valid path in the maze. 
 Other valid sequences in the maze include 0 → 1 → 1 → 0 and 0 → 0 → 0.
 
 
 Example 2:
 Input:
 1 2 3
 1 2 3
 
 output: false
 
 Explanation:
 The proposed path 1 → 2 → 3 does not exist in the maze, 
 so it cannot be a valid path.
 
 */

import java.util.*;
public class p1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int[] arr = new int[s.length];
        for(int i=0; i<arr.length; i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        String[] p = sc.nextLine().split(" ");
        int [] path = new int[p.length];
        for(int i=0; i<path.length; i++){
            path[i] = Integer.parseInt(p[i]);
        }
        Node root = insert(arr);
        find(root, path, 0);
        System.out.println(b);
        
    }
    static boolean b;
    static void find(Node root, int[] path, int i){
        if(root==null){
            return;
        }
        if(i==path.length-1 && root.val == path[i]){
            b=true;
            return;
        }
        if(root.val == path[i]){
            if(root.left!=null && i<path.length && root.left.val == path[i+1]){
            find(root.left, path, i+1);
            }
            if(root.right!=null && i<path.length && root.right.val == path[i+1]){
                find(root.right, path, i+1);
            }
        }else{
            return;
        }
        
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