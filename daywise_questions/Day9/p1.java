import java.util.*;
public class p1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int[] arr = new int[s.length];
        for(int i=0; i<arr.length; i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        Node root = insert(arr);
        int totalsum = summ(root);
        int temp = find(root, totalsum);
        System.out.println(b);
        
    }
    static boolean b= false;
    static int summ(Node root){
        if(root==null){
            return 0;
        }
        int l = summ(root.left);
        int r = summ(root.right);
        int sum = root.val + l + r;
        return sum;
    }
    static int find(Node root, int total){
        if(root==null){
            return 0;
        }
        int l = find(root.left,total);
        if(total - l == total/2){
            boolean b = true;
        }
        int r = find(root.right,total);
        if(total - r == total/2){
            boolean b = true;
        }
        int sum = root.val + l + r;
        return sum;
    }
    
    static Node insert(int[] arr){
        Queue<Node> q = new LinkedList<>();
        Node root= new Node(arr[0]);
        q.add(root);
        int i = 1;
        while(!q.isEmpty() && i<arr.length){
            Node node = q.poll();
            if(arr[i]==-1){
                node.left = new Node(arr[i]);
                q.add(node.left);
            }
            i++;
            if(i<arr.length && arr[i]==-1){
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