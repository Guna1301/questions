/*Given two strings S1 and S2, find if S2 can match S1 or not.

A match that is both one-to-one (an injection) and onto (a surjection), 
i.e. a function which relates each letter in string S1 to a separate and 
distinct non-empty substring in S2, where each non-empty substring in S2
also has a corresponding letter in S1.

Return true,if S2 can match S1.
Otherwise false.

Input Format:
-------------
Line-1 -> Two strings S1 and S2

Output Format:
--------------
Print a boolean value as result.


Sample Input-1:
---------------
abab kmitngitkmitngit

Sample Output-1:
----------------
true


Sample Input-2:
---------------
aaaa kmjckmjckmjckmjc

Sample Output-2:
----------------
true

Sample Input-3:
---------------
mmnn pqrxyzpqrxyz

Sample Output-3:
----------------
false
 */
import java.util.*;
class p3{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s[]  = sc.nextLine().split(" ");
        String s1 = s[0];
        String s2 = s[1];
        Map<Character,String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        System.out.println(find(0,0,s1,s2,map,set));
    }
    public static boolean find(int id, int idx, String s1, String s2,Map<Character,String> map,Set<String> set){
        if(id>=s1.length() && idx>=s2.length()){
            return true;
        }
        if(id>=s1.length() || idx>=s2.length()){
            return false;
        }
        char ch = s1.charAt(id);
        if(map.containsKey(ch)){
            String str = map.get(ch);
            if(!s2.startsWith(str,idx)){
                return false;
            }
            return find(id+1,idx+str.length(),s1,s2,map,set);
        }
        
        for (int i = idx + 1; i <= s2.length(); i++) {
            String substr = s2.substring(idx, i);
            if (set.contains(substr)) continue;
            map.put(ch, substr);
            set.add(substr);
            if (find(id + 1, i, s1, s2, map, set)) return true;
            map.remove(ch);
            set.remove(substr);
        }
        return false;
    } 
}

