/*Imagine you're playing a fantasy video game where secret level codes unlock new 
worlds. These codes are strings made up of letters, and a level code is only 
considered valid if every shorter code formed by its leading characters has been
discovered along the journey. In other words, a code is unlockable only when all
of its prefixes are also present in your collection.

Given a list of strings representing the level codes you’ve collected, find the 
longest valid level code such that every prefix of that code is in the list. 
If there is more than one valid code of the same length, choose the one that 
comes first in alphabetical order. If no such code exists, return an empty string.

Input Format
-------------
Line1: Space separated codes (strings)
 
Output Format
--------------
string 


Example 1:
----------
Input:
m ma mag magi magic magici magicia magician magicw
Output: 
"magician"

Explanation: The code "magician" is unlockable because every 
prefix—"m", "ma", "mag", "magi", "magic", "magici", and "magicia"—is present in 
the list. Although "magicw" appears too, it fails since its prefix "magica" is missing.


Example 2:
----------
Input:
a banana app appl ap apply apple
Output: 
"apple"  

Explanation: Both "apple" and "apply" have every prefix in the list, but "apple" 
comes first in alphabetical order.

Example 3:
----------
Input: 
abc bc ab abcd
Output: 
""

 */
import java.util.*;
class trien{
    trien children[];
    boolean end;
    trien(){
        children = new trien[26];
        end = false;
    }

}

class trie{
    trien root = new trien();
    public void insert(String s){
        trien curr = root;
        for(char ch:s.toCharArray()){
            int id = ch-'a';
            if(curr.children[id]==null){
                curr.children[id]  = new trien();
            }
            curr = curr.children[id];
        }
        curr.end = true;
    }
    public boolean search(String s){
        trien curr = root;
        for(char ch:s.toCharArray()){
            int id = ch-'a';
            if(curr.children[id]==null){
                return false;
            }
            
            curr = curr.children[id];
            if(!curr.end){
                return false;
            }
        }
        return true;
    }
    
}
class p1{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s1[] = sc.nextLine().split(" ");
        trie t = new trie();
        for(String s:s1){
            t.insert(s);
        }
        String ans = "";
        for(String s:s1){
            if(t.search(s)){
               if(ans.length()<s.length()){
                ans = s;
               }
               else if(ans.length()==s.length()){
                    if(s.compareTo(ans)<0){
                        ans = s;
                    }
                }
            }
        }
        System.out.print(ans);
        
    }

}