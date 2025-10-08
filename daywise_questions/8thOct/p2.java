/* Ananth interested in creating the acronyms for any word. The definition
of acronym is another word with a concatenation of its first letter,
the number of letters between the first and last letter, and its last letter. 
 
If a word has only two characters, then it is an acronym of itself.

Examples:
    - Acronym of 'fog' is f1g'.
    - Acronym of 'another' is 'a5r'.
    - Acronym of 'ab' is 'ab'.

You are given a list of vocabulary, and another list of words.
Your task is to check,each word with the vocabulary and
return "true" if atleast one of the following rules satisfied:
1. acronym of the word should not match with any of the acronyms of vocabulary
2. if acronym of the word matches with any of the acronyms of vocabulary
'the word' and matching words in vocabulary should be same.

Otherwise, return 'false'.

Input Format:
-------------
Line-1: Space separated strings, vocabulary[] 
Line-2: Space separated strings, words[] 

Output Format:
--------------
Print N boolean values, where N = words.length 


Sample Input-1:
---------------
cool bell cool coir move more mike
cool char move 

Sample Output-1:
----------------
true false false
*/
import java.util.*;
class p2{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String voc[] = sc.nextLine().split(" ");
        String words[] = sc.nextLine().split(" ");
        boolean ans[] = find(voc,words);
        for(int i=0;i<ans.length-1;i++){
            System.out.print(ans[i]+" ");
        }
        System.out.println(ans[ans.length-1]);
        sc.close();
    }
    public static boolean[] find(String voc[],String words[]){
        Map<String,Set<String>> map = new HashMap<>();
        for(String v:voc){
            String a = arc(v);
            map.putIfAbsent(a,new HashSet<>());
            map.get(a).add(v);
            
        }
        boolean ans[] = new boolean[words.length];
        for(int i=0;i<words.length;i++){
            String a = arc(words[i]);
            if(!map.containsKey(a) ||(map.get(a).size() == 1 && map.get(a).contains(words[i]))){
                ans[i] = true;
            }
        }
        return ans;
    }
    public static String arc(String s){
        StringBuilder sb = new StringBuilder();
        if(s.length()<=2){
            return s;
        }else{
            int len = s.length();
            sb.append(s.charAt(0));
            sb.append(len-2);
            sb.append(s.charAt(len-1));

        }
        return sb.toString();
    }
}