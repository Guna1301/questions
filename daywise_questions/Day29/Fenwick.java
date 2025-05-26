/*
 * Imagine you are the chief curator at a futuristic museum. The museum consists of
 N exhibition halls arranged in a row, and each hall consists of certain number 
 of rare artifacts. 
 Your job is to keep track of the total number of artifacts on display and to 
 manage special events that temporarily increase the artifact counts in a block 
 of halls.
 
 You need to support two types of operations:
 1. Count Artifacts: Calculate the total number of artifacts displayed in the 
    exhibition halls between indices start and end (inclusive).  
 2. Host Special Exhibition: For a given range of halls (from start to end), 
    increase the number of artifacts in each hall by a specified amount. 
    This simulates a special exhibition event that attracts additional artifacts.
 
 Input Format:
 -------------
 Line 1: Two integers N and Q, where N is the number of exhibition halls and Q is the number of operations.
 Line 2: N space-separated integers representing the initial artifact counts in each hall.
 Next Q Lines: Each line contains a query in one of the following formats:
   - For a Count Artifacts query:  
     1 start end
   - For a Host Special Exhibition event:  
     2 start end increment
 
 Output Format:
 --------------
 For every Count Artifacts query (operation 1), output an integer representing the total number of artifacts in the specified range.
 
 Example
 -------
 Input:
 8 5
 1 2 13 4 25 16 17 8
 1 2 6
 1 0 7
 2 2 4 3
 2 5 7 2
 1 2 7
 
 Output:
 75
 86
 98
 
 
 Explanation:
 - The initial artifact counts in the halls are: [1, 2, 13, 4, 25, 16, 17, 8].
 - Query 1: 1 2 6 → Sum halls 2 to 6: 13 + 4 + 25 + 16 + 17 = 75.
 - Query 2: 1 0 7 → Sum halls 0 to 7: 1 + 2 + 13 + 4 + 25 + 16 + 17 + 8 = 86.
 - Query 3: 2 2 4 3 → A special exhibition increases artifact counts in halls 
   2, 3, and 4 by 3. New counts become: [1, 2, 16, 7, 28, 16, 17, 8].
 - Query 4: 2 5 7 2 → Another event boosts halls 5, 6, and 7 by 2. New counts 
   become: [1, 2, 16, 7, 28, 18, 19, 10].
 - Query 5: 1 2 7 → Sum halls 2 to 7: 16 + 7 + 28 + 18 + 19 + 10 = 98.
 
 
 Constraints
 - 1 ≤ N ≤ 3×10^4  
 - -100 ≤ artifact count in each hall ≤ 100  
 - 0 ≤ start ≤ end < N  
 - -100 ≤ increment ≤ 100  
 - At most 3×10^4 operations will be performed.
 */

import java.util.*;
class Fenwick{
    int n;
    int[] bit;
    Fenwick(int size){
        this.n = size;
        this.bit = new int[n];
    }
    
    public void update(int i, int d){
        while(i<n){
            bit[i]+=d;
            i = i|(i+1);
        }
    }
    
    public void build(int[] arr){
        for(int i = 0; i<n; i++){
            update(i, arr[i]);
        }
    }
    
    public void rangeupdate(int l, int r, int num){
        for(int i = l; i<=r; i++){
            update(i, num);
        }
    }
    
    public int sum(int i){
        int s = 0;
        while(i>=0){
            s+= bit[i];
            i = (i&(i+1))-1;
        }
        return s;
    }
    
    public int rangeSum(int x, int y){
        return sum(y) - sum(x-1);
    }
}

class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        Fenwick s = new Fenwick(n);
        s.build(arr);
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i<m; i++){
            int x = sc.nextInt();
            if(x==1){
                int y = sc.nextInt();
                int z = sc.nextInt();
                ans.add(s.rangeSum(y,z));
                  //System.out.println(Arrays.toString(arr));
            }else{
                int l = sc.nextInt();
                int r = sc.nextInt();
                int num = sc.nextInt();
                s.rangeupdate(l,r,num);
            }
        }
        for(int num: ans){
            System.out.println(num);
        }
    }
}