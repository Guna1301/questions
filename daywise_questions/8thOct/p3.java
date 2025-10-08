/*Murali playing a mobile game, Blast the letters.

In the game he is given a word W and value R.
Murali has to perform the blasting operation as follows:
	- He has to blast the mimeograph M of length R in W, 
	  a mimeograph is a string such that each letter in it should be same.
	- After blasting the mimeograph, the rest of the string on its
	  left side and right side, concatenated together.

Murali has to perform the blasting operation repeatedly, 
until no more blasting is possible. Your task is to return 
the final string after all the blast operations have been done.

Input Format:
-------------
Line-1: A string and an integer, W and R.

Output Format:
--------------
Print a string, the final string after all the blast operations have been done.


Sample Input-1:
--------------- 
ababbaaab 3

Sample Output-1:
----------------
aba


Sample Input-2:
--------------- 
caaabbbaacdddd 2

Sample Output-2:
----------------
cabc
*/
import java.util.*;

class p3 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int r = sc.nextInt();
        System.out.println(find(s, r));
        sc.close();
    }

    public static String find(String s, int r) {
        Stack<int[]> st = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (!st.isEmpty() && st.peek()[0] == ch) {
                st.peek()[1]++;
            } else {
                st.push(new int[]{ch, 1});
            }
            if (st.peek()[1] == r) {
                st.pop();
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int[] pair : st) {
            char ch = (char) pair[0];
            int count = pair[1];
            for (int i = 0; i < count; i++) {
                ans.append(ch);
            }
        }
        return ans.toString();
    }

}