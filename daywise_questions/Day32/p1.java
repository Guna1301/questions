/*A detective is investigating a case involving a secret message hidden within a 
longer note. The detective knows that the culprit rearranged the letters of a 
short code-word into multiple secret locations within a larger note.

Given two strings, note (the longer text) and codeWord (the short secret code), 
your task is to help the detective find all starting positions within the note 
where any rearrangement or shuffled of codeWord is located.

Input Format:
-------------
Single line space separated strings, two words.

Output Format:
--------------
Print the list of integers, indices.


Sample Input-1:
---------------
bacdgabcda abcd
 
Sample Output-1:
----------------
[0, 5, 6]

Explanation:
- At index 0: "bacd" is an anagram of "abcd"
- At index 5: "abcd" matches exactly
- At index 6: "bcda" is an anagram of "abcd"
Thus, the positions [0, 5, 6] are returned.

Sample Input-2:
---------------
bacacbacdcab cab

Sample Output-2:
----------------
[0, 3, 4, 5, 9]
 */

import java.util.*;
public class p1 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String strs[] = sc.nextLine().split(" ");
        int k = strs[1].length();
        System.out.println(find(strs[0],strs[1],k));
    }
    public static List<Integer> find(String big, String small, int k){
        int n = big.length();
        List<Integer> ans = new ArrayList<>();
        int sfreq[] = new int[26];
        int bfreq[] = new int[26];
        for(char ch:small.toCharArray()){
            sfreq[ch-'a']++;
        }
        for(int i=0;i<k;i++){
            char ch = big.charAt(i);
            bfreq[ch-'a']++;
        }
        if(poss(bfreq,sfreq)){
            ans.add(0);
        }
        for(int i=k;i<n;i++){
            char ch = big.charAt(i);
            char prev = big.charAt(i-k);
            bfreq[prev-'a']--;
            bfreq[ch-'a']++;
            if(poss(bfreq,sfreq)){
                ans.add(i-k+1);
            }
        }
        return ans;
    }
    public static boolean poss(int bfreq[], int sfreq[]){
        for(int i=0;i<26;i++){
            if(bfreq[i]!=sfreq[i]){
                return false;
            }
        }
        return true;
    }
}
