package Day2;

import java.util.*;

/*Write a program to construct a binary tree from level-order input, while treating -1 
as a placeholder for missing nodes. The program reads input, constructs the tree, 
and provides an in-order traversal to verify correctness.

Input Format:
---------------
Space separated integers, level order data (where -1 indiactes null node).

Output Format:
-----------------
Print the in-order data of the tree.


Sample Input:
----------------
1 2 3 -1 -1 4 5

Sample Output:
----------------
2 1 4 3 5

Explanation:
--------------
    1
   / \
  2   3
     / \
    4   5


Sample Input:
----------------
1 2 3 4 5 6 7

Sample Output:
----------------
4 2 5 1 6 3 7

Explanation:
--------------
        1
       / \
      2   3
     / \  / \
    4  5 6  7

*/
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
public class p5 {

    static Node root;

    static void insert(Node temp, int x){
        if(root == null){
            root = new Node(x);
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(temp);
        while(!q.isEmpty()){
            Node node = q.poll();
            if(node.left==null){
                node.left = new Node(x);
                return;
            }else{
                q.add(node.left);
            }
            if(node.right == null){
                node.right = new Node(x);
                return;
            }else{
                q.add(node.right);
            }
        }
    }

    static void print(Node root, List<Integer> ans){
        if(root==null || root.val==-1){
            return;
        }
        print(root.left, ans);
        ans.add(root.val);
        print(root.right, ans);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        for(int i=0; i<s.length; i++){
            insert(root, Integer.parseInt(s[i]));
        }
        List<Integer> ans = new ArrayList<>();
        print(root, ans);
        System.out.println(ans);
    }
}
