/*Ravi is playing with strings.Given Three alphabets [a,b,c] , ravi has to 
make strings such that no two adjacents alphabets are same.

For example, He can make strings as "ab", "acb", "b" and "cbabcabcb" which 
are acceptable.where as the strings "bb", "bcc" and "aabbc" are not acceptable.

Given two integers N and L, Ravi made a list of all acceptable strings of 
length N sorted in lexicographical order.

Return the Lth string of this list or return an empty string if there are less 
than L acceptable strings of length N.

Input Format:
-------------
Line-1: Two space separated integers N and L.

Output Format:
--------------
Print a string result.


Sample Input-1:
---------------
2 3

Sample Output-1:
----------------
ba

Explanation:
-------------
Strings in lexigraphical order are ab,ac,ba,bc,ca,cb. and 3rd one is ba.


Sample Input-2:
---------------
3 4

Sample Output-2:
----------------
acb

Explanation:
------------
Strings in lexigraphical order are aba,abc,aca,acb,bab....

 */
import java.util.*;
class p2{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        System.out.println(find(n,l));
        sc.close();
    }
    public static String find(int n, int l){
        char chs[] = {'a','b','c'};
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++){
            // System.out.println("l "+l);
            for(char ch:chs){
                int len = sb.length();
                if(len>0 && sb.charAt(len-1)==ch)continue;
                int pos = possible(ch,n,i);
                // System.out.println(ch+" "+i+" "+pos);
                if(l>pos){
                    l = l-pos;
                    continue;
                }
                if(l<=pos){
                    sb.append(ch);
                    break;
                }
            }
        }
        return sb.toString();
    }
    public static int possible(char ch,int n,int i){
        int val = 1;
        n = n-i;
        while(n>0){
            val = val*2;
            n--;
        }
        return val;
    }
}