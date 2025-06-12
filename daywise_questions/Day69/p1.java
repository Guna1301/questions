/*Luke likes to construct and play with arrays. His dad gave him an array M of 
length N and assigned him the following tasks to be done in order:
 - reate continuous groups of size i from the array M (where i goes from 1 to N).
 - For each group of size i, find the minimum value.
 - Among all the minimums from that step, select the maximum.
 - Add this selected value as the i-th element of a new array P.
 - Repeat until i = N.

Note: Use 1-based indexing for array P in logic.

Input Format:
-------------
input1: Integer N – length of array M
input2: Integer array M of length N

Output Format:
--------------
Return the array P as output.

Sample Input:
-------------
3
1 2 3

Sample Output:
--------------
3 2 1


Sample Input: 
-------------
4
20 10 30 40

Sample Output: 
--------------
40 30 10 10
 */
// import java.util.*;
// class p1{
//     public static void main(String args[]){
//         Scanner sc = new Scanner(System.in);
//         int n = sc.nextInt();
//         int arr[] = new int[n];
//         for(int i=0;i<n;i++){
//             arr[i] = sc.nextInt();
//         }
//         int ans[] = find(arr,n);
//         for(int val:ans){
//             System.out.print(val+" ");
//         }
//     }
//     public static int[] find(int arr[], int n){
//         int ans[] = new int[n];
//         for(int i=1;i<=n;i++){
//             int max = Integer.MIN_VALUE;
//             PriorityQueue<Integer> minHeap = new PriorityQueue<>();
//             for(int j=0;j<i;j++){
//                 minHeap.offer(arr[j]);
//             }
//             max = Math.max(max,minHeap.peek());
//             for(int j=i;j<n;j++){
//                 minHeap.remove(arr[j-i]);
//                 minHeap.offer(arr[j]);
//                 max = Math.max(max,minHeap.peek());
//             }
//             ans[i-1] = max;
//         }
//         return ans;
//     }
// }

// Dp + stack
import java.util.*;

class p1 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Compute result using optimized approach
        int ans[] = maxOfMins(arr, n);

        for(int val : ans) {
            System.out.print(val + " ");
        }
    }

    // Function to compute the final result array
    public static int[] maxOfMins(int[] arr, int n) {

        // Arrays to store indices of previous and next smaller elements
        int[] left = new int[n];   // left[i] = index of previous smaller element for arr[i]
        int[] right = new int[n];  // right[i] = index of next smaller element for arr[i]
        Stack<Integer> st = new Stack<>();

        // Fill left[] using monotonic stack (previous smaller)
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            left[i] = st.isEmpty() ? -1 : st.peek();  // if no smaller element, use -1
            st.push(i);
        }

        st.clear(); // reuse the stack

        // Fill right[] using monotonic stack (next smaller)
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            right[i] = st.isEmpty() ? n : st.peek();  // if no smaller, use n (out of bounds)
            st.push(i);
        }

        // Array to store the maximum of minimums for each window size
        int[] res = new int[n + 1];  // 1-based indexing

        // For each element, determine the window length where it is minimum
        // and update the corresponding res[len] to hold the maximum such value
        for (int i = 0; i < n; i++) {
            int len = right[i] - left[i] - 1; // window size where arr[i] is the minimum
            res[len] = Math.max(res[len], arr[i]);  // update maximum for this length
        }

        // Fill the empty entries: If res[i] is 0, fill it with max of res[i+1]
        // Ensures no decreasing values in result array from left to right
        for (int i = n - 1; i >= 1; i--) {
            res[i] = Math.max(res[i], res[i + 1]);
        }

        // Final answer: extract from res[1...n] into a 0-indexed array
        int[] ans = new int[n];
        for (int i = 1; i <= n; i++) {
            ans[i - 1] = res[i];
        }

        return ans;
    }
}
