/*You are working in a genetics laboratory where you are tasked with correcting 
DNA sequences. Each DNA strand is represented as a sequence of characters 
'A', 'C', 'G', and 'T'.
Sometimes, due to mutations or errors during sequencing, the DNA strand (originalDNA) 
must be modified to match a targetDNA sequence exactly.

You can perform the following mutation operations:
- Insert a nucleotide (A, C, G, or T) into the DNA strand.
- Delete a nucleotide from the DNA strand.
- Replace a nucleotide with another one.

Each operation counts as one step.

Your task is to find the minimum number of mutation operations needed to 
transform the originalDNA into the targetDNA.

Input format:
-------------
2 space seperated strings, originalDNA and targetDNA

Output format:
--------------
An integer, the minimum number of mutation operations


Example 1:
-----------
Input:
ACT AGT

Output:
1

Explanation:
Delete 'C': "ACGT" → "AGT"
Only 1 mutation is needed.

Example 2:
----------
Input:
GATTAC GCATGCU

Output:
4

Explanation:
- Replace 'A' with 'C': "GATTAC" → "GCTTAC"
- Replace 'T' with 'A': "GCTTAC" → "GCATAC"
- Replace 'A' with 'G': "GCATAC" → "GCATGC"
- Insert 'U' at the end: "GCATGC" → "GCATGCU"

Thus, 4 mutations are needed.
 */
import java.util.*;
class Solution{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s[] = sc.nextLine().split(" ");
        String s1 = s[0];
        String s2 = s[1];
        int n1 = s1.length();
        int n2 = s2.length();
        int dp[][] = new int[n1][n2];
        for(int i=0;i<n1;i++){
            Arrays.fill(dp[i],-1);
        }
        System.out.println(find(0,0,s1,s2,dp));
        
    }
    public static int find(int i, int j, String s1, String s2, int dp[][]){
        if(i==s1.length())return s2.length()-j;
        if(j==s2.length()){
            return s1.length()-i;
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        if(s1.charAt(i)==s2.charAt(j)){
            return dp[i][j] = find(i+1,j+1,s1,s2,dp);
        }
        int delete = find(i+1,j,s1,s2,dp)+1;
        int insert = find(i,j+1,s1,s2,dp)+1;
        int replace = find(i+1,j+1,s1,s2,dp)+1;
        return dp[i][j] = Math.min(delete,Math.min(insert,replace));
        

    }
}