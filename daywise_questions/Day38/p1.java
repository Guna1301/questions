/*You are a stealthy archaeologist exploring a circular ring of ancient tombs 
located deep within a jungle. Each tomb holds a certain number of precious 
artifacts. 
However, these tombs are protected by an ancient magical curse: 
if you disturb two adjacent tombs during the same night, the entire ring 
activates a trap that seals you in forever.

The tombs are arranged in a perfect circle, meaning the first tomb is adjacent 
to the last. You must plan your artifact retrieval carefully to maximize the 
number of artifacts collected in a single night without triggering the curse.

Given an integer array  artifacts  representing the number of artifacts in each 
tomb, return the   maximum   number of artifacts you can collect without 
disturbing any two adjacent tombs on the same night.

Example 1:  
Input:
2 4 3
Output:  
4   

Explanation: You cannot loot tomb 1 (artifacts = 2) and tomb 3 (artifacts = 3), 
as they are adjacent in a circular setup.


Example 2:  
Input:
1 2 3 1
Output:  
4

Explanation: You can loot tomb 1 (1 artifact) and tomb 3 (3 artifacts) without 
breaking the ancient rule.  
Total = 1 + 3 = 4 artifacts.


Example 3:  
Input:
1 2 3
Output:  
3 

Constraints:  
-  1 <= artifacts.length <= 100 
-  0 <= artifacts[i] <= 1000 
 */
import java.util.*;
class Solution{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String vals[] = sc.nextLine().split(" ");
        int n = vals.length;
        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(vals[i]);
        }
        int dp[] = new int[n];
        Arrays.fill(dp,-1);
        
        int ans = find(n-1,1,arr,dp);
        Arrays.fill(dp,-1);
        ans = Math.max(ans,find(n-2,0,arr,dp));
        System.out.println(ans);
    }
    public static int find(int id,int end, int arr[],int dp[]){
        if(id<end){
            
            return 0;
        }
        if(dp[id]!=-1){
            return dp[id];
        }
        int take = arr[id]+find(id-2,end,arr,dp);
        int nottake = find(id-1,end,arr,dp);
        return dp[id] =  Math.max(take,nottake);
    }
}