/*A group of researchers is analyzing satellite imagery of agricultural fields, 
represented by a grid of land sections. Each section is marked either as 
fertile (1) or infertile (0). To efficiently plan crop planting, the researchers 
need to identify the largest rectangular area consisting exclusively of fertile 
land sections.

Given a binary grid representing the land (1 for fertile and 0 for infertile), 
your task is to compute the area of the largest rectangle consisting entirely 
of fertile land sections.

Input Format:
-------------
Line-1: Two integers rows(r) and columns(c) of grid.
Next r lines: c space separated integers, each row of the grid.

Output Format:
--------------
Print an integer, area of the largest rectangle consisting entirely of fertile land sections.

Example:
--------
input=
4 5
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

output=
6 */

import java.util.*;
class Solution{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        int grid[][] = new int[r][c];
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                grid[i][j] = sc.nextInt();
            }
        }
        int heights[] = new int[c];
        for(int i=0;i<c;i++){
            heights[i] = grid[0][i];
        }
        int ans = find(heights);
        for(int i=1;i<r;i++){
            for(int j=0;j<c;j++){
                int val = grid[i][j];
                heights[j] = (heights[j]*val+val);
            }
            ans = Math.max(ans,find(heights));
        }

        System.out.println(ans);
    }
    public static int find(int heights[]){
        int n = heights.length;
        int pses[] = new int[n];
        int nses[] = new int[n];
        solve(heights,pses,nses);
        int area = 0;
        for(int i=0;i<n;i++){
            area = Math.max(area,heights[i]*(nses[i]-pses[i]-1));
        }
        return area;
    }
    public static void solve(int heights[],int pses[], int nses[] ){
        Stack<Integer> ps = new Stack<>();
        Stack<Integer> ns = new Stack<>();
        int n = heights.length;
        for(int i=0;i<n;i++){
            while(!ps.isEmpty() && heights[ps.peek()]>=heights[i]){
                ps.pop();
            }
            pses[i] = ps.isEmpty()?-1:ps.peek();
            ps.push(i);
        }
        for(int i=n-1;i>=0;i--){
            while(!ns.isEmpty() && heights[ns.peek()]>=heights[i]){
                ns.pop();
            }
            nses[i] = ns.isEmpty()?n:ns.peek();
            ns.push(i);
        }
    }
}