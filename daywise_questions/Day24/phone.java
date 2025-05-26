package Day24;

/*
 * Imagine you're a top-secret agent receiving an encrypted directive from headquarters. The message comes as a string of digits, and each digit (from 2 to 9) is a cipher for a set of potential code letters. To uncover the true instruction, you must translate the string into every possible combination of letters by substituting each digit with its corresponding set of letters. The final decoded messages listed in lexicographycal order.
 
 Below is the mapping of digits to letters (as found on a traditional telephone keypad):
 
 | Digit | Letters       |
 |-------|---------------|
 | 2     | a, b, c       |
 | 3     | d, e, f       |
 | 4     | g, h, i       |
 | 5     | j, k, l       |
 | 6     | m, n, o       |
 | 7     | p, q, r, s    |
 | 8     | t, u, v       |
 | 9     | w, x, y, z    |
 
 Note: The digit 1 does not correspond to any letters.
 
 Example 1:
 Input: 23  
 Output: [ad, ae, af, bd, be, bf, cd, ce, cf]
 
 Example 2:
 Input: 2 
 Output: [a, b, c]
 
 
 Constraints:
 
 - 0 <= digits.length <= 4  
 - Each digit in the input is between '2' and '9'.
 
 */

 import java.util.*;
 public class phone {
     public static void main(String args[]){
         Scanner sc = new Scanner(System.in);
         String num = sc.nextLine();
         Map<Character,String> map = new HashMap<>();
         map.put('2',"abc");
         map.put('3',"def");
         map.put('4',"ghi");
         map.put('5',"jkl");
         map.put('6',"mno");.
         map.put('7',"pqrs");
         map.put('8',"tuv");
         map.put('9',"wxyz");
         List<String> ans = new ArrayList<>();
         find(0,num,map,ans,new StringBuilder());
         System.out.println(ans.toString());
     }
     public static void find(int id,String num, Map<Character,String> map,List<String> ans, StringBuilder sb){
         if(sb.length()==num.length()){
             ans.add(sb.toString());
         }
         for(int i=id;i<num.length();i++){
             for(char ch: map.get(num.charAt(i)).toCharArray()){
                 sb.append(ch);
                 find(i+1,num,map,ans,sb);
                 sb.deleteCharAt(sb.length()-1);
             }
         }
         
     }
 }