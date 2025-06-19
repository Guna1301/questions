/*Bablu is playing with Magnets and Iron balls.
Bablu has given a flat surface of m*n size, 
each position contains either empty '0', an Iron ball 'B' 
or Wooden Block 'W' (The wooden block is an anti-magnetic agent), 

Your task is to help Bablu to find the maximum number of 
Iron Balls he can attract by using a Magnet.

The Magnet attracts all the iron balls in the same row and column 
from their positions until the wooden block.
since the wooden block is an anti-magnetic block.

Note: You can only put the magnet in an empty position.

Input Format:
-------------
Line-1: Two integers m and n, size of the surface.
Next 'm' lines:  'n' space-separated characters only ('0', 'B', 'W').

Output Format
--------------
Print an integer, the maximum number of Iron Balls can be attracted by using a Magnet


Sample Input-1:
----------------
3 4
0 B 0 0 
B 0 W B
0 B 0 0

Sample Output:
--------------
3 

Explanation:
------------
For the given grid,
	0 B 0 0 
	B 0 W B
	0 B 0 0
Placing a Magnet at (1,1) attacts 3 iron balls. 

 */
import java.util.*;
class Solution{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        sc.nextLine();
        char grid[][] = new char[m][n];
        for(int i=0;i<m;i++){
            String s[] = sc.nextLine().split(" ");
            for(int j=0;j<n;j++){
                grid[i][j] = s[j].charAt(0);
            }
        }
        int ans = 0;
        int dp[][] = new int[m][n];
        for(int i=0;i<m;i++){
            Arrays.fill(dp[i],-1);
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]=='0'){
                    int count = dfs(i,j,i,j,grid);
                    // System.out.println(i+" "+j+" "+count);
                    ans = Math.max(ans,count);
                }
            }
        }
        System.out.println(ans);
    }
    public static int dfs(int i, int j, int row, int col, char grid[][]){
        int m = grid.length;
        int n = grid[0].length;
        if(i<0 || i>=m || j<0 || j>=n || grid[i][j]=='W' || grid[i][j]=='#' || (i!=row && j!=col)){
            return 0;
        }
        int count = 0;
        if(grid[i][j]=='B'){
            count++;
        }
        char pres = grid[i][j];
        grid[i][j]='#';
        count += dfs(i+1,j,row,col,grid);
        count += dfs(i,j+1,row,col,grid);
        count += dfs(i-1,j,row,col,grid);
        count += dfs(i,j-1,row,col,grid);
        grid[i][j] = pres;
        return count;
    }
}