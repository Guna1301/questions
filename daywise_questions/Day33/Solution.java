/*In a magical kingdom, each wizard carries a certain number of enchanted crystals. 
A pair of wizards is said to have a "dominant wizard" if the first wizard, who 
\arrived earlier at the royal gathering, possesses more than twice as many 
crystals as the second wizard, who arrived later.

Given an list of crystals, representing the number of enchanted crystals carried 
by each wizard in their order of arrival at the gathering, calculate the number 
of "dominant wizard" pairs.

A pair of wizards (x, y) is considered dominant if:

- 0 ≤ x < y < crystals.length and
- crystals[x] > 2 × crystals[y]

Example 1:
Input: 
1 3 2 3 1
Output: 2

Explanation: The dominant wizard pairs are:
- Wizard 1 (3 crystals) and Wizard 4 (1 crystal), since 3 > 2 × 1
- Wizard 3 (3 crystals) and Wizard 4 (1 crystal), since 3 > 2 × 1

Example 2:
Input:
2 4 3 5 1
Output: 3

Explanation: The dominant wizard pairs are:
- Wizard 1 (4 crystals) and Wizard 4 (1 crystal), since 4 > 2 × 1
- Wizard 2 (3 crystals) and Wizard 4 (1 crystal), since 3 > 2 × 1
- Wizard 3 (5 crystals) and Wizard 4 (1 crystal), since 5 > 2 × 1

Constraints:
- 1 ≤ crystals.length ≤ 5 × 10^4
- -2^31 ≤ crystals[i] ≤ 2^31 - 1 */

import java.util.*;
class node{
    int val, priority,size,count;
    node left;
    node right;
    public node(int val) {
        this.val = val;
        this.priority = new Random().nextInt(100);
        this.size = 1;
        this.count = 1;
        this.left = this.right = null;
    }
        
}

class treap{
    node root;
    int getsize(node temp){
        if(temp==null){
            return 0;
        }
        return temp.size;
    }
    int updatesize(node temp){
        if(temp!=null){
            temp.size = temp.count+getsize(temp.left)+getsize(temp.right);
        }
        return temp.size;
    }
    public void insert(int val){
        root = insertval(root,val);
    }
    public node insertval(node root, int val){
        if(root==null){
            return new node(val);
        }
        if(val==root.val){
            root.count++;
        }else if(val<root.val){
            root.left = insertval(root.left, val);
            if(root.left.priority>root.priority){
                root = right(root);
            }
        }else if(val>root.val){
            root.right = insertval(root.right, val);
            if(root.right.priority>root.priority){
                root = left(root);
            }
        }
        updatesize(root);
        return root;
    }
    public node left(node x){
        node y = x.right;
        node t2 = y.left;
        y.left = x;
        x.right = t2;

        updatesize(x);
        updatesize(y);
        return y;

    }
    public node right(node y){
        node x = y.left;
        node t2 = x.right;

        x.right = y;
        y.left = t2;

        updatesize(y);
        updatesize(x);
        return x;
    }

    public int find(node root, int val){
        if(root==null){
            return 0;
        }
        if(val<=root.val){
            return find(root.left,val);
        }else{
            return root.count + getsize(root.left)+find(root.right,val);
        }
    }
    public int count(int val){
        return find(root,val);
    }
}

public class Solution{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String vals[] = sc.nextLine().split(" ");
        int n = vals.length;
        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(vals[i]);
        }
        int ans =0;
        treap t = new treap();
        for(int i=n-1;i>=0;i--){
            ans += t.count(arr[i]);
            t.insert(2*arr[i]);
        }
        System.out.println(ans);
    }
}
