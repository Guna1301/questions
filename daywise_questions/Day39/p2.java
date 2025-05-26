/*Amogh is an Antiquarian, The person who collects antiques.
He found a rear keyboard which has following keys,
Keys are 'N', 'S', 'C' and 'P'

1st Key - 'N': Print one character 'N' on Console.
2nd Key - 'S': Select the whole Console.
3rd Key - 'C': Copy selected content to buffer.
4th Key - 'P': Print the buffer on Console, and append it after what has 
already been printed.

Now, your task is to find out maximum numbers of 'N's you can print
after K keystrokes . 

Input Format:
-------------
An integer K

Output Format:
--------------
Print an integer, maximum numbers of 'N's you can print.


Sample Input-1:
-------------------
3

Sample Output-1:
-------------------- 
3

Explanation: 
---------------
We can print at most get 3 N's on console by pressing following key sequence:
N, N, N



Sample Input-2:
-------------------
7

Sample Output-2:
---------------------
9

Explanation: 
---------------
We can print at most get 9 N's on console by pressing following key sequence:
N, N, N, S, C, P, P
 */
import java.util.*;

class p2 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        Map<Integer, Integer> dp = new HashMap<>();
        System.out.println(find(k, dp));
        sc.close();
    }

    public static int find(int k, Map<Integer, Integer> dp) {
        if (k <= 0) return 0;
        if (dp.containsKey(k)) return dp.get(k);

        int max = k; // The maximum N's we can print is k itself (by pressing 'N' k times)
        // We need at least 3 keystrokes to use 'S', 'C', and 'P'
        // So we start from 1 and go up to k - 3
        // because we need at least 2 more keystrokes for 'S' and 'C'
        // and 1 for 'P' to paste the copied content
        for (int i = 1; i <= k - 3; i++){
            int ns = find(i, dp);
            int rem = k - i;
            int paste = rem - 2; // 1 for 'S', 1 for 'C'
            if (paste > 0) {
                int total = ns + (ns * paste);
                max = Math.max(max, total);
            }
        }
        dp.put(k, max);
        return max;
    }
}
