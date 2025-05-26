/*There are N computers in a network, all the computers are connected as tree 
structure. And one new connection is added in the Network. The computers in 
the network are identified with their IDs, the IDs are numbered between 1 to N.

The connections in the network is given as coonection[i] = [comp-A, comp-B], 
there is a connection between comp-A and comp-B.

Your task is to remove a connection in the network and print it, so that 
all the computers are connected as tree structure. If there are multiple 
options to remove, remove the connection that occurs last in the input.


Input Format:
-------------
Line-1: Two space separated integers N, number of computers.
Next N lines: Two space separated integers, comp-A & comp-B.

Output Format:
--------------
Print the connection which is removed.


Sample Input-1:
---------------
6
1 2
3 4
3 6
4 5
5 6
2 3

Sample Output-1:
---------------
5 6


Sample Input-2:
---------------
4
1 2
2 3
3 4
2 4

Sample Output-2:
---------------
2 4
 */

import java.util.*;
class dsu{
    Map<Integer,Integer> parent = new HashMap<>();
    
    public int find(int x){
        if(!parent.containsKey(x)){
            parent.put(x,x);
        }
        if(parent.get(x)!=x){
            parent.put(x,find(parent.get(x)));
        }
        return parent.get(x);
    }
    public boolean union(int a, int b){
        int roota = find(a);
        int rootb = find(b);
        if(roota==rootb){
            return false;
        }
        parent.put(rootb,roota);
        return true;
    }
}
class copm_dsu{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n= sc.nextInt();
        int arr[][] = new int[n][2];
        for(int i=0;i<n;i++){
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        dsu u= new dsu();
        int ans[] = new int[2];
        for(int con[]:arr){
            if(u.find(con[0])==u.find(con[1])){
                ans[0] =con[0];
                ans[1] = con[1];
            }else{
                u.union(con[0],con[1]);
            }
            
        }
        
        
        System.out.println(ans[0]+" "+ans[1]);
        sc.close();
        
    }
}