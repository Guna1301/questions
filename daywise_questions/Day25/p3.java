/*
 * A bookstore manager is analyzing the best-selling books from their records. 
 Each record contains a book title, author, and publishing date in the format 
 "DD-MM-YYYY".
 
 Your task is to extract and count the number of distinct publishing years from 
 the given list of best-selling books.
 
 Input Format
 ------------
 A string containing multiple book records, where each book entry includes a 
 publishing date in "DD-MM-YYYY" format.
 
 Output Format
 --------------
 Return an integer representing the total number of distinct publishing years 
 in the given records.
 
 
 Sample Input:
 --------------
 The Great Gatsby by F. Scott Fitzgerald was published on 10-04-1925. To Kill a Mockingbird by Harper Lee was published on 11-07-1960.
 
 Sample Output:
 ----------------
 2
 
 Explanation:
 --------------
 The books were published in 1925 and 1960.
 Total distinct years = 2.
 
 
 Sample Input:
 -------------
 1984 by George Orwell was published on 08-06-1949. Animal Farm by George Orwell was published on 17-08-1945. The Catcher in the Rye by J.D. Salinger was published on 16-07-1951
 
 Sample Output:
 ---------------
 3
 
 Explanation:
 -------------
 The books were published in 1945, 1949, and 1951.
 Total distinct years = 3.
 
 
 Sample Input:
 ---------------
 Pride and Prejudice by Jane Austen was published on 28-01-1812. Sense and Sensibility by Jane Austen was published on 30-10-1812. Emma by Jane Austen was published on 23-12-1812.
 
 Sample Output:
 ----------------
 1
 
 
 */
//NTE DRIVE
import java.util.*;
public class p3{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        HashSet<String> hs = new HashSet<>();
        for(int i = 0; i<s.length; i++){
            if(s[i].length() >=10 && s[i].charAt(2)=='-' && s[i].charAt(5)=='-'){
                hs.add(s[i].substring(5));
            }
        }
        System.out.println(hs.size());
    }
}