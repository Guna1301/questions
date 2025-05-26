/*
 * A Kid built a structure using building blocks, 
 by placing the building-blocks adjacent to each other.
 
 A building-block is a vertical alignment of blocks.
                                ___
 here one block each represents  as |___|.
 
 The following structure made up of using building blocks
 
                           ___
                       ___|___|    ___
                      |___|___|_w_|___|___              ___
                   ___|___|___|___|___|___| w   _w_  w |___| 
               ___|___|___|___|___|___|___|_w__|___|_w_|___|____________
     
                0  1   3   4   2   3    2   0   1   0   2
 
 Once the structure is completed, kid pour water(w) on it.
 
 You are given a list of integers, heights of each building-block in a row.
  Now your task How much amount of water can be stored by the structure.
 
  Input Format:
  -------------
  Space separated integers, heights of the blocks in the structure. 
 
 Output Format:
  --------------
  Print an integer, 
   
 Sample Input:
 -------------
  0 1 3 4 2 3 2 0 1 0 2
     
 Sample Output:
 --------------
 6
     
 Explanation:
  -----------
 In the above structure,  6 units of water (w represents the water in the structure)
 can be stored.
 
 */
import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<arr.length; i++){
            arr[i] = sc.nextInt();
        }
        System.out.println(find(arr));
    }
    static int find(int[] arr){
        int n = arr.length;
        int leftmax = 0;
        int rightmax = 0;
        int l = 0, r=n-1, ans = 0;
        while(l<=r){
            if(arr[l]<=arr[r]){
                if(leftmax > arr[l]){
                   ans+=leftmax - arr[l]; 
                }else{
                    leftmax = arr[l];
                }
                l++;
            }else{
                if(rightmax > arr[r]){
                    ans+=rightmax - arr[r];
                }else{
                    rightmax = arr[r];
                }
                r--;
            }
        }
        return ans;
    }
}