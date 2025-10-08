/*You are working on a CPU workload optimizer for a gaming computer.
The processor executes a series of N operations, each represented by a numeric ID.
Every operation takes exactly 1 unit of time to execute.

However, due to thermal throttling, if the same operation type is executed 
repeatedly, the CPU must cool down for K time units before executing that 
same operation ID again. Different operations can be executed during 
the cooling period.

Your goal is to find the minimum total time required for the CPU to execute 
all operations without overheating.

Input Format
------------
Line-1: Two integers N and K representing the cool-down time between two identical operations.
Line-2: N space seperated integers representing operation IDs.

Output Format
-------------
Return a single integer — the minimum total time needed to finish all operations.

Constraints
-----------
1 ≤ number of operations ≤ 10 000
0 ≤ k ≤ 100
Operation IDs are positive integers


Sample Input:
-------------
4 2
1 2 1 1

Sample Output:
--------------
7


Explanation:
------------
The CPU runs operations in the order below (using _ to denote idle/
cool-down slots): 
1 → 2 → idle → 1 → idle → idle → 1
Total = 7 units */
import java.util.*;
public class p2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(find(arr, n, k));
    }
    public static int find(int[] arr, int n, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int id : arr) {
            freq.put(id, freq.getOrDefault(id, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        pq.addAll(freq.values());
        Queue<int[]> q = new LinkedList<>();
        int ans = 0;
        while (!pq.isEmpty() || !q.isEmpty()) {
            ans++;
            if (!pq.isEmpty()) {
                int count = pq.poll() - 1;
                if (count > 0) {
                    q.offer(new int[]{count, ans+k});
                }
            }
            if (!q.isEmpty() && q.peek()[1] == ans) {
                pq.offer(q.poll()[0]);
            }
        }
        return ans;
    }
}
