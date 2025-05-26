/*
 * The Indian Army has established multiple military camps at strategic locations 
 along the Line of Actual Control (LAC) in Galwan. These camps are connected in 
 a hierarchical structure, with a main base camp acting as the root of the network.
 
 To fortify national security, the Government of India has planned to deploy 
 a protective S.H.I.E.L.D. that encloses all military camps forming the outer 
 boundary of the network.
 
 Structure of Military Camps:
     - Each military camp is uniquely identified by an integer ID.
     - A camp can have at most two direct connections:
         - Left connection → Represents a subordinate camp on the left.
         - Right connection → Represents a subordinate camp on the right.
     - If a military camp does not exist at a specific position, it is 
       represented by -1
    
 Mission: Deploying the S.H.I.E.L.D.
 Your task is to determine the list of military camp IDs forming the outer 
 boundary of the military network. This boundary must be traversed in 
 anti-clockwise order, starting from the main base camp (root).
 
 The boundary consists of:
 1. The main base camp (root).
 2. The left boundary:
     - Starts from the root’s left child and follows the leftmost path downwards.
     - If a camp has a left connection, follow it.
     - If no left connection exists but a right connection does, follow the right connection.
     - The leftmost leaf camp is NOT included in this boundary.
 3. The leaf camps (i.e., camps with no further connections), ordered from left to right.
 4. The right boundary (in reverse order):
     - Starts from the root’s right child and follows the rightmost path downwards.
     - If a camp has a right connection, follow it.
     - If no right connection exists but a left connection does, follow the left connection.
     - The rightmost leaf camp is NOT included in this boundary.
 
 
 Input Format:
 -------------
 space separated integers, military-camp IDs.
 
 Output Format:
 --------------
 Print all the military-camp IDs, which are at the edge of S.H.I.E.L.D.
 
 
 Sample Input-1:
 ---------------
 5 2 4 7 9 8 1
 
 Sample Output-1:
 ----------------
 [5, 2, 7, 9, 8, 1, 4]
 
 
 Sample Input-2:
 ---------------
 11 2 13 4 25 6 -1 -1 -1 7 18 9 10
 
 Sample Output-2:
 ----------------
 [11, 2, 4, 7, 18, 9, 10, 6, 13]
 
 
 Sample Input-3:
 ---------------
 1 2 3 -1 -1 -1 5 -1 6 7
 
 Sample Output-3:
 ----------------
 [1, 2, 7, 6, 5, 3]
 
 */
import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int[] arr = new int[s.length];
        for(int i=0; i<s.length; i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        Node root = insert(arr);
        List<Integer> ans = new ArrayList<>();
        find(root,ans);
        System.out.println(ans);
    }
    static void find(Node root, List<Integer> ans){
        if(root==null){
            return;
        }
        ans.add(root.val);
        List<Integer> lefts = new ArrayList<>();
        List<Integer> rights = new ArrayList<>();
        List<Integer> leaves = new ArrayList<>();
        leftview(root.left, lefts);
        rightview(root.right,0,rights);
        leaf(root, leaves);
        ans.addAll(lefts);
        ans.addAll(leaves);
        Collections.reverse(rights);
        ans.addAll(rights);
    }
    static void leaf(Node root, List<Integer> ans){
        if(root==null){
            return;
        }
        if(root.left==null && root.right==null){
            ans.add(root.val);
        }
        leaf(root.left, ans);
        leaf(root.right, ans);
    }
    static void rightview(Node root, int d, List<Integer> ans){
        if(root==null || (root.left==null && root.right==null)){
            return;
        }
        if(d==ans.size()) ans.add(root.val);
        rightview(root.right, d+1, ans);
        rightview(root.left, d+1, ans);
    }
    static void leftview(Node root, List<Integer> ans){
        if(root==null|| (root.left==null && root.right==null)){
            return;
        }
        ans.add(root.val);
        if(root.left!=null){
            leftview(root.left, ans);
        }
        else{
            leftview(root.right, ans);
        }
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