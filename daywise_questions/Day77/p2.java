/*Bunny is playing with binary strings. He wants to break
a binary string B into 3 parts, of length atleast '1',
when we merge the 3 parts will result the string B.

Your task is to find the count the various forms to break 
the Binary String B into 3 parts, where each part should 
contain equal number of 1's.


Input Format:
-------------
A string S.

Output Format:
--------------
Print an integer, count the various forms to break.


Sample Input-1:
---------------
01010010

Sample Output-1:
----------------
6

Explanation:
------------
There are six forms to break S into 3 parts 
where each part contain the equal number of  1's.
01 | 01 | 0010
01 | 010 | 010
01 | 0100 | 10
010 | 1 | 0010
010 | 10 | 010
010 | 100 | 10


Sample Input-2:
---------------
010010

Sample Output-2:
----------------
0
 */
import java.util.*;
class Solution{
    static int ans = 0;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        find(0,s,-1,0);
        System.out.println(ans);
        sc.close();
    }
    public static void find(int id, String s, int count, int parts){
        if(id>=s.length())return;
        if(parts==2){
            String last = s.substring(id);
            int c = ones(last);
            if(c==count){
                ans++;
            }
            return;
        }
        for(int i=id+1;i<s.length();i++){
            String part = s.substring(id,i);
            int c = ones(part);
            if(count!=-1 && c!=count)continue;
            find(i,s,c,parts+1);
        }
    }
    public static int ones(String s){
        int count = 0;
        for(char ch:s.toCharArray()){
            if(ch=='1')count++;
        }
        return count;
    }
}