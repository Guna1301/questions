/*
In the world of secret codes and cryptography, you are entrusted with deciphering a hidden message. 
You have two encoded keys, key1 and key2, both of equal length. Each character 
in key1 is paired with the corresponding character in key2. 
 
 This relationship follows the standard rules of an equivalence cipher:
 • Self-Mapping: Every character inherently maps to itself.  
 • Mutual Mapping: If a character from key1 maps to one in key2, then that 
   character in key2 maps back to the one in key1.  
 • Chain Mapping: If character A maps to B, and B maps to C, then A, B, and C 
   are all interchangeable in this cipher.
 
 Using this mapping, you must decode a cipherText, by replacing every character 
 with the smallest equivalent character from its equivalence group. 
 The result should be the relatively smallest possible decoded message.
 
 
 Input Format:
 -------------
 Three space separated strings, key1 , key2 and cipherText
 
 Output Format:
 --------------
 Print a string, decoded message which is relatively smallest string of cipherText.
 
 Example 1: 
 input=
 attitude progress apriori
 output=
 aaogoog
 
 
 Explanation: The mapping pairs form groups: [a, p], [o, r, t], [g, i], [e, u], 
 [d, e, s]. By substituting each character in cipherText with the smallest from 
 its group, you decode the message to "aaogoog".
 
 
 Constraints:  
 • 1 <= key1.length, key2.length, cipherText.length <= 1000  
 • key1.length == key2.length  
 • key1, key2, and cipherText consist solely of lowercase English letters.
 
 */

 import java.util.*;
 class dsu{
     int parent[] = new int[26];
     public int find(int x){
         if(parent[x] !=x){
             parent[x] = find(parent[x]);
         }
         return parent[x];
     }
     public boolean union(int x, int y){
         int rootx = find(x);
         int rooty = find(y);
         if(rootx==rooty){
             return false;
         }
         if(rootx<rooty){
             parent[rooty] = rootx;
         }else{
             parent[rootx] = rooty;
         }
         return true;
     }
     
 }
 class Solution{
     public static void main(String args[]){
         Scanner sc = new Scanner(System.in);
         String strs[] = sc.nextLine().split(" ");
         System.out.println(find(strs[0],strs[1],strs[2]));
         sc.close();
     }
     public static String find(String key1, String key2, String s){
         dsu ds = new dsu();
         
         for(int i=0;i<26;i++){
             ds.parent[i] = i;
         }
         for(int i=0;i<key1.length();i++){
             ds.union(key1.charAt(i)-'a', key2.charAt(i)-'a');
         }
         StringBuilder ans = new StringBuilder();
         for(char ch:s.toCharArray()){
             ans.append((char)(ds.find(ch-'a')+97));
         }
         return ans.toString();
     }
 }