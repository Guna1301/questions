/*Imagine you're the curator of an ancient manuscript archive. Each manuscript is
assigned a unique significance score, and the archive is arranged so that every 
manuscript on the left has a lower score and every manuscript on the right has a
higher score, forming a special ordered display. Now, for an upcoming exhibition,
scholars have decided that only manuscripts with significance scores between low 
and high (inclusive) are relevant. Your task is to update the archive by removing
any manuscripts whose scores fall outside the range [low,high]. Importantly, 
while you remove some manuscripts, you must preserve the relative order of the 
remaining ones—if a manuscript was originally displayed as a descendant of another, 
that relationship should remain intact. It can be proven that there is a unique 
way to update the archive.

Display the manuscript of the updated archive. Note that the main manuscript 
(the root) may change depending on the given score boundaries.

Input format:
Line 1: space separated scores to build the manuscript archive
Line 2: two space seperated integers, low and high.

Output format:
space separated scores of the updated archive

Example 1:
input=
1 0 2
1 2
output=
1 2

Explanation:
Initial archieve:
      1
     / \
    0   2


Updated archieve:
    1
     \
      2
After removing manuscripts scores below 1 (i.e. 0), only the manuscript with 1 
and its right manuscript 2 remain.

Example 2:
input=
3 0 4 2 1
1 3
output=
3 2 1

Explanation:
Initial archieve:
          3
         / \
        0   4
         \
          2
         /
        1

Updated archieve:
      3
     /
    2
   /
  1
 */

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
class binary{
    public node build(int arr[]){
        node root = new node(arr[0]);
        for(int i=1;i<arr.length;i++){
            place(root, arr[i]);
        }
        return root;
    }
    public node place(node root, int val){
        if(val==-1){
            return null;
        }
        if(root ==null){
            return new node(val);
        }
        if(val>root.val){
            root.right = place(root.right,val);
        }
        if(val<root.val){
            root.left = place(root.left,val);
        }
        return root;
    }
    public node find(node root, int low, int high){
        if(root==null){
            return null;
        }
        if(root.val<low){
            return find(root.right,low,high);
        }
        if(root.val>high){
            return find(root.left,low,high);
        }
        root.left = find(root.left,low,high);
        root.right = find(root.right,low,high);
        return root;
    }
    public List<Integer> print(node root){
        Queue<node> q = new LinkedList<>();
        q.offer(root);
        List<Integer>ans = new ArrayList<>();
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                node curr = q.poll();
                ans.add(curr.val);
                if(curr.left!=null){
                    q.offer(curr.left);
                }
                if(curr.right!=null){
                    q.offer(curr.right);
                }
            }
        }
        return ans;
    }
}
class trimBST{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String vals[] = sc.nextLine().split(" ");
        int n = vals.length;
        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(vals[i]);
        }
        int low = sc.nextInt();
        int high = sc.nextInt();
        binary tree = new binary();
        node root = tree.build(arr);
        root = tree.find(root,low,high);
        System.out.println(tree.print(root));
        sc.close();
    }
}