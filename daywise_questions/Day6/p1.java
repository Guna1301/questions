/*
 * Given the preorder and postorder traversals of a binary tree, construct 
 the original binary tree and print its level order traversal.
 
 Input Format:
 ---------------
 Space separated integers, pre order data
 Space separated integers, post order data
 
 Output Format:
 -----------------
 Print the level-order data of the tree.
 
 
 Sample Input:
 ----------------
 1 2 4 5 3 6 7
 4 5 2 6 7 3 1
 
 Sample Output:
 ----------------
 [[1], [2, 3], [4, 5, 6, 7]]
 
 Explanation:
 --------------
         1
        / \
       2   3
      / \  / \
     4   5 6  1 21 7
 
 
 Sample Input:
 ----------------
 1 2 3
 2 3 1
 
 Sample Output:
 ----------------
 [[1], [2, 3]]
 
 Explanation:
 --------------
     1
    / \
   2  31 2 
 
 */
import java.util.*;
class Solution{
    static int preindex = 0;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s1 = sc.nextLine().split(" ");
        String[] s2 = sc.nextLine().split(" ");
        int[] pre = new int[s1.length];
        int[] post = new int[s2.length];
        for(int i=0; i<s1.length; i++){
            pre[i] = Integer.parseInt(s1[i]);
            post[i] = Integer.parseInt(s2[i]);
        }
        HashMap<Integer,Integer> postt = new HashMap<>();
        for(int i=0; i<pre.length; i++){
            postt.put(post[i], i);
        }
        Node root = build(pre,post, postt, 0, pre.length-1);
        List<List<Integer>> ans = new ArrayList<>();
        levelorder(root, ans);
        System.out.println(ans);
        
    }
    static void levelorder(Node root, List<List<Integer>> ans){
        if(root==null){
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> list = new ArrayList<>();
            for(int i=0; i<size; i++){
                Node node = q.poll();
                if(node.left!=null){
                    q.add(node.left);
                }
                if(node.right!=null){
                    q.add(node.right);
                }
                list.add(node.val);
            }
            ans.add(new ArrayList<>(list));
        }
    }
    static Node build(int[] pre,int[] post,HashMap<Integer, Integer> posthm, int li, int ri){
        if(li>ri){
            return null;
        }
        Node root = new Node(pre[preindex++]);
        if(li==ri){
            return root;
        }
        int left = pre[preindex];
        int postindex = posthm.get(left);
        if(postindex>=li && postindex<=ri){
            root.left = build(pre,post, posthm, li, postindex);
            root.right = build(pre,post, posthm, postindex+1, ri-1);
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