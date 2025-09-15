/* Given an infinite number of coins with values 1, 2, and 6, and only 2 coins with value 4 and an integer n, 
return the number of ways to make the sum n using the available coins.
Since the result may be very large, return it modulo 109 + 7.
Input: n = 5  
Output: 4

Explanation:
Here are the valid combinations:
[1, 1, 1, 1, 1]
[1, 1, 1, 2]
[1, 2, 2]
[4,1]

Input: n = 10  
Output: 16

Explanation:
Some valid combinations include:
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
[2, 2, 2, 2, 2]
[6, 2, 2]
[4, 4, 2] (Valid since 4 can be used at most twice)
[6, 4]
[2, 2, 2, 2, 1, 1]
[1, 1, 1, 1, 6]
idi chesi ela cheyalo chepava*/

import java.util.*;

class adobe_pq_1 {
    static int mod = 1_000_000_007;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] coins = {6,4,2,1};
        int dp[][][] = new int[n + 1][4][3];
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j < 4; j++){
                Arrays.fill(dp[i][j], -1);
            }
                
        }
        System.out.println(find(n, coins, 0, 2, dp));
    }

    public static int find(int n, int[] coins, int id, int count, int dp[][][]) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        if(dp[n][id][count] != -1) return dp[n][id][count];
        int ans = 0;
        for (int i = id; i < coins.length; i++) {
            if (coins[i] == 4) {
                if (count > 0)
                    ans = (ans + find(n - 4, coins, i, count-1,dp)) % mod;
            } else {
                ans = (ans + find(n - coins[i], coins, i, count,dp)) % mod;
            }
        }
        return dp[n][id][count] = ans % mod;
    }
}

