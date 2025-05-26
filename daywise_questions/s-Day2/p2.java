/*Venkatadri is a maths teacher.
He is teaching matrices to his students.
He is given a matrix of size m*n, and it contains only positive numbers.
He has given a task to his students to find the special matrix, 
in the iven matrix A[m][n].
A special matrix has following property:
	- The sum of elements in each row, each column and the two diagonals are equal.
	- Every 1*1 matrix is called as a special matrix.
	- The size of the special matrix should be a square, i.e., P*P.

Your task is to help the students to find the speical matrix  with max size P.


Input Format:
-------------
Line-1: Two space separated integers M and N, size of the matrix.
Next M lines: N space separated integers m and n.

Output Format:
--------------
Print an integer, maximum size P of the special matrix.


Sample Input-1:
---------------
5 5
7 8 3 5 6
3 5 1 6 7
3 5 4 3 1
6 2 7 3 2
5 4 7 6 2

Sample Output-1:
----------------
3

Explanation:
------------
The special square is:
5 1 6
5 4 3
2 7 3


Sample Input-2:
---------------
4 4
7 8 3 5
3 2 1 6
3 2 3 3
6 2 3 3

Sample Output-2:
----------------
2

Explanation:
------------
The special square is:
3 3
3 3
 */

 import java.util.*;
class p2{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int mat[][] = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                mat[i][j] = sc.nextInt();
            }
        }
        System.out.println(find(mat,m,n));
    }
    public static int find(int mat[][], int m, int n){
        int prerow[][] = new int[m][n+1];
        int precol[][] = new int[n][m+1];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                prerow[i][j+1] = prerow[i][j]+mat[i][j]; 
            }
        }
        for(int j=0;j<n;j++){
            for(int i=0;i<m;i++){
                precol[j][i+1] = precol[j][i] + mat[i][j];
            }
        }
        int max = Math.min(m,n);
        for(int size=max;size>=1;size--){
            for(int i=0;i<=m-size;i++){
                for(int j=0;j<=n-size;j++){
                    if(special(mat,i,j,size,prerow,precol)){
                        return size;
                    }
                }
            }
        }
        return 1;
    }
    public static boolean special(int mat[][], int r, int c, int size, int prerow[][], int precol[][]){
        int target = prerow[r][c+size]-prerow[r][c];
        for(int i=r;i<r+size;i++){
            int sum = prerow[i][c+size]-prerow[i][c];
            if(sum!=target){
                return false;
            }
        }
        for(int j=c;j<c+size;j++){
            int sum = precol[j][r+size]-precol[j][r];
            if(sum!=target){
                return false;
            }
        }
        int d1 = 0;
        for(int i=0;i<size;i++){
            d1+=mat[r+i][c+i];
        }
        if(d1!=target){
            return false;
        }
        int d2 =0;
        for(int i=0;i<size;i++){
            d2+=mat[r+i][c+size-i-1];
        }
        if(target!=d2){
            return false;
        }
        return true;
    }
}