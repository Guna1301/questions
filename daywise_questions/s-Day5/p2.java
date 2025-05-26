/*There are N employees from 3 different companies in a row, emps[], the employees 
are identified using company IDs as 1,2,3. A Courier Boy need to deliver 
P parcels to these 3 companies. The parcel details are given as parcels[],
where parcel[i]=[Ci,CIDi], i-th parcel, 'Ci' is Courier Boy's current position, 
and  'CIDi' is company's ID, he/she need to deliver parcel[i] from Ci position 
to the nearest employee belongs to companay ID equals to CIDi in the row.

You are given emps[] and parcels[] information,
Your task is to help the courier boy to find the distance between him to 
the nearest employee in that row to deliver the parcel.
If there is no solution found, return -1.

Input Format:
-------------
Line-1: Two space separated integers, N and P
Line-2: N space separated integers, only 1, 2 & 3.
Next P lines: Two space separated integers, position Ci and Company ID

Output Format:
--------------
Print the space separated integers, distance between boy and the employee for 
all the parcels.


Sample Input-1:
---------------
6 2
1 1 2 2 3 3
1 3
2 2

Sample Output-1:
----------------
3 0 

Sample Input-2:
---------------
5 2
1 2 3 2 1
2 1
1 1

Sample Output-2:
----------------
2 1 

 */

 import java.util.*;
class p2{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();
        int emps[] = new int[n];
        int parcels[][] = new int[p][2];
        for(int i=0;i<n;i++){
            emps[i]= sc.nextInt();
        }
        for(int i=0;i<p;i++){
            parcels[i][0] = sc.nextInt();
            parcels[i][1] = sc.nextInt();
        }
        int ans[] = new int[p];
        find(n,p,emps,parcels,ans);
        for(int i=0;i<p;i++){
            System.out.print(ans[i]+" ");
        }
        sc.close();
    }
    public static void find(int n, int p, int emps[],int parcels[][], int ans[]){
        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int i=0;i<n;i++){
            map.putIfAbsent(emps[i],new ArrayList<>());
            map.get(emps[i]).add(i);
        }
        for(int i=0;i<p;i++){
            int pos = parcels[i][0];
            int id = parcels[i][1];
            int min = Integer.MAX_VALUE;
            if(!map.containsKey(id) ||map.get(id).size()==0){
                ans[i] = -1;
                continue;
            }
            for(int idx:map.get(id)){
                min = Math.min(min,Math.abs(idx-pos));
            }
            ans[i] = min;
        }
    }
}