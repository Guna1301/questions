
    

/*Imagine you are a secret agent tasked with sending encoded messages. 
Each original message is a string of lowercase letters, and you can disguise 
it by replacing selected, non-adjacent segments with the count of characters 
in those segments. However, the encoding must follow strict rules: only 
non-empty segments can be replaced, replacements cannot be adjacent, and any 
numbers used must not have leading zeros.

For instance, the message "substitution" can be encoded in various ways, such as:

• "s10n" (keeping 's', replacing the next 10 characters with 10, and ending with 'n')  
• "sub4u4" (keeping "sub", replacing 4 characters, then 'u', and replacing 4 more characters)  
• "12" (replacing the entire message with its length)  
• "su3i1u2on" (using a different pattern of replacements)  
• "substitution" (leaving the message unaltered)

Invalid encodings include:

• "s55n" (because the replaced segments are adjacent)  
• "s010n" (the number 010 has a leading zero)  
• "s0ubstitution" (attempts to replace an empty segment)

Your task is: given an original message and an encoded version, 
determine if the encoded version is a valid secret code for the message.

Example 1:

Input: 
internationalization
i12iz4n
  
Output: 
true  

Explanation: "internationalization" can be encoded as "i12iz4n" by keeping 'i', 
replacing 12 letters, keeping "iz", replacing 4 letters, and then ending with 'n'.

Example 2:

Input: 
apple
a2e
  
Output: 
false  

Explanation: The encoding "a2e" does not correctly represent "apple".

Time Complexity: O(n) where n=max(len(word),len(abbr))
Auxiliary Space:  O(1).
 */

 import java.util.*;
 public class ValidCode {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String code = sc.nextLine();
        System.out.println(find(s,code));
        sc.close();
    }
    public static boolean find(String s, String code){
        int n = s.length();
        int n2 = code.length();
        int p1 = 0;
        int p2 = 0; 
        while(p1<n && p2<n2){
            
            int val = 0;
            if(Character.isDigit(code.charAt(p2))){
                while(p2<n2 && Character.isDigit(code.charAt(p2))){
                    if(val==0 && code.charAt(p2)=='0'){
                        return false;
                    }
                    val = val*10 + (code.charAt(p2)-'0');
                    p2++;
                }
                p1+=val;
            }else{
                if( s.charAt(p1)!=code.charAt(p2)){
                    return false;
                }else{
                    p2++;
                    p1++;
                }
            }

            
        }
        return p1==n;
    }
}