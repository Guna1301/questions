/*A secret agent encodes a message by recursively scrambling it using a random encryption 
protocol.  The encryption process follows these rules:
 - If the message is a single character, leave it unchanged.
 - If the message has more than one character:
		- Split the message into two non-empty parts at any random position.
		- With a 50% chance, swap the two parts; otherwise, keep them in the same order.
		- Repeat this scrambling recursively on each part.

This encryption method produces a scrambled version of the original message.

You are now given two messages:
original: the message before scrambling.
scrambled: a possibly scrambled version of the original message.

Write a program to determine whether the scrambled message could have been produced 
by scrambling the original message using the above protocol.

Sample Input:
-------------
Two strings, original and scrambled, each of equal length.

Sample Output:
---------------
Return true if the scrambled string could be a scrambled version of the original using 
the given encryption protocol. Otherwise, return false.


Sample Input:
-------------
secure cesure

Sample Output:
---------------
true

Explanation: 
------------
One possible scrambling path leads from "secure" to "cesure".

Sample Input:
-------------
planet npealt

Sample Output:
---------------
false

Explanation: 
------------
No sequence of valid splits and swaps can lead to "petlan" from "npealt".

 */
import java.util.*;

class p2 {
    static Map<String, Boolean> memo = new HashMap<>();

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String s[] = sc.nextLine().split(" ");
        System.out.println(find(s[0], s[1]));
        sc.close();
    }

    public static boolean find(String og, String scramb) {
        if (og.length() != scramb.length()) return false;
        if (!anagram(og, scramb)) return false;
        return solve(og, scramb);
    }

    public static boolean solve(String s1, String s2) {
        if (s1.equals(s2)) return true;
        if (!anagram(s1, s2)) return false;

        String key = s1 + "-" + s2;
        if (memo.containsKey(key)) return memo.get(key);

        int n = s1.length();
        for (int i = 1; i < n; i++) {
            if (solve(s1.substring(0, i), s2.substring(0, i)) &&
                solve(s1.substring(i), s2.substring(i))) {
                memo.put(key, true);
                return true;
            }

            if (solve(s1.substring(0, i), s2.substring(n - i)) &&
                solve(s1.substring(i), s2.substring(0, n - i))) {
                memo.put(key, true);
                return true;
            }
        }

        memo.put(key, false);
        return false;
    }

    public static boolean anagram(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int[] freq = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            freq[s1.charAt(i) - 'a']++;
            freq[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0) return false;
        }
        return true;
    }
}
