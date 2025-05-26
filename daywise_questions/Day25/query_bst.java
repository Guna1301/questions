/*
 * Imagine you are the curator of a historic library, where books are arranged in a 
 unique catalog system based on their publication years. The library’s archive is 
 structured like a hierarchical tree, with each book’s publication year stored at 
 a node. You are given the nodes of this catalog tree starting with main node
 and a list of query years.
 
 For each query year, you need to find two publication years:
 - The first is the latest year in the archive that is less than or equal to the 
   query year. If no such book exists, use -1.
 - The second is the earliest year in the archive that is greater than or equal 
   to the query year. If no such book exists, use -1.
 
 Display the results as an list of pairs, where each pair corresponds to a query.
 
 Example 1:
 ----------
 Input: 
 2006 2002 2013 2001 2004 2009 2015 2014
 2002 2005 2016
 
 Output:
 [[2002, 2002], [2004, 2006], [2015, -1]] 
 
 
 Archive Structure:
           2006
          /    \
      2002     2013
      /   \     /   \
   2001  2004  2009  2015
                      /
                   2014
                   
 Explanation:  
 - For the query 2002, the latest publication year that is ≤ 2002 is 2002, and 
   the earliest publication year that is ≥ 2002 is also 2002.  
 - For the query 2005, the latest publication year that is ≤ 2005 is 2004, and 
   the earliest publication year that is ≥ 2005 is 2006.  
 - For the query 2016, the latest publication year that is ≤ 2016 is 2015, but 
   there is no publication year ≥ 2016, so we output -1 for the second value.
 
 Example 2:
 ----------
 Input:  
 2004 2009
 2003
 
 Output:
 [[-1, 2004]]
 
 Explanation:  
 - For the query 2003, there is no publication year ≤ 2003, while the earliest 
   publication year that is ≥ 2003 is 2004.
 
 Constraints:
 - The total number of books in the archive is in the range [2, 10^5].
 - Each publication year is between 1 and 10^6.
 - The number of queries n is in the range [1, 10^5].
 - Each query year is between 1 and 10^6.
 
 */

 //BST implementation
 import java.util.*;
 class node{
     int val;
     node left;
     node right;
     node(int val){
         this.val = val;
         this.right = this.left = null;
     }
 }
 class bst{
     public node build(node root,int val){
         if(root==null){
             return new node(val);
         }
         if(val<root.val){
             root.left = build(root.left,val);
         }
         if(val>root.val){
             root.right = build(root.right,val);
         }
         return root;
     }
     public void findmin(node root,int q, int temp[]){
         if(root==null){
             return;
         }
         if(root.val==q){
             temp[0] = root.val;
             temp[1] = root.val;
             return;
         }
         if(root.val>q){
             temp[1] = root.val;
             findmin(root.left, q, temp);
         }else{
             temp[0] = root.val;
             findmin(root.right, q, temp);
         }
         
     }
     // public void findmax(node root,int q, int temp[]){
     //     if(root==null){
     //         return;
     //     }
     //     if(root.val<q){
     //         findmax(root.right, q, temp);
     //     }else{
     //         temp[1] = root.val;
     //         findmax(root.left, q, temp);
     //     }
         
     // }
 }
 
 public class query_bst {
     public static void main(String args[]){
         Scanner sc = new Scanner(System.in);
         String vals[] = sc.nextLine().split(" ");
         String qs[] = sc.nextLine().split(" ");
         int n = vals.length;
         int arr[] = new int[n];
         int n1 = qs.length;
         int ques[] = new int[n1];
         for(int i=0;i<n;i++){
             arr[i] = Integer.parseInt(vals[i]);
         }
         for(int i=0;i<n1;i++){
             ques[i] = Integer.parseInt(qs[i]);
         }
         bst tree = new bst();
         node root = new node(arr[0]);
         for(int i=1;i<arr.length;i++){
             tree.build(root,arr[i]);
         }
         List<int[]> ans = new ArrayList<>();
         for(int q:ques){
             int temp[] = new int[]{-1,-1};
             tree.findmin(root,q,temp);
             // tree.findmax(root,q,temp);
             ans.add(temp);
         }
         for(int dum[]:ans){
             System.out.println(Arrays.toString(dum));
         }
         
         
     }
 }