/*
 * Given the in-order and post-order traversals of a binary tree, construct 
 the original binary tree. For the given Q number of queries, 
 each query consists of a lower level and an upper level. 
 The output should list the nodes in the order they appear in a level-wise.
 
 Input Format:
 -------------
 An integer N representing the number nodes.
 A space-separated list of N integers representing the similar to in-order traversal.
 A space-separated list of N integers representing the similar to post-order traversal.
 An integer Q representing the number of queries.
 Q pairs of integers, each representing a query in the form:
 Lower level (L)
 Upper level (U)
 
 Output Format:
 For each query, print the nodes in order within the given depth range
 
 Example
 Input:
 7
 4 2 5 1 6 3 7
 4 5 2 6 7 3 1
 2
 1 2
 2 3
 Output:
 [1, 2, 3]
 [2, 3, 4, 5, 6, 7]
 
 Explanation:
         1
        / \
       2   3
      / \  / \
     4   5 6  7
 Query 1 (Levels 1 to 2): 1 2 3
 Query 2 (Levels 2 to 3): 2 3 4 5 6 7
 
 */
import java.util.*;
class Solution{
    static int postindex = 0;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] inorder = new int[n];
        int[] post = new int[n];
        postindex = n-1;
        for(int i=0; i<n; i++){
            inorder[i] = sc.nextInt();
        }
        for(int i=0; i<n; i++){
            post[i] = sc.nextInt();
        }
        int levels = sc.nextInt();
        int[][] range = new int[levels][2];
        for(int i=0; i<levels; i++){
            range[i][0] = sc.nextInt();
            range[i][1] = sc.nextInt();
        }
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i=0; i<n; i++){
            hm.put(inorder[i], i);
        }
        Node root = constructBinaryTree(inorder, post,hm,0, n-1);
        List<List<Integer>> ans = new ArrayList<>();
        for(int i=0; i<levels; i++){
            List<Integer> list = new ArrayList<>();
            levelorder(root, list,1, range[i][0], range[i][1]);
            ans.add(new ArrayList<>(list));
        }
        for(int i=0; i<ans.size(); i++){
            System.out.println(ans.get(i));
        }
        
    }
    
    static Node constructBinaryTree(int[] inorder,int[] post, HashMap<Integer, Integer> hm, int li, int ri){
        if(li>ri){
            return null;
        }
        Node root = new Node(post[postindex--]);
        int ci = hm.get(root.val);
        
        root.right = constructBinaryTree(inorder, post, hm, ci+1, ri);
        root.left = constructBinaryTree(inorder, post, hm, li, ci-1);
        return root;
    }
    
    
    static void levelorder(Node root, List<Integer> ans, int i,int l, int r){
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
            if(i>=l && i<=r){
                ans.addAll(list);
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