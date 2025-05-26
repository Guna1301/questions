/*Captain Raynor is on a mission to decode an alien transmission.
The transmission contains a single long string S, and it is believed to be 
made up of multiple unique signal chunks, sent one after another with no spaces.

Your task is to help Captain Raynor split the transmission into the maximum 
number of non-empty, unique signal chunks such that when all chunks are 
concatenated in order, they exactly recreate the original transmission S.

Your goal is to maximize the number of unique chunks the message can be split into.

Input Format:
Line-1: A string S representing the alien transmission.

Output Format:
Print a single integer – the maximum number of distinct chunks the string can be split into.

Sample Input-1:
---------------
banana

Sample Output-1:
----------------
4

Explanation: 
------------
One valid way to split the string is: "b", "a", "n", "ana".
This keeps all chunks unique.
Another way like "b", "a", "n", "an", "a" is invalid because "a" appears twice.


Sample Input-2:
---------------
mississippi

Sample Output-2:
----------------
7

Explanation: 
------------
One valid way to split it is: "m", "i", "s", "si", "ssi", "p", "pi".
All chunks are distinct and together recreate the original transmission.

NOTE: Only contiguous chunks (i.e., substrings) are allowed. Subsequence-based 
      splitting is not permitted.
 */

import java.util.*;
class p2{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        Set<String> set = new HashSet<>();
        System.out.println(find(0,s,set));
    }
    public static int find(int id, String s, Set<String>set){
        if(id>=s.length()){
            return set.size();
        }
        
        int ans = Integer.MIN_VALUE;
        for(int i=id+1;i<=s.length();i++){
            StringBuilder sb = new StringBuilder();
            sb.append(s.substring(id,i));
            if(set.contains(sb.toString())){
                continue;
            }
            set.add(sb.toString());
            int val = find(i,s,set);
            ans = Math.max(ans,val);
            set.remove(sb.toString());
            sb.setLength(0);
        }
        return ans;
    }
}