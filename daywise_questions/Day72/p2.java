/*Gopal would like to go on Tour, and planned a schedule.
Airport authority has created a new way of issuing tickets.
Gopal purchased a set of airline tickets, 
each ticket contains the 'departure from' and 'arrival to'.

Redesign the Gopal's tour schedule in an order.
Gopal intially must begin his tour from BZA.
Hence the tour schedule's start point should begin with BZA. 

You are given a list of flight tickets which Gopal has purchased.
Your task is to find out and return the tour schedule that has the least 
lexical order. Gopal must use all the tickets and only once.

Note:
------
If there are multiple valid schedules, you should return the schedule 
that has the smallest lexical order when read as a single string. 
For example, the schedule ["BZA", "LGA"] has a smaller lexical order than ["BZA", "LGB"].

All airports are represented by three capital letters.
You may assume all tickets form at least one valid schedule.

Input Format:
-------------
Single Line of tickets separated by comma, and each ticket separated by space, 
Gopal's flight tickets.

Output Format:
--------------
Print the schedule, which is smallest lexical order of tour schedule.


Sample Input-1:
----------------
DEL HYD,BZA DEL,BLR MAA,HYD BLR

Sample Output-1:
--------------------
[BZA, DEL, HYD, BLR, MAA]


Sample Input-2:
------------------
BZA BLR,BZA CCU,BLR CCU,CCU BZA,CCU BLR

Sample Output-2:
------------------
[BZA, BLR, CCU, BZA, CCU, BLR]
 */
import java.util.*;
class Solution{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String edges[] = sc.nextLine().split(",");
        Map<String,PriorityQueue<String>> graph = new HashMap<>();
        Map<String,Boolean> vis = new HashMap<>();
        for(String edge:edges){
            String s[] = edge.split(" ");
            String u = s[0];
            String v = s[1];
            if(!graph.containsKey(u)){
                graph.put(u,new PriorityQueue<>());
            }
            vis.put(u,false);
            graph.get(u).offer(v);
        }
        int n = graph.size();
        
        List<String>path = new ArrayList<>();
        find("BZA",n,graph,path);
        Collections.reverse(path);
        System.out.println(path);
    }
    public static void find(String node,int n,Map<String,PriorityQueue<String>> graph,List<String> path ){
        PriorityQueue<String> pq = graph.get(node);
        while(pq!=null&&!pq.isEmpty()){
            String curr = pq.poll();
            find(curr,n,graph,path);
        }
        path.add(node);
        
       
    }
}