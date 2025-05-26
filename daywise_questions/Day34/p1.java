/*Imagine a unique circular conveyor belt in a sushi restaurant, which serves 
exactly 26 distinct dishes labeled by 26 unique lowercase English letters 
('a' to 'z'). The dishes are arranged linearly along the conveyor belt, indexed 
from 0 to 25 according to the given order. Initially, a robotic serving arm is 
positioned at index 0.

Whenever a customer orders a specific dish, the robotic arm moves from its current 
position to the position of the desired dish along the belt. The robotic arm takes 
exactly one second per unit distance to move along the conveyor belt (the time 
taken from index i to index j is |i - j| seconds).

Given the conveyor belt layout (order of dishes) and a customer's order represented 
as a word (sequence of dishes), write a code to calculate the total time the robotic 
serving arm will take to serve the customer's entire order.


Input Format:
-------------
Line-1: A String, belt layout.
Line-2: A String, word: customer's order.

Output Format:
--------------
An integer T, time to serve.


Sample Output-1:
----------------
abcdefghijklmnopqrstuvwxyz
code

Sample Output-1:
----------------
26
 */
import java.util.*;
class p1{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String belt = sc.nextLine();
        String word = sc.nextLine();
        System.out.println(find(belt,word));
    }
    public static int find(String belt, String word){
        int ids[] = new int[26];
        for(int i=0;i<belt.length();i++){
            char ch = belt.charAt(i);
            ids[ch-'a'] = i;
        }
        int prev = 0;
        int ans = 0;
        for(char ch:word.toCharArray()){
            int next = ids[ch-'a'];
            ans += Math.abs(next-prev);
            prev = next;
        }
        return ans;
    }
}