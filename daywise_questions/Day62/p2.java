/*Preethi is playing with strings. She is given 2 strings. 
Preethi can remove any number of characters from 2 strings such that 2 strings 
to be equal after removal (Sequence of characters shouldn't change).
Find the smallest ASCII sum possible for the removed characters.

Input Format:
-------------
Line-1: Two space seperated strings

Output Format:
--------------
return an integer , represents ASCII sum with the given constraints.

Sample Input-1:
---------------
treat create

Sample Output-1:
----------------
316

Explanation:
-------------
Remove 't' in string1 and 'c' and 'e' in string 2. so sum= 116+99+101=316


Sample Input-2:
---------------
interest pintrest

Sample Output-2:
----------------
213

Explanation:
-------------
Remove 'e' in string1 and 'p' in string2. so sum=101+112=213
 */
import java.util.*;
class Solution{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s[] = sc.nextLine().split(" ");
        String s1 = s[0];
        String s2 = s[1];
        System.out.println(find(s1.length()-1,s2.length()-1,s1,s2));
    }
    public static int find(int i, int j, String s1, String s2){
        
        if(i<0){
            int count = 0;
            for(int k=0;k<=j;k++){
                count += ((int)s2.charAt(k));
            }
            return count;
        }
        if(j<0){
            int count = 0;
            for(int k=0;k<=i;k++){
                count += ((int)s1.charAt(k));
            }
            return count;
        }
        int ans = (int)1e9;
        if(s1.charAt(i)==s2.charAt(j)){
            ans = Math.min(ans,find(i-1,j-1,s1,s2));
        }else{
            int c1 = ((int)(s1.charAt(i)))+find(i-1,j,s1,s2);
            ans = Math.min(ans,c1);
            int c2 = ((int)(s2.charAt(j)))+find(i,j-1,s1,s2);
            ans = Math.min(ans,c2);
        }
        return ans;
    }
}