/*A Builder builds a wall in a strange way.
The wall has to built with a width of W.
And there are N building-blocks are available with some width and height.

The wall is built as follows:
	- Pick the first few buiding-blocks and place them in the available 
	width of the wall.
	- Once no more building block in the order can't be kept in the available 
	width of the wall, place a concrete rack on the highest building-block 
	among the row of building blocks.
	- Construct the rest of the wall by repeating above two steps until
	all the blocks used.

Your task is to find the minimum possible height of the wall built 
with width W, after using all N building blocks.


Input Format:
-------------
Line-1: Two space separaed integers, N and W.
Next N lines: Two space separaed integers, width and height of the brick.

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
7 5
1 2
2 2
2 3
2 3
1 4
3 4
4 2

Sample Output-1:
----------------
11


Sample Input-2:
---------------
5 3
1 1
2 2
1 2
2 3
3 2

Sample Output-2:
----------------
7
 */
// TODO : do like decision like take this block in this row or move to next row, take 2d dp with dp[id][remWidth]
import java.util.*;
class p1{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int w = sc.nextInt();
        int blocks[][] = new int[n][2];
        for(int i=0;i<n;i++){
            blocks[i][0] = sc.nextInt();
            blocks[i][1] = sc.nextInt();
        }
        int dp[] = new int[n];
        Arrays.fill(dp,-1);
        System.out.println(find(blocks,w,0,dp));
        sc.close();
    }
    public static int find(int blocks[][],int w, int id, int dp[]){
        if(id==blocks.length){
            return 0;
        }
        if(dp[id]!=-1){
            return dp[id];
        }
        int ans = Integer.MAX_VALUE;
        int sum = 0;
        int hight = 0;
        for(int i=id;i<blocks.length;i++){
            sum += blocks[i][0];
            if(sum>w){
                break;
            }
            hight = Math.max(hight,blocks[i][1]);
            int val = find(blocks,w,i+1,dp);
            ans = Math.min(ans,hight+val);
        }
        return dp[id] = ans;
    }
}