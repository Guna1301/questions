/*Shaggy Rogers is working with numbers.
He is given a number N, 
He wants to replace all occurrences of a digit in the number N,
with another digit between [0-9]. May be with same number too.

But there should not be any leading zeros in the number after the replacement,
Or the number should not become zero.

Rogers can perform the replacement of the occurrences of the digit in N for two 
times, He will generate two new numbers P and Q, such that the value of |P-Q| 
should be Maximum.

Your task is to help Shaggy Roers to find the maximum difference of P and Q possible.


Input Format:
-------------
An integer N, the number

Output Format:
--------------
Print an integer, the maximum difference of P and Q


Sample Input-1:
---------------
123

Sample Output-1:
----------------
820

Explanation:
------------
Replacement-1: replace 1 with 9 you will get P as 923
Replacement-2: replace 2 with 0 you will get Q as 103
So Max difference is 820.


Sample Input-2:
---------------
4254

Sample Output-2:
----------------
8008

Explanation:
------------
Replacement-1: replace 4 with 9 you will get P as 9259
Replacement-2: replace 4 with 1 you will get Q as 1251
So Max difference is 8008.
 */

 import java.util.*;
class p1{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(find(n));
        sc.close();
    }
    public static int find(int n){
        String num = Integer.toString(n);
        
        char max = ' ';
        for(char ch:num.toCharArray()){
            if(ch!='9'){
                max = ch;
                break;
            }
        }
        
        String p = num.replace(max,'9');
        
        char dum = num.charAt(0);
        String q;
        if(dum!='1'){
            q = num.replace(dum,'1');
        }else{
            char min = ' ';
            boolean found = false;
            for(int i=1;i<num.length();i++){
                char ch = num.charAt(i);
                if(ch!='0' && ch!='1'){
                    min = ch;
                    found = true;
                    break;
                }
            }
            if(found){
                q = num.replace(min,'0');
            }else{
                q = num;
            }
            
        }
        // System.out.println(p+" "+q);
        return Integer.parseInt(p) - Integer.parseInt(q);
        
    }
    
}