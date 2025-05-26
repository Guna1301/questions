/*Imagine you're a digital security analyst reviewing a suspicious email. The email’s 
content is a continuous string of characters, and you have a list of keywords 
commonly used in phishing scams. Your mission is to scan the email text and flag 
every segment that exactly matches one of these keywords. In other words, identify 
all index pairs [i, j] such that the substring from position i to j in the email 
text is one of the suspicious keywords. Return these pairs sorted by their starting 
index, and if two pairs share the same starting index, sort them by their ending index.

Input Format
------------
Line-1: string STR(without any space)
Line-2: space separated strings, suspicious keywords[]

Output Format
-------------
Print the pairs[i, j] in sorted order.


Example 1:
----------
Input:  
cybersecuritybreachalert
breach alert cyber

Output: 
0 4
13 18
19 23

Example 2:
----------
Input:  
phishphishingphish
phish phishing

Output:
0 4
5 9
5 12
13 17


Explanation: Notice that keywords can overlap—for instance, the word "phish" appears 
as part of the substring [5,9] in addition to the complete "phishing" substring [5,12].

Constraints:

- 1 <= emailText.length <= 100  
- 1 <= suspiciousWords.length <= 20  
- 1 <= suspiciousWords[i].length <= 50  
- emailText and each suspicious word consist of lowercase English letters.  
- All suspicious words are unique.
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
        }
        return curr.end;
    }
    
}
class trie1{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String s1[] = sc.nextLine().split(" ");
        trie t = new trie();
        for(String s:s1){
            t.insert(s);
        }
        int n = str.length();
        for(int j=0;j<n;j++){
            for(int i=j+1;i<=n;i++) {
                if(t.search(str.substring(j,i))){
                    System.out.println(j+" "+(i-1));
                }
            }
             
                
        }
    }
}