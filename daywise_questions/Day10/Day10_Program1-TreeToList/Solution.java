import java.util.*;
public class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int[] arr = new int[s.length];
        for(int i=0; i<s.length; i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        Node root = insert(arr);
        transfer(root);
        print(root);
    }
    static Node prev = null;
    static void print(Node root){
        if(root==null){
            return;
        }
        System.out.print(root.val+ " ");
        // print(root.left);
        print(root.right);
    }
    
    static void transfer(Node root){
        if(root==null){
            return;
        }
        transfer(root.right);
        transfer(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
        
        
    }
    
    static Node insert(int[] arr){
        Queue<Node> q = new LinkedList<>();
        Node root = new Node(arr[0]);
        int i = 1;
        q.add(root);
        while(!q.isEmpty() && i<arr.length){
            Node node = q.poll();
            if(arr[i]!=-1){
                node.left = new Node(arr[i]);
                q.add(root.left);
            }
            i++;
            if(i<arr.length && arr[i]!=-1){
                node.right = new Node(arr[i]);
                q.add(root.right);
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