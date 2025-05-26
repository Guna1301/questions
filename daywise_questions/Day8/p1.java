/*
 * VishnuVardan is working with Decision Trees for AI-based predictions.
 To analyze alternative outcomes, Kishore has planned to flip the decision 
 tree horizontally to simulate a reverse processing approach.
 
 Rules for Flipping the Decision Tree:
    - The original root node becomes the new rightmost node.
    - The original left child becomes the new root node.
    - The original right child becomes the new left child.
 This transformation is applied level by level recursively.
 
 Note:
 ------
 - Each node in the given tree has either 0 or 2 children.
 - Every right node in the tree has a left sibling sharing the same parent.
 - Every right node has no further children (i.e., they are leaf nodes).
 
 Your task is to help VishnuVardan flip the Decision Tree while following 
 the given transformation rules.
 
 Input Format:
 -------------
 Space separated integers, nodes of the tree.
 
 Output Format:
 --------------
 Print the list of nodes of flipped tree as described below.
 
 
 Sample Input-1:
 ---------------
 4 2 3 5 1
 
 Sample Output-1:
 ----------------
 5 1 2 3 4
 
 
 Sample Input-2:
 ---------------
 4 2 5
 
 Sample Output-2:
 ----------------
 2 5 4
 
 
 */
import java.util.*;

/*
For reference purpose only.

class TreeNode {
    Integer val;
    TreeNode left, right;
    
    TreeNode(Integer val) {
        this.val = val;
        this.left = this.right = null;
    }
}

*/

class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int[] arr = new int[s.length];
        for(int i=0; i<arr.length; i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        Node root = build(arr);
        List<List<Integer>> ans = new ArrayList<>();
        find(root, ans);
        // Collections.sort(ans);
        for(int i=ans.size()-1; i>=0; i--){
            for(int j=0; j<ans.get(i).size(); j++){
                System.out.print(ans.get(i).get(j)+" ");
            }
            // System.out.println(ans.get(i));
        }
        
    }
    
    static void find(Node root, List<List<Integer>> ans){
        Queue<Node>  q = new LinkedList<>();
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
    
    static Node build(int[] arr){
        Queue<Node> q = new LinkedList<>();
        Node root = new Node(arr[0]);
        int i=1;
        q.add(root);
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