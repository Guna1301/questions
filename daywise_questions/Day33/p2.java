/*There are n football players standing in the ground, coach wants to know the 
P-th largest height of the players. Given an array of heights[] and the value of P. 
Help the coach to find the P'th largest height.

Note: You are supposed to print the P'th largest height in the sorted order of heights[].
      Not the P'th distinct height.

Input Format:
-------------
Line-1: Size of array n and P value(space separated)
Line-2: Array elements of size n.

Output Format:
--------------
Print P'th largest height.

Sample input-1:
---------------
8 2
1 2 1 3 4 5 5 5

Sample output-1:
----------------
5

Sample input-2:
---------------
6 3
2 4 3 1 2 5

Sample output-2:
----------------
3
 */

 import java.util.*;
 class node{
     int val, priority,size,freq;
     node left;
     node right;
     public node(int val){
         this.val = val;
         this.priority = new Random().nextInt(100);
         this.size = 1;
         this.freq = 1;
         this.left = this.right = null;
     }
 }
 
 class Treap{
     node root
     public int getsize(node temp){
         if(temp==null){
             return 0;
         }
         return temp.size;
     }
     public void updatesize(node temp){
         if(temp!=null){
             temp.size = temp.freq+getsize(temp.left)+getsize(temp.right);
         }
     }
     public node right(node n2){
         node n1 = n2.left;
         n2.left = n2.right;
         n1.right = n2;
         updatesize(n2);
         updatesize(n1);
         return n1;
     }
     public node left(node n1){
         node n2 = n1.right;
         n1.right = n2.left;
         n2.left = n1;
         updatesize(n1);
         updatesize(n2);
         return n2;
     }
     public node build(node root,int val){
         if(root==null){
             return new node(val);
         }
         if(val==root.val){
             root.freq++;
         }else if(val<root.val){
             root.left = build(root.left,val);
             if(root.left.priority>root.priority){
                 root = right(root);
             }
         }else{
             root.right = build(root.right,val);
             if(root.right.priority>root.priority){
                 root = left(root);
             }
         }
         updatesize(root);
         return root;
     }
     public node insert(int arr[]){
         for(int num:arr){
             root = build(root,num);
         }
         
         return root;
     }
     public int find(node root, int p){
         if(root==null){
             return -1;
         }
         int rsize = getsize(root.right);
         if(rsize+1<=p && rsize+root.freq>=p){
             return root.val;
         }else if(rsize>=p){
             return find(root.right,p);
         }else{
             return find(root.left,p-rsize-root.freq);
         }
     }
 }
 class p2{
     public static void main(String args[]){
         Scanner sc = new Scanner(System.in);
         int n = sc.nextInt();
         int p = sc.nextInt();
         int arr[] = new int[n];
         for(int  i=0;i<n;i++){
             arr[i] = sc.nextInt();
         }
         Treap t = new Treap();
         node root = t.insert(arr);
         System.out.println(t.find(root,p));
     }
     
 }