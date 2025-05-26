/*In Hyderabad after a long pandemic gap, the Telangana Youth festival is 
Organized at HITEX. In HITEX, there are a lot of programs planned. During 
the festival in order to maintain the rules of Pandemic, they put a 
constraint that one person can only attend any one of the programs in 
one day according to planned days.

Now it’s your aim to implement the "Solution" class in such a way that 
you need to return the maximum number of programs you can attend according 
to given constraints.

Explanation:
You have a list of programs 'p' and days 'd', where you can attend only 
one program on one day. Programs [p] = [first day, last day], 
p is the program's first day and the last day.


Input Format:
-------------
Line-1: An integer N, number of programs.
Line-2: N comma separated pairs, each pair(f_day, l_day) is separated by space.

Output Format:
--------------
An integer, the maximum number of programs you can attend.


Sample Input-1:
---------------
4
1 2,2 4,2 3,2 2

Sample Output-1:
----------------
4

Sample Input-2:
---------------
6
1 5,2 3,2 4,2 2,3 4,3 5

Sample Output-2:
----------------
5
 */

/*
    greedy approach:
    Sort events by start time.
    Use a Min-Heap to keep track of event end times.
    Iterate day by day, always choosing the event that ends the earliest.

    minHeap.poll(); → Attend the event that ends the earliest
    The priority queue (min-heap) keeps track of event end times, sorted in ascending order.
    poll() removes and returns the smallest (earliest ending) event.
    This ensures that if multiple events are available on the same day, we attend the one that finishes soonest, maximizing future attendance.
    ans++; → Increment the count of attended events
    Since we've successfully attended an event, we increase the count of attended events (ans).
    day++; → Move to the next day
    We move to the next available day (day).
    This is important because we can only attend one event per day.

 class Solution {
    public int maxEvents(int[][] events) {
        int ans =0;
        Arrays.sort(events, (a,b)->a[0]==b[0]?a[1]-b[1]:a[0]-b[0]);
        int day =0;
        int i=0;
        int n = events.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while(!pq.isEmpty()||i<n){
            if(pq.isEmpty()){
                day = events[i][0];
            }
            while(i<n && events[i][0]==day){
                pq.offer(events[i][1]);
                i++;
            }
            pq.poll();
            ans++;
            day++;
            while(!pq.isEmpty() && pq.peek()<day){
                pq.poll();
            }
        }
        return ans;
    }
}
 */

 import java.util.*;

 class SegmentTree {
     int[] tree;
     int n;
 
     public SegmentTree(int size) {
         n = size;
         tree = new int[4 * n];
         build(1, 1, n);
     }
 
     public void build(int node, int start, int end) {
         if (start == end) {
             tree[node] = 1;
             return;
         }
         int mid = (start + end) / 2;
         build(2 * node, start, mid);
         build(2 * node + 1, mid + 1, end);
         tree[node] = tree[2 * node] + tree[2 * node + 1];
     }
 
     public int query(int node, int start, int end, int L, int R) {
         if (start > R || end < L) return -1;
         if (start == end) return tree[node] == 1 ? start : -1;
 
         int mid = (start + end) / 2;
         int left = query(2 * node, start, mid, L, R);
         if (left != -1) return left;
         return query(2 * node + 1, mid + 1, end, L, R);
     } 
     public void update(int node, int start, int end, int day) {
         if (start == end) {
             tree[node] = 0;
             return;
         }
         int mid = (start + end) / 2;
         if (day <= mid) update(2 * node, start, mid, day);
         else update(2 * node + 1, mid + 1, end, day);
 
         tree[node] = tree[2 * node] + tree[2 * node + 1];
     }
 
     public int findEarliestAvailableDay(int L, int R) {
         return query(1, 1, n, L, R);
     }
 
     public void markDayOccupied(int day) {
         update(1, 1, n, day);
     }
 }
 
 public class p3 {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int N = sc.nextInt();
         sc.nextLine();
         int[][] events = new int[N][2];
 
         String[] inputEvents = sc.nextLine().split(",");
         for (int i = 0; i < N; i++) {
             String[] event = inputEvents[i].trim().split(" ");
             events[i][0] = Integer.parseInt(event[0]);
             events[i][1] = Integer.parseInt(event[1]);
         }
 
         System.out.println(maxEvents(events));
         sc.close();
     }
 
     public static int maxEvents(int[][] events) {
         Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
         int maxDay = 0;
         for (int[] event : events) maxDay = Math.max(maxDay, event[1]);
 
         SegmentTree segTree = new SegmentTree(maxDay);
         int ans = 0;
 
         for (int[] event : events) {
             int availableDay = segTree.findEarliestAvailableDay(event[0], event[1]);
             if (availableDay != -1) {
                 segTree.markDayOccupied(availableDay);
                 ans++;
             }
         }
 
         return ans;
     }
 }
 