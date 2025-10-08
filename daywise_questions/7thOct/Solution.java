import java.util.*;
class Solution{
    static int m, n, e, r;
    static int[][] grid;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        e = sc.nextInt();
        r = sc.nextInt();
        grid = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                grid[i][j] = sc.nextInt();
            }
        }
        System.out.println(solve(0,0,e));
    }

    public static int solve(int i, int j, int en){
        if(i>=m || j>=n) return (int)1e9;

        int cell = grid[i][j];

        if(i == m-1 && j == n-1){
            if(en < cell) return r + cell;
            else return cell;
        }

        int rem = en - cell;

        int right = (j+1<n) ? 0 : (int)1e9;
        int down = (i+1<m) ? 0 : (int)1e9;

        if(j+1<n){
            int rigthen = rem;
            int cost = 0;
            if(rigthen < grid[i][j+1]){
                cost += r;
                rigthen = e;
            }
            right = cost + solve(i, j+1, rigthen) + cell;
        }

        if(i+1<m){
            int downen = rem;
            int cost = 0;
            if(downen < grid[i+1][j]){
                cost += r;
                downen = e;
            }
            down = cost + solve(i+1, j, downen) + cell;
        }

        return Math.min(right, down);
    }
}
