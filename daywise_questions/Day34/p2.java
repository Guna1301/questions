/*Bablu is working in a construction field.
He has N number of building blocks, where the height and width of all the blocks are same.
And the length of each block is given in an array, blocks[].

Bablu is planned to build a wall in the form of a square.
The rules to cunstruct the wall are as follows:
	- He should use all the building blocks.
	- He should not break any building block, but you can attach them with other.
	- Each building-block must be used only once.
	
Your task is to check whether Bablu can cunstruct the wall as a square
with the given rules or not. If possible, print true. Otherwise, print false.

Input Format:
-------------
Line-1: An integer N, number of BuildingBlocks.
Line-2: N space separated integers, length of each block.

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
6
1 2 6 4 5 6

Sample Output-1:
----------------
true


Sample Input-2:
---------------
6
5 3 2 5 5 6

Sample Output-2:
----------------
false */
import java.util.*;
class p2{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = 0;
        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
            sum+= arr[i];
        }
        if(sum%4!=0){
            System.out.println(false);
            return;
        }
        int target = sum/4;
        boolean vis[] = new boolean[n];
        int sides[] = new int[4];
        System.out.println(find(0,arr,target,sides));
    }
    public static boolean find(int id, int arr[],int target,int sides[] ){
        
        if(id>=arr.length){
            for(int i=0;i<4;i++){
                if(sides[i]!=target){
                    return false;
                }
            }
            return true;
        }
        for(int i=0;i<4;i++){
            if(sides[i]+arr[id]<=target){
                sides[i]+=arr[id];
                if(find(id+1,arr,target,sides)){
                    return true;
                }
                sides[i]-=arr[id];
            }
            
        }
        return false;
        
    }
}