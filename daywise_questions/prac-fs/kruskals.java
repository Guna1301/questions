/*Budget Padmanabham planned to visit the tourist places. There are N tourist 
places in the city. The tourist places are numbered from 1 to N.

You are given the routes[] to travel between the tourist places in the city.
where routes[i]=[place-1, place-2, price], A route is a bi-directional route.
You can travel from place-1 to place-2 or place-2 to place-1 with the given price.
 
Your task is to help Budget Padmanabham to find the cheapest route plan, to 
visit all the tourist places in the city. If you are not able to find such plan, 
print -1.
 
Input Format:
-------------
Line-1: Two space separated integers N and R,number of places and routes.
Next R lines: Three space separated integers, start, end and price.
  
Output Format:
--------------
Print an integer, minimum cost to visit all the tourist places.
 
 
Sample Input-1:
---------------
4 5
1 2 3
1 3 5
2 3 4
3 4 1
2 4 5
 
Sample Output-1:
----------------
8
 
Explanation:
------------
 The cheapest route plan is as follows:
 1-2, 2-3, 3-4 and cost is 3 + 4 + 1 = 8
 
 
Sample Input-2:
---------------
4 3
1 2 3
1 3 5
2 3 4
 
Sample Output-2:
----------------
-1
 */

import java.util.*;
class dsu{
    int parent[];
    int rank[];
    dsu(int n){
        parent = new int[n+1];
        rank = new int[n+1];
        for(int i=0;i<n;i++){
            parent[i] = i;
            rank[i]=0;
        }
    }
    public int find(int x){
        if(parent[x]!=x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    public void union(int a, int b){
        int roota = find(a);
        int rootb = find(b);
        if(roota==rootb){
            return;
        }
        if(rank[a]>rank[b]){
            parent[rootb] = roota;
        }else if(rank[b]>rank[a]){
            parent[roota] = rootb;
        }else{
            parent[rootb] = roota;
            rank[roota]++;
        }
    }
}
class kruskals{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int r = sc.nextInt();
        int grid[][] = new int[r][3];
        for(int i=0;i<r;i++){
            grid[i][0] = sc.nextInt();
            grid[i][1] = sc.nextInt();
            grid[i][2] = sc.nextInt();
        }
        sc.close();
        dsu u = new dsu(n);
        Arrays.sort(grid,(a,b)->a[2]-b[2]);
        int cost = 0;
        int count = 0;
        for(int arr[]:grid){
            int roota = u.find(arr[0]);
            int rootb = u.find(arr[1]);
            if(roota!=rootb){
                u.union(arr[0],arr[1]);
                cost += arr[2];
                count++;
            }
            if(count == n-1){
                break;
            }
        }
        if(count!=n-1){
            cost = -1;
        }
        System.out.println(cost);
    }
}