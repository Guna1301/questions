package Day7;

/*
 * Bubloo is working with computer networks, where servers are connected 
 in a hierarchical structure, represented as a Binary Tree. Each server (node) 
 is uniquely identified by an integer value.
 
 Bubloo has been assigned an important task: find the shortest communication 
 path (in terms of network hops) between two specific servers in the network.
 
 Network Structure:
 ------------------
 The network of servers follows a binary tree topology.
 Each server (node) has a unique identifier (integer).
 If a server does not exist at a certain position, it is represented as '-1' (NULL).
 
 Given the root of the network tree, and two specific server IDs (E1 & E2), 
 determine the minimum number of network hops (edges) required to 
 communicate between these two servers.
 
 Input Format:
 -------------
 Space separated integers, elements of the tree.
 
 Output Format:
 --------------
 Print an integer value.
 
 
 Sample Input-1:
 ---------------
 1 2 4 3 5 6 7 8 9 10 11 12
 4 8
 
 Sample Output-1:
 ----------------
 4
 
 Explanation:
 ------------
 The edegs between 4 and 8 are: [4,1], [1,2], [2,3], [3,8]
 
 
 Sample Input-2:
 ---------------
 1 2 4 3 5 6 7 8 9 10 11 12
 6 6
 
 Sample Output-2:
 ----------------
 0
 
 Explanation:
 ------------
 No edegs between 6 and 6.
 
 */
import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int[] arr = new int[s.length];
        for(int i=0; i<arr.length; i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        int p= sc.nextInt();
        int q = sc.nextInt();
        if(p==q){
            System.out.println(0);
            return;
        }
        Node root = build(arr);
        int[] depth = {-1,-1,-1};
        boolean temp=find(root, p, q,0,depth);
        System.out.println(depth[0]-depth[2]+depth[1]-depth[2]);
    }
    static boolean find(Node root, int p, int q, int d , int[] depth){
        if(root==null){
            return false;
        }
        if(root.val == p){
            depth[0] = d;
            return true;
        }
        if(root.val == q){
            depth[1] = d;
            return true;
        }
        boolean l = find(root.left, p, q, d+1, depth);
        boolean r = find(root.right, p,q,d+1,depth);
        if(depth[0]!=-1 && depth[1]!=-1){
            depth[2] = d;
            return true;
        }
        return r&&l;
        
        
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