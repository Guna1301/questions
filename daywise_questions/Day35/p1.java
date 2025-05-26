/*Ramesh and Suresh are best friends. 
They decided to play a word game.

The game rules are as follows:
	- The game board shows a word W consist of two characters only A and B.
	- You are allowed to replace a pair of neighbour letters AA with BB in W.
	- Finally, The one who failed to replace the letters will lose the game.

You can assume that Ramesh will start playing the game always.

Your task is to determine if Ramesh can guarantee a win.
Print 'true', if Ramesh guarantee a win, otherwise, print 'false'.

Input Format:
-------------
A string W, word.

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
AAABAAAA

Sample Output-1:
----------------
true

Explanation:
------------
Given word is AAABAAAA 
Ramesh -> AAABBBAA 
Now whatever the pair Suresh replaced with BB, 
one more replace is possible for Ramesh
So, there is a guarantee for Ramesh to win.

Sample Input-2:
---------------
AAAAAA

Sample Output-2:
----------------
true


Sample Input-3:
---------------
AAABAAA

Sample Output-3:
----------------
false
 */
import java.util.*;
class p1{
     public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = s.length();
        boolean vis[] = new boolean[n];
        // int count = findcount(s);
        System.out.println(find(s,n,vis));
    }
    public static boolean find( String s, int n, boolean vis[]){
        for(int i=0;i<n-1;i++){
            if(s.charAt(i)=='A' && s.charAt(i+1)=='A'){
                if(!vis[i] && !vis[i+1]){
                    vis[i] = true;
                    vis[i+1] = true;
                    boolean opploss = !find(s,n,vis);
                    vis[i]=false;
                    vis[i+1]=false;
                    if(opploss){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // public static int findcount(String s){
    //     int count = 0;
    //     int dum = 0;
    //     for(int i=0;i<s.length();i++){
    //         if(s.charAt(i)=='A'){
    //             dum++;
    //         }else{
    //             count += (dum/2);
    //             dum=0;
    //         }
    //     }
    //     count += (dum/2);
    //     return count;
    // }

}