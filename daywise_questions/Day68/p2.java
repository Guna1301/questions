/*Alex and his twin brother Jordan often create secret messages. One day, Jordan 
gives Alex two encrypted messages and challenges him to find the longest common 
palindromic pattern hidden within both messages.

Alex wants your help to decode the longest common palindromic subsequence that 
exists in both strings.

Your task is to determine the length of the longest subsequence that:
- Appears in both messages
- Is a palindrome

Input Format:
-------------
input1: A string representing the first encrypted message.
input2: A string representing the second encrypted message.

Output Fromat:
--------------
Return an integer representing the length of the longest common palindromic 
subsequence shared by both messages.


Sample Input: 
-------------
adfa
aagh

Sample Output:
--------------
2


Sample Input-2:
---------------
abcda
fxaaba

Sample Output:
--------------
3

Explanation:
------------
The longest palindromic subsequence common to both is "aba" with length 3.

 */
import java.util.*;

class p2 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        int n1 = s1.length();
        int n2 = s2.length();

        int[][] dp = new int[n1][n2];
        for (int[] row : dp) Arrays.fill(row, -1);

        find(n1 - 1, n2 - 1, s1, s2, dp);

        String lcs = build(s1, s2, n1 - 1, n2 - 1, dp);
        
        int n = lcs.length();
        int[][] memo = new int[n][n];
        for (int[] row : memo) Arrays.fill(row, -1);

        int len = length(lcs, 0, n - 1, memo);
        System.out.println(len);
    }

    public static int find(int i, int j, String s1, String s2, int[][] dp) {
        if (i < 0 || j < 0) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            dp[i][j] = 1 + find(i - 1, j - 1, s1, s2, dp);
        } else {
            dp[i][j] = Math.max(find(i - 1, j, s1, s2, dp), find(i, j - 1, s1, s2, dp));
        }
        return dp[i][j];
    }

    public static String build(String s1, String s2, int i, int j, int[][] dp) {
        StringBuilder sb = new StringBuilder();

        while (i >= 0 && j >= 0) {
            if (s1.charAt(i) == s2.charAt(j)) {
                sb.append(s1.charAt(i));
                i--;
                j--;
            } else {
                int up = (i > 0) ? dp[i - 1][j] : 0;
                int left = (j > 0) ? dp[i][j - 1] : 0;
                if (up >= left) {
                    i--;
                } else {
                    j--;
                }
            }
        }

        return sb.reverse().toString();
    }

    public static int length(String s, int i, int j, int[][] memo) {
        if (i > j) return 0;
        if (i == j) return 1;
        if (memo[i][j] != -1) return memo[i][j];

        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = 2 + length(s, i + 1, j - 1, memo);
        } else {
            memo[i][j] = Math.max(length(s, i + 1, j, memo), length(s, i, j - 1, memo));
        }
        return memo[i][j];
    }
}
