/*
Write a program that takes an IP address and subnet mask (in CIDR notation, 
e.g., 192.168.1.1/24) as input and calculates the subnet mask in dotted decimal 
format.

Input Format:
---------------
An integer, CIDR

Output Format:
---------------
String, Subnet's IP Address


Sample Input-1:
-----------------
25

Sample Output-1:
------------------
255.255.255.128


Sample Input-2:
-----------------
22

Sample Output-2:
------------------
255.255.252.0
*/


import java.util.*;
class subnet1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int cidr = sc.nextInt();
        int ip=0xFFFFFFFF;
        int myip = ip<<(32-cidr);
        System.out.println(inttoIP(myip));
    }
    static String inttoIP(int num){
        StringBuilder sb = new StringBuilder();
        sb.append(num>>24 & 0XFF);
        sb.append(".");
        sb.append(num>>16 & 0xFF);
        sb.append(".");
        sb.append(num>>8 & 0xFF);
        sb.append(".");
        sb.append(num & 0xFF);
        return sb.toString();
    }
}