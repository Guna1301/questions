/*
 * You are given some tokens printed with unique numbers on it and an integer T.
Your task is to find the least number of tokens that you need to make up the 
value T, by adding the numbers printed on all the tokens. 
If you cannot make the value T, by any combination of the tokens, return -1.

NOTE: Assume that you have unlimited set of tokens of each number type.

Input Format:
-------------
Line-1: Space separated integers tokens[], number printed on tokens.
Line-2: An integer T.

Output Format:
--------------
Print an integer, minimum number of tokens to make the value T.


Sample Input-1:
---------------
1 2 5
11

Sample Output-1:
----------------
3

Explanation:
------------
5+5+1 = 11


Sample Input-2:
---------------
2 4
15

Sample Output-2:
----------------
-1


 */

 import java.util.*;
class Solution{
    static int[][] dp;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split("\\s+");
        int[] arr = new int[s.length];
        for(int i = 0; i<arr.length; i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        int x = sc.nextInt();
        dp = new int[arr.length+1][x+1];
        for(int[] a: dp){
            Arrays.fill(a, -1);
        }
        int y = find(arr, 0, x);
        System.out.println(y==Integer.MAX_VALUE?-1:y);
    }
    
    static int find(int[] arr, int idx, int amount){
        if(amount==0){
            return 0;
        }
        if(idx==arr.length || amount<0){
            return Integer.MAX_VALUE;
        }
        if(dp[idx][amount]!=-1){
            return dp[idx][amount];
        }
        int nottake = find(arr, idx+1, amount);
        int take = Integer.MAX_VALUE;
        if(amount >= arr[idx]){
            int x = find(arr, idx, amount-arr[idx]);
            if(x!=Integer.MAX_VALUE){
                take=x+1;
            }
        }
        return dp[idx][amount] = Math.min(take, nottake);
    }
}