/*Liam is a textile artist creating a patchwork quilt. He has X types of uniquely 
sized fabric patches. Each patch has a specific number of design squares on it. 
Liam wants to create a perfect square pattern on the quilt using these patches. 
He can use any number of patches of each type.

However, there are some square counts that he cannot achieve with these patches. 
Your task is to help Liam figure out the maximum number of design squares that 
cannot be formed using any combination of the available patch types.

Note: The number of design squares on any two different patches is co-prime.

Input Format:
-------------
input1: An integer X representing the number of different patch types available.
input2: An integer array representing the number of design squares each patch type contains.

Output Fromat:
--------------
Return an integer value representing the maximum number of design squares that 
cannot be created on the quilt using the given patch types.

Sample Input:
-------------
2
3 5

Sample Output:
--------------
7

Explanation:
------------
Using patches with 3 and 5 design squares, the largest number of design squares 
that cannot be formed by any combination is 7.


Sample Input:
-------------
4
3 7 17 29

Sample Output:
--------------
11
 */

import java.util.*;
class Solution{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int patches[] = new int[n];
        for(int i=0;i<n;i++){
            patches[i] = sc.nextInt();
        }
        System.out.println(find(patches));
    }
    public static int find(int patches[]){
        int limit = 100000;
        boolean reach[] = new boolean[limit+1];
        reach[0] = true;
        for(int i=0;i<=limit;i++){
            if(reach[i]){
                for(int patch:patches){
                    if(i+patch<=limit){
                        reach[i+patch] = true;
                    }
                }
            }
        }
        for(int i=limit;i>=0;i--){
            // System.out.print(reach[i]+" ");
            if(!reach[i]){
                return i;
            }
        }
        return 0;
    }
}