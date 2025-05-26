/*You are participating in a futuristic space exploration mission where you must 
navigate through a series of planets aligned in a straight line.
The planets are numbered from 0 to n-1, and you start your journey on planet 0.

You are given a 0-indexed array planets, where each element planets[i] represents 
the maximum number of planets you can move forward from planet i. In simpler terms, 
standing on planet i, you can move to any planet from i+1 to i+planets[i], 
as long as you don't exceed the last planet.

Your goal is to reach the final planet (planet n-1) in the fewest number of 
moves possible.

It is guaranteed that a path to the final planet always exists.

Return the minimum number of moves needed to reach planet n-1.

Example 1
----------
Input:
2 3 1 0 4
Output:
2

Explanation:
- Move from planet 0 to planet 1.
- Move from planet 1 to planet 4 (last planet).
 */
import java.util.*;
class p1{
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
        System.out.println(find(0,arr,dp));
    }
    public static int find(int id, int arr[],int dp[]){
        if(id>=arr.length-1){
            return 0;
        }
        if(dp[id]!=-1)return dp[id];
        int ans = 1000000009;
        for(int i=1;i<=arr[id];i++){
            if(i+id>arr.length)continue;
            int val = 0;
            val+= find(id+i,arr,dp)+1;
            ans = Math.min(ans,val);
        }
        return dp[id] = ans;
    }
}