/*
 * Archaeologists discovered an ancient manuscript represented by a string 'text', 
 where each character represents an ancient symbol. They suspect the manuscript 
 might contain repeated symbol patterns (substrings).
 
 Your task is to analyze the text and determine the length of the longest 
 repeating symbol pattern. If the text contains no repeating patterns, return '0'.
 
 Example:
 --------
 Input=
 scarabankhscarab
 
 Output=
 6
 
 Explanation: The longest repeating symbol pattern is "scarab", appearing twice.
 
  Constraints:
 - 1 <= text.length <= 2000
 - 'text' consists of lowercase English letters ('a' - 'z').
 
 */
import java.util.*;
 class Solution{
     public static void main(String[] args){
         Scanner sc = new Scanner(System.in);
         String s = sc.nextLine();
         System.out.println(find(s,s.length()-1));
     }
     
     static int find(String s, int idx){
         int n = s.length();
         for(int i = (n-1); i>=1; i--){
             HashSet<String> hs = new HashSet<>();
             for(int j = 0; j<=(n-i); j++){
                 String sb = s.substring(j, j+i);
                 if(hs.contains(sb)){
                     return i;
                 }
                 hs.add(sb);
             }
         }
         return 0;
     }
 }