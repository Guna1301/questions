/*Umesh is a Mathematician,
He is given a task to his student Shanker, 
There are N coins in a row, indexed from 0 to N-1, intially all the coins are 
facing "tail". And Umesh has given him a final State-to-Achieve.

Shanker can achieve the final state by doing a swap operation as follows:
    - Shanker can choose any index i, 
    - all the coins has to be swap their faces, from "head" to "tail" 
    or "tail to "head" from index 'i' to 'N-1'.

Shanker is given a binary string S, State-to-Achieve contains [0,1] only. 
"tail" indicates with '0' and "head" indicates with '1'

Please help Shanker to find the minimum number of swap operations required 
to reach State-to-Achieve.


Input Format:
-------------
A String S, final State-to-Achieve.

Output Format:
--------------
Print an integer, minimum number of swap operations.


Sample Input-1:
---------------
10111010

Sample Output-1:
----------------
6

Explanation: 
------------
Initial configuration "00000000".
swap from the first coin: "00000000" -> "11111111"
swap from the second coin: "11111111" -> "10000000"
swap from the third coin: "10000000" -> "10111111"
swap from the sixth coin: "10111111" -> "10111000"
swap from the seventh coin: "10111000" -> "10111011"
swap from the eighth coin: "10111011" -> "10111010"
A total of 6 swap operations required.


Sample Input-2:
---------------
11111

Sample Output-2:
----------------
1

 */

 import java.util.*;
class p1{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(find(s));
    }
    public static int find(String s){
        int n = s.length();
        int ans = 0;
        if(s.charAt(0)=='1'){
            ans++;
        }
        for(int i=1;i<n;i++){
            if(s.charAt(i)!=s.charAt(i-1)){
                ans++;
            }
        }
        return ans;
        
    }
    
}