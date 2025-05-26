/*
 * Two brothers want to play a game, 
     The rules of the game are: one player gives two sorted lists of 
     numerical elements and a number (sum). 
     The opponent has to find the closest pair of elements 
     to the given sum.
     -> pair consists of elements from each list
     
     Please help those brothers to develop a program, that takes 
     two sorted lists as input and return a pair as output.
     
     Input Format:
     -------------
     size of list_1
     list_1 values
     size of list_2
     list_2 values
     closest number
     
     Output Format:
     --------------
     comma-separated pair
     
     Sample Input-1:
     ---------------
     4
     1 4 5 7
     4
     10 20 30 40
     32
     Sample Output-1
     ---------------
     1, 30
     
     Sample Input-2
     ---------------
     3
     2 4 6
     4
     5 7 11 13
     15
     sample output-2
     ---------------
     2, 13
 
 */
import java.util.*;
class Pairs{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int arr1[] = new int[n1];
        for(int i=0;i<n1;i++){
            arr1[i] = sc.nextInt();
        }
        int n2 = sc.nextInt();
        int arr2[] = new int[n2];
        for(int i=0;i<n2;i++){
            arr2[i] = sc.nextInt();
        }
        int target = sc.nextInt();
        System.out.println(find(arr1,arr2,target));
        sc.close();
    }
    public static String find(int arr1[], int arr2[], int target){
        int diff = target;
        int ans[] = new int[]{-1,-1};
        int i =0;
        int j = arr2.length-1;
        while(i<arr1.length && j>=0){
            // int rem = target - arr1[i];
            // int id = binary(arr2,rem);
            int d = Math.abs((arr1[i]+arr2[j])-target);
            if(d<diff){
                diff = d;
                ans[0] = arr1[i];
                ans[1] = arr2[j];
            }else if((arr1[i]+arr2[j])>target){
                j--;
            }else{
                i++;
            }
            
        }
        return ans[0]+", "+ans[1];
    }
    // public static int binary(int arr2[], int target){
    //     // System.out.println("in binary " +target);
    //     int left = 0;
    //     int right = arr2.length-1;
    //     while(left<right){
    //         int mid = left + (right-left)/2;
    //         if(arr2[mid]==target){
    //             return mid;
    //         }else if(arr2[mid]<target){
    //             left = mid+1;
    //         }else{
    //             right = mid;
    //         }
    //     }
    //     if(left==0)return 0;
    //     if(left==arr2.length) return arr2.length-1;
    //     int n1 = Math.abs(arr2[left]-target);
    //     int n2 = Math.abs(arr2[left-1]-target);
    //     return n1<n2?left:left-1;
    // }
}
