/*
 * "Emphatic Pronunciation" of a given word is where we take the word and
 replicate some of the letter to emphasize their impact.
 
 Instead of saying 'oh my god', someone may say "ohhh myyy goddd", 
 We define emphatic pronunciation of a word, which is derived by replicating 
 a group (or single) of letters in the original word. 
 
 So that the replicated group is atleast 3 characters or more and 
 greater than or equal to size of original group. 
 For example Good -> Goood is an emphatic pronunciation,
 but Goodd is not because in Goodd the 'd' are only occuring twice consecutively.
  
 In the question you are given the "Emphatic pronunciation" word, 
 you have to findout how many words can legal result in the 
 "emphatic pronunciation" word.
 
 Input Format:
 -------------
 Line-1 -> A String contains a single word, Emphatic Pronunciation word
 Line-2 -> Space seperated word/s
 
 Output Format:
 --------------
 Print an integer as your result
 
 
 Sample Input-1:
 ---------------
 goood
 good goodd
 
 Sample Output-1:
 ----------------
 1
 
 
 Sample Input-2:
 ---------------
 heeelllooo
 hello hi helo
 
 Sample Output-2:
 ----------------
 2
 
 
 */
import java.util.*;
public class p3{
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        String s = sc.nextLine();
        String[] ss = sc.nextLine().split(" ");
        int c = 0;
        for(int i = 0; i<ss.length; i++){
            if(isValid(ss[i], s)){
                c++;
            }
        }
        System.out.println(c);
    }
    
    static boolean isValid(String a, String s){
        int i = 0;
        int j = 0;
        while(i<a.length() && j<s.length()){
            if(a.charAt(i)!=s.charAt(j)){
                return false;
            }
            char ch = a.charAt(i);
            int xindex = i;
            int yindex = j;
            while(xindex<a.length() && a.charAt(xindex)==ch ){
                xindex++;
            }
            while(yindex<s.length() && s.charAt(yindex)==ch){
                yindex++;
            }
            
            if((xindex-i) > (yindex-j)){
                return false;
            }
            if(((yindex-j) > (xindex-i) ) && (yindex-j) <3){
                return false;
            }
            i = xindex;
            j = yindex;
        }
        return i==a.length() && j==s.length();
    }
}