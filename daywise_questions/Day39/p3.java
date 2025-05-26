/*Mr Suresh is working with the plain text P, a list of words w[], 
He is converting P into C [the cipher text], C is valid cipher of P, 
if the following rules are followed:
	- The cipher-text C is a string ends with '$' character.
	- Every word, w[i] in w[], should be a substring of C, and 
	the substring should have $ at the end of it.

Your task is to help Mr Suresh to find the shortest Cipher C,  
and return its length.

Input Format:
-------------
Single line of space separated words, w[].

Output Format:
--------------
Print an integer result, the length of the shortest cipher.


Sample Input-1:
---------------
kmit it ngit

Sample Output-1:
----------------
10

Explanation:
------------
A valid cipher C is "kmit$ngit$".
w[0] = "kmit", the substring of C, and the '$' is the end character after "kmit"
w[1] = "it", the substring of C, and the '$' is the end character after "it"
w[2] = "ngit", the substring of C, and the '$' is the end character after "ngit"


Sample Input-2:
---------------
ace

Sample Output-2:
----------------
4

Explanation:
------------
A valid cipher C is "ace$".
w[0] = "ace", the substring of C, and the '$' is the end character after "ace"

 */
import java.util.*;
class trien{
    trien children[];
    boolean end;
    int count;
    trien(){
        children = new trien[26];
        end = false;
        count =0;
    }
}
class trie{
    trien root = new trien();
    public void insert(String s){
        trien curr = root;
        for(char ch:s.toCharArray()){
            int id = ch-'a';
            if(curr.children[id]==null){
                curr.children[id] = new trien();
                curr.count++;
            }
            curr = curr.children[id];
        }
        curr.end = true;
    }
    public int search(trien curr,int depth){
        if(curr.count==0){
            return depth+1;
        }
        int sum = 0;
        for(int i=0;i<26;i++){
            if(curr.children[i]!=null){
                sum+=search(curr.children[i],depth+1);
            }
        }
        return sum;
    }
}
class p3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] words = sc.nextLine().split(" ");
		System.out.println(find(words));
		sc.close();
	}
    public static int find(String[] words) {
        // Arrays.sort(words,(a,b)->b.length()-a.length());
        // StringBuilder sb = new StringBuilder();
        // for(String s:words){
        //     int id = sb.indexOf(s+"#");
        //     if(id==-1 ){
        //         // System.out.println(sb.toString());
        //         sb.append(s+"#");
        //     }
        // }
        // return sb.length();
        trie t = new trie();
        for(String word:words){
            StringBuilder sb = new StringBuilder();
            String rev = sb.append(word).reverse().toString();
            t.insert(rev);
        }   
        return t.search(t.root,0);
    }
}