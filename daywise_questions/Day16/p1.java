/*
 * There are N cities, and M routes[], each route is a path between two cities.
routes[i] = [city1, city2], there is a travel route between city1 and city2.
Each city is numbered from 0 to N-1.
 
There are one or more Regions formed among N cities. 
A Region is formed in such way that you can travel between any two cities 
in the region that are connected directly and indirectly.
 
Your task is to findout the number of regions formed between N cities. 
 
Input Format:
-------------
Line-1: Two space separated integers N and M, number of cities and routes
Next M lines: Two space separated integers city1, city2.
 
Output Format:
--------------
Print an integer, number of regions formed.
 
 
Sample Input-1:
---------------
5 4
0 1
0 2
1 2
3 4
 
Sample Output-1:
----------------
2
 
 
Sample Input-2:
---------------
5 6
0 1
0 2
2 3
1 2
1 4
2 4
 
Sample Output-2:
----------------
1
 
Note: Look HINT for explanation.

 */
import java.util.*;

class dsu{
    int parent[];
    dsu(int n){
        parent = new int[n];
        for(int i=0;i<n;i++){
            parent[i] = i;
        }
    }
    public int find(int x){
        if(parent[x]!=x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    public boolean union(int a, int b){
        int roota = find(a);
        int rootb = find(b);
        if(roota==rootb){
            return false;
        }
        parent[roota] = rootb;
        parent[rootb] = rootb;
        return true;
    }
}
class p1{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        // boolean vis[] = new boolean[n];
        // Map<Integer,List<Integer>> map = new HashMap<>();
        // for(int i=0;i<m;i++){
        //     int a  = sc.nextInt();
        //     int b = sc.nextInt();
        //     map.putIfAbsent(a,new ArrayList<>());
        //     map.get(a).add(b);
        //     map.putIfAbsent(b,new ArrayList<>());
        //     map.get(b).add(a);
            
        // }
        
        // int ans = 0;
        // for(int i=0;i<n;i++){
        //     if(!vis[i]){
        //         dfs(i,map,vis);
        //         ans++;
        //     }
        // }
        // System.out.println(ans);
        int graph[][]  = new int[m][2];
        for(int i=0;i<m;i++){
            graph[i][0] = sc.nextInt();
            graph[i][1] = sc.nextInt();
        }
        Set<Integer> set = new HashSet<>();
        dsu u = new dsu(n);
        for(int i=0;i<m;i++){
            u.union(graph[i][0], graph[i][1]);
            
        }
        for(int i=0;i<n;i++){
            set.add(u.find(i));
        }
        System.out.println(set.size());
        sc.close();
    }
    // public static void dfs(int i, Map<Integer,List<Integer>> map, boolean vis[]){
    //     vis[i] = true;
    //     if(!map.containsKey(i)){
    //         return;
    //     }
    //     for(int val : map.get(i)){
    //         if(!vis[val]){
    //             dfs(val,map,vis);
    //         }
    //     }
    // }
}