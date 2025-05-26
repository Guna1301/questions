/*You are a database integrity engineer working for a global cloud company. 
Your team maintains a distributed database network, where each server either:
    - Stores equivalent data to another server (serverX == serverY).
    - Stores different data from another server (serverX != serverY).

The transitive consistency rule must be followed:
    - If A == B and B == C, then A == C must be true.
    - If A == B and B != C, then A != C must be true.

Your task is to analyze the given constraints and determine whether they 
follow transitive consistency. If all relations are consistent, return true; 
otherwise, return false

Input Format:
-------------
Space separated strnigs, list of relations

Output Format:
--------------
Print a boolean value, whether transitive law is obeyed or not.


Sample Input-1:
---------------
a==b c==d c!=e e==f

Sample Output-1:
----------------
true


Sample Input-2:
---------------
a==b b!=c c==a

Sample Output-2:
----------------
false

Explanation:
------------ 
{a, b} form one equivalence group.
{c} is declared equal to {a} (c == a), which means {a, b, c} should be equivalent.
However, b != c contradicts b == a and c == a.

Sample Input-3:
---------------
a==b b==c c!=d d!=e f==g g!=d

Sample Output-3:
----------------
true
 */

import java.util.*;
class dsu{
    int parent[] = new int[26];
    dsu(){
        for(int i=0;i<26;i++){
            parent[i] = i;
        }
    }
    public int find(int x){
        if(parent[x]!=x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    public boolean union(int a, int b){
        int roota = find(a);
        int rootb = find(b);
        if(roota==rootb){
            return false;
        }
        parent[roota] = rootb;
        parent[rootb] = rootb;
        return true;
    }
}
class transitive{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String strs[] = sc.nextLine().split(" ");
        sc.close();
        dsu u = new dsu();
        int n = strs.length;
        for(int i=0;i<n;i++){
            if(strs[i].charAt(1)=='='){
                u.union(strs[i].charAt(0)-'a', strs[i].charAt(3)-'a');
            }
        }
        for(int i=0;i<n;i++){
            int a = strs[i].charAt(0)-'a';
            int b = strs[i].charAt(3)-'a';
            if(strs[i].charAt(1)=='!'){
                 if(u.find(a) == u.find(b)){
                     System.out.println(false);
                     return;
                 }
            }
        }
        System.out.println(true);
        
    }
}