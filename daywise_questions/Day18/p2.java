/*
 * Pranav has a puzzle board filled with square boxes in the form of a grid. Some 
 cells in the grid may be empty. '0' - indicates empty, '1' - indicates a box. 
 
 The puzzle board has some patterns formed with boxes in it, 
 the patterns may be repeated. The patterns are formed with boxes (1's) only, 
 that are connected horizontally and vertically but not diagonally.
 
 Pranav wants to find out the number of unique patterns in the board.
 
 You are given the board in the form of a grid M*N, filled wth 0's and 1's.
 Your task is to help Pranav to find the number of unique patterns in 
 the puzzle board.
 
 Input Format:
 -------------
 Line-1: Two integers M and N, the number of rows and columns in the grid-land.
 Next M lines: contains N space-separated integers [0, 1].
 
 Output Format:
 --------------
 Print an integer, the number of unique patterns in the puzzle board.
 
 
 Sample Input-1:
 ---------------
 5 5
 0 1 0 1 1
 1 1 1 0 1
 0 1 0 1 0
 1 0 1 1 1
 1 1 0 1 0
 
 Sample Output-1:
 ----------------
 3
 
 Explanation-1:
 ------------
 The unique patterns are as follows:
   1			1 1	    1 
 1 1 1		  1 ,	1 1
   1	   ,	
    
    
 Sample Input-2:
 ---------------
 6 6
 1 1 0 0 1 1
 1 0 1 1 0 1
 0 1 0 1 0 0
 1 1 0 0 0 1
 0 0 1 0 1 1
 1 1 0 1 0 0
 
 Sample Output-2:
 ----------------
 5
 
 Explanation-2:
 ------------
 The unique patterns are as follows:
 1 1		1 1		    1		1 1	,	1
 1   ,     1 ,	    1 1 ,		
 
 
 */

import java.util.*;
public class p2{
    static HashSet<String> hs = new HashSet<>();
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m =sc.nextInt();
        int[][] arr = new int[n][m];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                arr[i][j] = sc.nextInt();
            }
        }
        boolean[][] vis = new boolean[n][m];
        for(int i =0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(arr[i][j]==1 && !vis[i][j]){
                    hs.add(bfs(arr,i,j, vis));
                }
            }
        }
        System.out.println(hs.size());
    }
    static String bfs(int[][] arr,int i, int j, boolean[][] vis){
        int n = arr.length;
        int m = arr[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i,j});
        vis[i][j] = true;
        StringBuilder sb = new StringBuilder();
        int[] drow = {-1,0,1,0};
        int[] dcol = {0,1,0,-1};
        int baserow = i;
        int basecol = j;
        while(!q.isEmpty()){
            int[] p = q.poll();
            int r = p[0];
            int c = p[1];
            sb.append((r-baserow)+" "+ (c-basecol));
            for(int k = 0; k<4; k++){
                int nr = r+drow[k];
                int nc = c+dcol[k];
                if(nr>=0 && nr<n && nc>=0 && nc<m && arr[nr][nc]==1 && !vis[nr][nc]){
                    q.add(new int[]{nr,nc});
                    vis[nr][nc] = true;
                }
            }
        }
        return sb.toString();
    }
    
}