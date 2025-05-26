/*
 * Construct the binary tree from the given In-order and Pre-order. 
 Find Nodes Between Two Levels in Spiral Order.
 The spiral order is as follows:
 -> Odd levels → Left to Right.
 -> Even levels → Right to Left.
 
 Input Format:
 --------------
 An integer N representing the number of nodes.
 A space-separated list of N integers representing the in-order traversal.
 A space-separated list of N integers representing the pre-order traversal.
 Two integers:
 Lower Level (L)
 Upper Level (U)
 
 Output Format:
 Print all nodes within the specified levels, but in spiral order.
 
 Example:
 Input:
 7
 4 2 5 1 6 3 7
 1 2 4 5 3 6 7
 2 3
 
 Output:
 3 2 4 5 6 7
 
 Explanation:
 Binary tree structure:
         1
        / \
       2   3
      / \  / \
     4   5 6  7
 
 Levels 2 to 3 in Regular Order:
 Level 2 → 2 3
 Level 3 → 4 5 6 7
 
 Spiral Order:
 Level 2 (Even) → 3 2 (Right to Left)
 Level 3 (Odd) → 4 5 6 7 (Left to Right)
 
 */
import java.util.*;
class Solution{
    static int preindex = 0;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] inorder = new int[n];
        int[] pre = new int[n];
        for(int i=0; i<n; i++){
            inorder[i] = sc.nextInt();
        }
        for(int i=0; i<n; i++){
            pre[i] = sc.nextInt();
        }
        int l = sc.nextInt();
        int u = sc.nextInt();
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int i=0; i<n; i++){
            hm.put(inorder[i], i);
        }
        Node root = tree(inorder, pre, hm, 0, n-1);
        List<Integer> ans = new ArrayList<>();
        spiral(root, ans, 1, l,u);
        System.out.println(ans);
    }
    
    static Node tree(int[] inorder, int[] pre, HashMap<Integer,Integer> hm, int li, int ri){
        if(li>ri){
            return null;
        }
        Node root = new Node(pre[preindex++]);
        int ci = hm.get(root.val);
        root.left = tree(inorder, pre, hm, li, ci-1);
        root.right = tree(inorder, pre, hm, ci+1, ri);
        return root;
    }
    
    static void spiral(Node root, List<Integer> ans, int i, int l, int u){
        if(root==null){
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> list = new ArrayList<>();
            for(int k=0; k<size; k++){
                Node node = q.poll();
                if(node.left!=null){
                    q.add(node.left);
                }
                if(node.right!=null){
                    q.add(node.right);
                }
                list.add(node.val);
            }
            if(i>=l && i<=u){
                if(i%2!=0){
                    ans.addAll(list);
                }else{
                    Collections.reverse(list);
                    ans.addAll(list);
                }
            }
            i++;
        }
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