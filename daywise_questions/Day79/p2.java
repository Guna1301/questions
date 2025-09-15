/*Prabhath is working on words.  He used to take out every letter that was repeated 
in the word. 
A word W is given to Prabhath. His objective is to eliminate every duplicate 
letter from W such that the resultant word R contains every unique letter from W
and did not contain any duplicate letters. 
And R should be at the top of the dictionary order.

NOTE:
-----
He is not allowed to shuffle the relative order of the letters of the word W to
create the word R.

Input Format:
-------------
A String, the word W.

Output Format:
--------------
Print a String, resultant word R.


Sample Input-1:
---------------
cbadccb

Sample Output-1:
----------------
adcb


Sample Input-2:
---------------
silicosis

Sample Output-2:
----------------
ilcos

 */
import java.util.*;

class p2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        System.out.println(getLargestUniqueSubsequence(word));
    }

    public static String getLargestUniqueSubsequence(String word) {
        int n = word.length();
        int[] lastIndex = new int[26];

        for (int i = 0; i < n; i++) {
            lastIndex[word.charAt(i) - 'a'] = i;
        }

        Stack<Character> stack = new Stack<>();
        boolean[] inResult = new boolean[26];

        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            int idx = c - 'a';

            if (inResult[idx]) continue;

            while (!stack.isEmpty() &&
                   stack.peek() > c &&
                   lastIndex[stack.peek() - 'a'] > i) {
                char removed = stack.pop();
                inResult[removed - 'a'] = false;
            }

            stack.push(c);
            inResult[idx] = true;
        }

        // Build the result from stack
        StringBuilder result = new StringBuilder();
        for (char ch : stack) {
            result.append(ch);
        }

        return result.toString();
    }
}
