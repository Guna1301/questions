/*Coach Arjun is training a cricket team and is experimenting with a new fitness 
evaluation drill. He provided the fitness scores of N players in the team. As 
part of the drill, he asked the team manager to keep track and perform these 
two specific operations on the players' fitness scores:

1. bestFitness(start, end) - Report the maximum fitness score among the players 
   whose jersey numbers lie between the positions start and end inclusive.
2. improveFitness(index, newScore) - Update the fitness score of the player at 
   the specific index position with a new fitness score newScore.

Your task is to efficiently handle these requests by using a Segment Tree data structure.

Input Format:  
-------------
Line-1: Two integers N and Q, representing the number of players and the total 
        number of queries respectively.
Line-2: N space-separated integers representing the initial fitness scores of 
        the players.
The next Q lines: Each line contains three integers: 
    - First integer (option) specifies the type of query:
      - If option = 1, the next two integers (start, end) represent the range 
        of jersey numbers (inclusive) for which to report the maximum fitness.
      - If option = 2, the next two integers (index, newScore) represent the 
        player's index to update and their new fitness score.

Output Format:  
--------------
- Output an integer value for every bestFitness query, representing the maximum 
  fitness score within the specified range.

Sample Input:  
-------------

8 5
1 2 13 4 25 16 17 28
1 2 6        //bestFitness
1 0 7        //bestFitness
2 2 18       //improveFitness
2 4 36       //improveFitness
1 2 7        //bestFitness


Sample Output:  
--------------
25
28
36
 */

import java.util.*;
class segment{
    int stree[];
    int n;
    segment(int arr[]){
        n = arr.length;
        stree = new int[4*n];
        build(arr,0,0,n-1);
    }
    public void build(int arr[], int i , int tl, int tr){
        if(tl==tr){
            stree[i] = arr[tl];
        }else{
            int mid = (tl+tr)/2;
            build(arr,2*i+1,tl,mid);
            build(arr,2*i+2,mid+1,tr);
            stree[i] = Math.max(stree[2*i+1],stree[2*i+2]);
        }
    }
    public int range(int l, int r){
        return max(0,0,n-1,l,r);
    }
    public int max(int i, int tl, int tr, int l, int r){
        if(r<tl || l>tr){
            return 0;
        }
        if(l<=tl && tr<=r){
            return stree[i];
        }
        int tm = (tl+tr)/2;
        int left = max(2*i+1,tl,tm,l,r);
        int right = max(2*i+2,tm+1,tr,l,r);
        return Math.max(left,right);
    }
    public void update(int pos,int val){
        up(0,0,n-1,pos,val);
    }
    public void up(int i, int tl, int tr, int pos, int val){
        if(tl==tr){
            stree[i] = val;
        }else{
            int tm = (tl+tr)/2;
            if(pos<=tm){
                up(2*i+1,tl,tm,pos,val);
            }else{
                up(2*i+2,tm+1,tr,pos,val);
            }
            stree[i] = Math.max(stree[2*i+1],stree[2*i+2]);
        }
    }
}

class p2{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        int qs[][] = new int[q][3];
        for(int i=0;i<q;i++){
            qs[i][0] = sc.nextInt();
            qs[i][1] = sc.nextInt();
            qs[i][2] = sc.nextInt();
        }
        segment s = new segment(arr);
        for(int i=0;i<q;i++){
            if(qs[i][0]==1){
                System.out.println(s.range(qs[i][1],qs[i][2]));
            }else{
                s.update(qs[i][1],qs[i][2]);
            }
        }
    }
}