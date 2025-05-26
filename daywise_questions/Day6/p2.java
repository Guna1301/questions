/*
 * Balbir Singh is working with Binary Trees.
 The elements of the tree are given in level-order format.
 
 Balbir is observing the tree from the right side, meaning he 
 can only see the rightmost nodes (one node per level).
 
 You are given the root of a binary tree. Your task is to determine 
 the nodes visible from the right side and return them in top-to-bottom order.
 
 Input Format:
 -------------
 Space separated integers, elements of the tree.
 
 Output Format:
 --------------
 A list of integers representing the node values visible from the right side
 
 
 Sample Input-1:
 ---------------
 1 2 3 5 -1 -1 5
 
 Sample Output-1:
 ----------------
 [1, 3, 5]
 
 
 
 Sample Input-2:
 ---------------
 3 2 4 3 2
 
 Sample Output-2:
 ----------------
 [3, 4, 2]
 
 */
import java.util.*;
class p2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int[] arr = new int[s.length];
        for(int i=0; i<s.length; i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        Node root = insert(arr);
        List<Integer> ans = new ArrayList<>();
        find(root,0,ans);
        System.out.println(ans);
    }
    static void findd(Node root, List<Integer> ans){
        if(root==null){
            return;
        }
        Queue<Node> q= new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            int value = 0;
            for(int i=0; i<size; i++){
                Node node = q.poll();
                if(node.left !=null && node.left.val!=-1){
                    q.add(node.left);
                }
                if(node.right!=null && node.right.val!=-1){
                    q.add(node.right);
                }
                value = node.val;
            }
            ans.add(value);
        }
    }
    static void find(Node root, int d, List<Integer> ans){
        if(root==null){
            return;
        }
        if(d==ans.size()) ans.add(root.val);
        find(root.right, d+1, ans);
        find(root.left, d+1, ans);
    }
    static Node insert(int[] arr){
        Queue<Node> q= new LinkedList<>();
        Node root = new Node(arr[0]);
        q.add(root);
        int i=1;
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
        this.left = left;
        this.right = right;
    }
}