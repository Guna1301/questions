package Day23;
/* 
Imagine you are designing a grand castle where each room holds a specific amount 
of treasure. The castle is built in a binary layout, meaning every room may lead 
to two adjacent wings—a left wing and a right wing. 

An "organized section" of the castle follows this rule: for any given room, 
every room in its left wing contains a treasure value that is strictly less 
than the current room’s value, and every room in its right wing contains a 
value that is strictly greater. Additionally, each wing must itself be organized
according to the same rule.

Your challenge is to determine the maximum total treasure (i.e., the sum of 
treasure values) that can be gathered from any such organized section of the castle.

Example 1:
input=
1 4 3 2 4 2 5 -1 -1 -1 -1 -1 -1 4 6
output=
20

Castle:
          1
        /   \
       4     3
      / \   / \
     2   4 2   5
              / \
             4   6

Explanation: The best organized section starts at the room with a treasure value 
of 3, yielding a total treasure of 20.

Example 2:
input=
4 3 -1 1 2
output=
2

Castle:
    4
   /
  3
 / \
1   2

Explanation: The optimal organized section is just the single room with a 
treasure value of 2.

Example 3:
input=
-4 -2 -5
output=
0

Castle:
   -4
  /  \
-2   -5
 
Explanation: Since all rooms contain negative treasure values, no beneficial 
organized section exists, so the maximum total treasure is 0.

Constraints:

- The number of rooms in the castle ranges from 1 to 40,000.
- Each room’s treasure value is an integer between -40,000 and 40,000.
*/

import java.util.*;
class node{
    int val;
    node left;
    node right;
    node(int val){
        this.val = val;
        this.left = this.right = null;
    }
}
class binary{
    public node build(int arr[]){
        int id = 1;
        node root = new node(arr[0]);
        Queue<node> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            node curr = q.poll();
            if(id<arr.length && arr[id]!=-1){
                curr.left = new node(arr[id]);
                q.offer(curr.left);
            }
            id++;
            if(id<arr.length && arr[id]!=-1){
                curr.right = new node(arr[id]);
                q.offer(curr.right);
            }
            id++;
            
        }
        return root;
    }
    int max = 0;
    public int find(node root){
        if(root==null){
            return 0;
        }
        if(root.left==null && root.right==null){
            max = Math.max(max,root.val);
            return root.val;
        }
        int left = find(root.left);
        int right = find(root.right);
        if((root.left!=null && root.left.val>=root.val) || (root.right!=null && root.right.val<=root.val)){
            return -1;
        }
        if(left>-1 && right>-1){
            int dum = left+right+root.val;
            max = Math.max(dum,max);
            return dum;
        }
        
        
        return -1;
    }
}
class Solution{
    public static void main(String args[]){
        Scanner sc= new Scanner(System.in);
        String vals[] = sc.nextLine().split(" ");
        int n = vals.length;
        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(vals[i]);
        }
        binary tree = new binary();
        node root = tree.build(arr);
        tree.find(root);
        System.out.println(tree.max);
    }
}