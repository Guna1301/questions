/*
 * Imagine you are a librarian organizing books on vertical shelves in a grand 
 library. The books are currently scattered across a tree-like structure, where 
 each book (node) has a position determined by its shelf number (column) and row 
 number (level).
 
 Your task is to arrange the books on shelves so that:
 1. Books are placed column by column from left to right.
 2. Within the same column, books are arranged from top to bottom (i.e., by row).
 3. If multiple books belong to the same shelf and row, they should be arranged 
 from left to right, just as they appear in the original scattered arrangement.
 
 Sample Input:
 -------------
 3 9 20 -1 -1 15 7
 
 Sample Output:
 --------------
 [[9],[3,15],[20],[7]]
 
 Explanation:
 ------------
          3
        /   \
       9     20
           /    \
          15     7
 
 Shelf 1: [9]
 Shelf 2: [3, 15]
 Shelf 3: [20]
 Shelf 4: [7]
 
 
 Sample Input-2:
 ---------------
 3 9 8 4 0 1 7
 
 Sample Output-2:
 ----------------
 [[4],[9],[3,0,1],[8],[7]]
 
 Explanation:
 ------------
 
           3
        /     \
       9       8
     /   \   /   \
    4     0 1     7
 
 Shelf 1: [4]
 Shelf 2: [9]
 Shelf 3: [3, 0, 1]
 Shelf 4: [8]
 Shelf 5: [7]
 
 */
import java.util.*;
public class p3{
    static TreeMap<Integer, List<Integer>> tm = new TreeMap<>();
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        if(s[0].isEmpty()){
            System.out.println(new ArrayList<List<Integer>>());
            return;
        }
        int[] arr = new int[s.length];
        for(int i=0; i<arr.length; i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        Node root = insert(arr);
        find(root);
        // find(root, 0);
        List<List<Integer>> ans = new ArrayList<>();
        // int first = tm.firstKey();
        // int last = tm.lastKey();
        // for(int i = first; i<=last; i++){
        //     ans.add(new ArrayList<>(tm.get(i)));
        
        // }
        for(List<Integer> a: tm.values()){
            ans.add(a);
        }
        System.out.println(ans);
        
    }
    
    // static void find(Node root, int level){
    //     if(root==null){
    //         return;
    //     }
    //     if(!tm.containsKey(level)){
    //         tm.put(level, new ArrayList<>());
    //     }
    //     tm.get(level).add(root.val);
    //     find(root.left, level-1);
    //     find(root.right, level+1);
    // }
    
    
    static Node insert(int[] arr){
        if(arr[0]==-1){
            return null;
        }
        Queue<Node> q = new LinkedList<>();
        Node root= new Node(arr[0]);
        q.add(root);
        int i = 1;
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

    static void find(Node root){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i<size; i++){
                Pair p = q.poll();
                Node node = p.node;
                int index = p.index;
                if(node.left!=null){
                    q.add(new Pair(node.left, index-1));
                }
                if(node.right!=null){
                    q.add(new Pair(node.right, index+1));
                }
                if(!tm.containsKey(index)){
                    tm.put(index, new ArrayList<>());
                }
                tm.get(index).add(node.val);
            }
        }
    }
}
class Pair{
    Node node;
    int index;
    Pair(Node node, int index){
        this.node = node;
        this.index = index;
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