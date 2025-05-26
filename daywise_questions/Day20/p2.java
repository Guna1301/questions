package Day20;
/*
 * Imagine you're the chief curator at a renowned museum that houses a rare collection 
 of ancient artifacts. These artifacts are arranged in a special display that 
 follows a strict rule: any artifact positioned to the left of another has a lower 
 value, and any artifact on the right has a higher value. 
 
 Your task is to transform this display into what is known as a "Greater Artifact 
 Display." In this new arrangement, each artifact’s new value will be its original 
 value plus the sum of the values of all artifacts that are more valuable than it.
 
 Example 1:
 ----------
 input=
 4 2 6 1 3 5 7
 output=
 22 27 13 28 25 18 7
 
 Explanation:
 Input structure 
        4
       / \
      2   6
     / \ / \
    1  3 5  7
 
 Output structure:
         22
       /   \
      27   13
     / \   / \
    28 25 18  7
 
 Reverse updates:
 - Artifact 7: 7
 - Artifact 6: 6 + 7 = 13
 - Artifact 5: 5 + 13 = 18
 - Artifact 4: 4 + 18 = 22
 - Artifact 3: 3 + 22 = 25
 - Artifact 2: 2 + 25 = 27
 - Artifact 1: 1 + 27 = 28
 
 */
import java.util.*;
public class p2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int[] arr = new int[s.length];
        for(int i = 0; i<s.length; i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        Node root = insert(arr);
        find(root, new int[]{0});
        List<Integer> res = new ArrayList<>();
        
        levelorder(root, res);
        System.out.println(res);
    }
    
    static void levelorder(Node root, List<Integer> ans){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node node = q.poll();
            ans.add(node.val);
            if(node.left!=null){
                q.add(node.left);
            }
            if(node.right!=null){
                q.add(node.right);
            }
        }
    }
    
    static void find(Node root, int[] sum){
        if(root==null){
            return;
        }
        find(root.right, sum);
        sum[0]+=root.val;
        root.val = sum[0];
        find(root.left, sum);
    }
    
    
    static Node insert(int[] arr){
        Node root = new Node(arr[0]);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while(!q.isEmpty() && i<arr.length){
            Node node = q.poll();
            if(arr[i]!=-1){
                node.left = new Node(arr[i]);
                q.add(node.left);
            }
            i++;
            if(i<arr.length&& arr[i]!=-1){
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
    public Node(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }
}