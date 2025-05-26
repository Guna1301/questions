/*In the ancient land of Palindoria, wizards write magical spells as strings of 
lowercase letters. However, for a spell to be effective, each segment of the 
spell must read the same forward and backward.

Given a magical spell 'spell', your task is to partition it into sequences of 
valid magical spell segments. 
Your goal is to help the wizard discover all valid combinations of magical spell 
segmentations.

Example 1:
----------
Input:  
aab
  
Output:  
[[a, a, b], [aa, b]]

Example 2:

Input:  
a  
Output:  
[[a]]

Spell Constraints:

- The length of the spell is between 1 and 16 characters.  
- The spell contains only lowercase English letters.   */

import java.util.*;
class p1{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = s.length();
        List<List<String>> ans = new ArrayList<>();
        find(0,s,n,new ArrayList<>(), ans);
        
    }
    public static void find(int id, String s, int n, List<String> temp,List<List<String>> ans){
        if(id>=n){
            ans.add(new ArrayList<>(temp));
            System.out.println(ans);
            return;
        }
        for(int i=id+1;i<=n;i++){
            String sub = s.substring(id,i);
            if(!pali(sub)){
                continue;
            }
            temp.add(sub);
            find(i,s,n,temp,ans);
            temp.remove(temp.size()-1);
        }
    }
    public static boolean pali(String s){
        int i=0;
        int j = s.length()-1;
        while(i<j){
            if(s.charAt(i)!=s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}