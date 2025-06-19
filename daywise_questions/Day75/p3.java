/*Radhika is leading a disaster response mission and must divide her team’s 
resources into two operational units: Unit Alpha and Unit Beta.

Each resource is represented by a positive integer denoting its capacity score, 
given in the array resources[].

To ensure both units are equally capable, Radhika must split the resources 
into two non-empty ordered groups such that:
	- Every resource is assigned to exactly one unit
	- The sum of capacity scores in both units is at least k

A split is called a valid deployment plan (or great partition) if both groups have 
a total capacity ≥ k. Since the number of deployment plans can be huge, return 
the number of distinct valid deployment plans modulo 10⁹ + 7.

Two plans are considered distinct if at least one resource is assigned to a different unit in each plan.

Input Format
------------
First line: An integer k — the minimum required capacity per unit.
Second line: An integer n — the number of resources.
Third line: n space-separated integers representing resource capacities.

Output Format
---------------
A single integer: the number of valid deployment plans modulo 10⁹ + 7

Constraints
-----------
1 ≤ n, k ≤ 1000
1 ≤ resources[i] ≤ 10

Sample Input
--------------
4
4
1 2 3 4

Sample Output
---------------
6

Explanation
------------
These are the valid deployment plans (where each group has sum ≥ 4):
[1, 2, 3] and [4]
[1, 3] and [2, 4]
[1, 4] and [2, 3]
[2, 4] and [1, 3]
[2, 3] and [1, 4]
[4] and [1, 2, 3]


Sample Input
--------------
4
4
1 2 1 2

Sample Output
---------------
0
 */
import java.util.*;
class Solution{
    static int MOD = (int)(1e9+7);
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        int arr[] = new int[n];
        int sum = 0;
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        if(sum/2<k){
            System.out.println(0);
            return;
        }
        int dp[][][] = new int[n][sum+1][sum+1];
        for(int i=0;i<n;i++){
            for(int j=0;j<=sum;j++){
                Arrays.fill(dp[i][j],-1);
            }
        }
        System.out.println(find(0,0,0,arr,k,dp)%MOD);
    }
    public static int find(int id,int sum1, int sum2,int arr[],int k,int dp[][][]){
        if(id>=arr.length ){
            if(sum1>=k && sum2>=k)return 1;
            return 0;
        }
        if(dp[id][sum1][sum2]!=-1)return dp[id][sum1][sum2];
        int take = find(id+1,sum1+arr[id],sum2,arr,k,dp);
        int nottake = find(id+1,sum1,sum2+arr[id],arr,k,dp);
        return dp[id][sum1][sum2] = (take+nottake)%MOD;
        
    }
}