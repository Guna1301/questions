/*You have given flatland in the form of m*n grid,
The land contains some ponds represented as 0,
and the land represented as 1.

Your task is to find the number of square-shaped lands which contains no pond. 


Input Format:
-------------
Line-1: Two integers M and N.
Next M lines: N space separated integers.

Output Format:
--------------
Print an integer, number of square-grids.


Sample Input-1:
---------------
3 4
0 1 1 1
1 1 1 1
0 1 1 1

Sample Output-1:
----------------
15

Explanation:
------------
There are 10 square-grids of side length 1.
There are 4 square-grids of side length 2.
There is 1 square-grid of side length 3.

Total number of square-grids without a pond in it = 10 + 4 + 1 = 15.


Sample Input-2:
---------------
3 3
1 0 1
1 1 0
1 1 0

Sample Output-2:
----------------
7

Explanation:
------------
There are 6 square-grids of side length 1.
There is 1 square-grid of side length 2.
Total number of square-grids without a pond in it = 6 + 1 = 7. */
import java.util.*;
class p1{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int grid[][] = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                grid[i][j] = sc.nextInt();
            }
        }
        int ans = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    ans += find(i,j,grid);
                }
            }
        }
        System.out.println(ans);
    }
    public static int find(int i,int j, int grid[][]){
        int m = grid.length;
        int n = grid[0].length;
        if(i>=m || j>=n){
            return 0;
        }
        if(grid[i][j]==0){
            return 0;
        }
        int right = find(i,j+1,grid);
        int down = find(i+1,j,grid);
        int diag = find(i+1,j+1,grid);
        return 1+Math.min(right,Math.min(down,diag));
    }
    
}