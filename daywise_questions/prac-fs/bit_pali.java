/*AlphaCipher is a string formed from another string by rearranging its letters

You are given a string S.
Your task is to check, can any one of the AlphaCipher is a palindrome or not.

Input Format:
-------------
A string S 

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
carrace

Sample Output-1:
----------------
true


Sample Input-2:
---------------
code

Sample Output-2:
----------------
false
 */
import java.util.*;
class bit_pali{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s= sc.nextLine();
        System.out.println(find(s));
        sc.close();
    }
    public static boolean find(String s){
        int dum = 0;
        for(char ch:s.toCharArray()){
            dum = dum^(1<<(ch-'a'));
        }
        return (dum & (dum-1))==0;
    }
}