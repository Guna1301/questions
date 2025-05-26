/*Mr. Crazy Robert is in a Mall, There are N stalls which are interconnected.
i.e., you can visit a stall-'i',if you visit stall-'i-1' only.

Mr. Robert can start at stall-0 and visit stall-1, stall-2,...,so on.

He is given an array of integers amount[], he need to pay amount[i] to visit 
stall-'i'. And also he is given a coupon worth amount C INR. 
Mr. Crazy Robert can use the coupon atmost once, to pay atmost an amount C INR.

Now, Mr. Crazy Robert task is to visit all the stalls in the mall. He must have 
atleast an amount 1$ with him all the time.

Your task is to find the minimum amount that Crazy Robert need to visit 
all the stalls in the Mall.


Input Format:
-------------
Line-1: Two space separated integers, N and C.
Line-2: N comma separated integers, 

Output Format:
--------------
Print an integer, the minimum amount.



Sample Input-1:
---------------
6 4
1,5,10,8,6,4

Sample Output-1:
----------------
31

Explanation:
------------
One optimal way to visit all the stalls is to start with an amount 31 INR:
At stall-1, pay 1 INR. He has 31 - 1 = 30 INR left.
At stall-2, pay 5 INR. He has 30 - 5 = 25 INR left.
At stall-3, pay 10 INR. He has 25 - 10 = 15 INR left.
At stall-4, pay 8 INR. He has 15 - 8 = 7 INR left.
At stall-5, pay 6 INR. He uses coupon worth 4 and pay 4 INR.
            So, 7 + 4 - 6 = 5 INR left.
At stall-6, pay 4 INR. He has 5 - 4 = 1 INR left.
So, the minimum amount is 31 INR he need to start with.


Sample Input-2:
---------------
6 12
7,7,9,6,2,6

Sample Output-2:
----------------
29


Sample Input-2:
---------------
6 0
7,7,9,6,2,6

Sample Output-2:
----------------
38 */
// import java.util.*;

// class p1 {
//     public static void main(String args[]) {
//         Scanner sc = new Scanner(System.in);
//         int n = sc.nextInt();
//         int c = sc.nextInt();
//         int arr[] = new int[n];
//         int sum = 0;
//         for(int i = 0; i < n; i++) {
//             arr[i] = sc.nextInt();
//             sum += arr[i];
//         }
//         System.out.println(find(arr, sum, c));
//     }

//     public static int find(int arr[], int sum, int c) {
//         int left = 0;
//         int right = sum;
//         int ans = 0;
//         while(left <= right) {
//             int mid = left + (right - left) / 2;
//             if(possible(mid, arr, c)) {
//                 right = mid - 1;
//                 ans = mid;
//             } else {
//                 left = mid + 1;
//             }
//         }
//         return ans;
//     }

//     public static boolean possible(int amm, int arr[], int coupon) {
//         int c = coupon; 
//         for(int i = 0; i < arr.length; i++) {
//             if(arr[i] <= amm) {
//                 amm -= arr[i];
//             } else {
//                 if(c == 0 || arr[i] > amm + c) {
//                     return false;
//                 }
//                 if(arr[i] <= c) {
//                     c = 0;
//                 } else {
//                     amm -= (arr[i] - c);
//                     c = 0;
//                 }
//             }
//             if(amm < 1) return false; 
//         }
//         return true;
//     }
// }
import java.util.*;

class p1 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();
        int arr[] = new int[n];
        int sum = 0;
        int max = 0;
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
            max = Math.max(max, arr[i]);
        }
        System.out.println(find(arr, sum, c, max));
        sc.close();
    }

    public static int find(int arr[], int sum, int c, int max){
        int ans = 0;
        ans += (sum - Math.min(c, max));
        return ans+1;
    }
}