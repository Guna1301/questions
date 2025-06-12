/*For the given a list of integers, Your task to is find out, the length of the 
longest subsequence that is a toggle sequence.

Toggle Sequence means, the difference between the consecutive numbers
are alternate positive and negative.

For Example:

Given list of integers = 1 3 2 5 4 
the consecutive differences are [ 2, -1, 3, -1], 
the differences are alternate +ve and -ve.
So, complete list is a toggle sequence.

If the list of integers = 1 3 2 4 5,
the consecutive differences are [ 2, -1, 2, 1], not alternate +ve and -ve.
Not a toggle sequence.

Note: A sequence with fewer than two elements is a toggle sequence.

Input Format:
-------------
Space separated Integers, List

Output Format:
--------------
Print the length of the longest toggle sequence


Sample Input-1:
---------------
1 7 4 9 2 5

Sample Output-1:
----------------
6

Explanation:
------------
Given list of integers = 1 7 4 9 2 5
the consecutive differences are [ 6, -3, 5, -7, 3], 
the differences are alternate +ve and -ve.
So, complete list is a toggle sequence.

Sample Input-2:
---------------
1 5 4 3 7 9 10

Sample Output-2:
----------------
4

Explanation:
------------
Given list of integers = 1 5 4 3 7 9 10
There are several subsequences that achieve this length.
One is [1 5 4 7] with differences (4, -1, 3).
 */
import java.util.*;
class p1{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String vals[] = sc.nextLine().split(" ");
        int n = vals.length;
        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(vals[i]);
        }
        Map<String,Integer> dp = new HashMap<>();
        int ans = 0;
        for(int i=0;i<n;i++){
            ans = Math.max(ans,1+find(i+1,arr,arr[i],true,dp));
            ans = Math.max(ans,1+find(i+1,arr,arr[i],false,dp));
        }
        
        System.out.println(ans);
    }
    public static int find(int id, int arr[],int prev, boolean tog,Map<String,Integer> dp ){
        
        if(id>=arr.length){
            return 0;
        }
        String key = id+","+tog+","+prev;
        if(dp.containsKey(key)){
            return dp.get(key);
        }
        int ans = 0;
        if(tog){
            if((arr[id]-prev)>0){
                ans = Math.max(ans,1+find(id+1,arr,arr[id],!tog,dp));
            }
        }else {
            if((arr[id]-prev)<0){
                ans = Math.max(ans,1+find(id+1,arr,arr[id],!tog,dp));
            }
        }
        ans = Math.max(ans,find(id+1,arr,prev,tog,dp));
        dp.put(key,ans);
        return ans;
        
    }
}