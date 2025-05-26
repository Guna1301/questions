package Day3;
/*
 * You are given an integer array nums and two integers l and r. Your task is to 
 analyze the volatility of a sequence of values. The volatility of a sequence is 
 defined as the difference between the maximum and minimum values in that sequence.
 
 You need to determine the sequence with the highest volatility among all 
 sequences of length between l and r (inclusive).
 
 Return the highest volatility. If no such sequence exists, return -1.
 
 Input Format:
 -------------
 Line-1: 3 space separated integers, n, l, r
 Line-2: n space separated integers, nums[].
 
 Output Format:
 -------------
 An integer, the highest volatility.
 
 
 Sample Input-1:
 ---------------
 5 2 3
 8 3 1 6 2
 
 Sample Output-1:
 ----------------
 7
 
 Explanation:
 ------------
 The possible sequences of length between l = 2 and r = 3 are:
 
 [8, 3] with a volatility of 8−3=5
 [3, 1] with a volatility of 3−1=2
 [1, 6] with a volatility of 6−1=5
 [8, 3, 1] with a volatility of 8−1=7
 The sequence [8, 3, 1] has the highest volatility of 7.
 
 Sample Input-2:
 ---------------
 4 2 4
 5 5 5 5
 
 Sample Output-2:
 ----------------
 0
 
 Explanation:
 ------------
 All possible sequences have no volatility as the maximum and minimum values 
 are the same, resulting in a difference of 0.
  
 
 */

import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        int ans = 0; 
        
        // for(int i=l; i<=r; i++){
        //     int maxi = Integer.MIN_VALUE;
        //     int mini = Integer.MAX_VALUE;
        //     for(int j=0; j<i; j++){
        //         maxi = Math.max(maxi, arr[j]);
        //         mini = Math.min(mini, arr[j]);
        //     }
        //     ans= Math.max(ans, maxi-mini);
        //     for(int j=i; j<arr.length; j++){
        //       int x = findvoi(j-i+1, j, arr);
        //       ans = Math.max(x, ans);
        //     }
        // }
        PriorityQueue<Integer> maxi = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> mini = new PriorityQueue<>();
        for(int j=0; j<r; j++){
                maxi.add(arr[j]);
                mini.add(arr[j]);
        }
        ans = Math.max(maxi.peek()-mini.peek(), ans);
        for(int j=r; j<arr.length; j++){
            maxi.remove(arr[j-r]);
            mini.remove(arr[j-r]);
            maxi.add(arr[j]);
            mini.add(arr[j]);
            ans = Math.max(ans, maxi.peek()-mini.peek());
        }
        System.out.println(ans);
    }
    // static int findvoi(int i, int j, int[] arr){
    //     int mini = Integer.MAX_VALUE;
    //     int maxi = Integer.MIN_VALUE;
    //     for(int k=i; k<=j; k++){
    //         mini = Math.min(mini, arr[k]);
    //         maxi = Math.max(maxi, arr[k]);
    //     }
    //     return maxi-mini;
    // }
}