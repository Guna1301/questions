/*
 * In an exhibition, A shopkeeper organizing a game stall,
'N' peolple can participate in the game, each person will get a token 
with a positive number printed on both sides(possibly different).

After that, all of them place the 'N' tokens on a table in a row, 
Now, the shopkeeper will turn any number of tokens upside down.
For example, if a token placed on a table shows 5 upside 2 downside
after turn it will show 2 upside and 5 downside.

After turning the tokens, if the number X on the downside of the token, 
is not on the upside of any other token, then the person who owns that token 
will be the winner. If no such number found, NO winner.

Based on following information and you need to find X.
You will be given 'N' upside numbers and 'N' down side numbers on tokens 
when placed on table.

Note: You need to print the number X, and X to be smallest of all such tokens.
if no such number X present, print 0.

Input Format:
----------------
Line-1 -> an integers N, number of tokens
Line-2 -> N space seperated integers, upside numbers
Line-3 -> N space seperated integers, downside numbers

Output Format:
------------------
Print the integer X if found.
Otherwise print '0'.


Sample Input-1:
-------------------
5
1 2 4 4 7
1 3 4 1 3

Sample Output-1:
---------------------
2

Explanation:
---------------
If we turn the second token upside down, the upside numbers are [1,3,4,4,7]
and the downside numbers are [1,2,4,1,3].
We choose the second token, which has number 2 on the downside, and it
isn't on the upside of any token, so X=2.


Sample Input-2:
-------------------
5
1 3 4 4 1
1 4 3 1 3

Sample Output-2:
---------------------
0
*/

import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] up = new int[n];
        int[] down = new int[n];
        Set<Integer> hs = new HashSet<>();
        for(int i = 0; i < n; i++){
            up[i] = sc.nextInt();
        }
        for(int i = 0; i < n; i++){
            down[i] = sc.nextInt();
            hs.add(down[i]);
        }
        int x = Integer.MAX_VALUE;
        for(int i = 0; i<n; i++){
            if(!hs.contains(up[i])){
                x = Math.min(x, up[i]);
            }
        }
        // int x = find(up, down, 0);
        System.out.println(x==Integer.MAX_VALUE ? 0 : x);
    }
    static int find(int[] up, int[] down, int idx){
        if (idx == up.length){
            return getminValid(up, down);
        }
        int notFlipped = find(up, down, idx + 1);
        swap(idx, up, down);
        int flipped = find(up, down, idx + 1);
        swap(idx, up, down);
        return Math.min(notFlipped, flipped);
    }
    static int getminValid(int[] up, int[] down) {
        Set<Integer> hs = new HashSet<>();
        for(int val : up){
            hs.add(val);
        }
        int mini = Integer.MAX_VALUE;
        for (int val:down){
            if(!hs.contains(val)) {
                mini = Math.min(mini, val);
            }
        }
        return mini;
    }
    static void swap(int idx, int[] up, int[] down){
        int temp = up[idx];
        up[idx] = down[idx];
        down[idx] = temp;
    }
}