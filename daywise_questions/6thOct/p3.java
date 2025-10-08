/* A city’s Smart Energy Controller must decide which power-saving plans to 
activate in a single day. Each plan connects to certain zones of the city 
and provides a specific energy-saving benefit (in kWh).

However, each zone can only participate in one plan at a time (to avoid power overlap).

You need to determine the maximum total energy saving that can be achieved by 
activating a valid combination of plans without any zone being used more than once.

Input Format
------------
Line-1: Two space separated integers N M: Total number of city zones, number of plans.
Next M lines: space separated integers of each plan, 
      where first integer is Number of Zones in the plan, followed by zones, and
      the last integer is energy-saving benefit.

Output Format
-------------
Return a single integer — the maximum achievable total energy saving (kWh).


Sample Input:
-------------
4 4
2 1 2 100
2 2 3 200
2 3 4 150
2 1 4 120

Sample Output:
--------------
320

Explanation:
------------
We can activate Plan-2 (zones 2,3) and Plan-4 (zones 1,4).
They do not overlap and give 200 + 120 = 320 kWh savings.
Activating any other combination gives less total saving.
*/
import java.util.*;
public class p3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] plans = new int[m][];
        for (int i = 0; i < m; i++) {
            int size = sc.nextInt();
            plans[i] = new int[size + 2];
            plans[i][0] = size;
            for (int j = 1; j < size + 2; j++) {
                plans[i][j] = sc.nextInt();
            }
        }
        System.out.println(find(plans, n, m));
    }
    public static int find(int plans[][], int n, int m){
        Arrays.sort(plans,(a,b)->b[b.length-1]-a[a.length-1]);
        boolean vis[] = new boolean[n+1];
        int ans = 0;
        for(int i=0;i<m;i++){
            boolean flag = true;
            int len = plans[i].length;
            for(int j=1;j<len-1;j++){
                if(vis[plans[i][j]]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                ans += plans[i][len-1];
                for(int j=1;j<len-1;j++){
                    vis[plans[i][j]] = true;
                }
            }
        }
        return ans;
    }
}
