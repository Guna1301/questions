/*Imagine you're an adventurer with a mystical treasure map. This map is a grid of 
ancient runes, where each cell holds a single character. Legend has a 
powerful incantation—represented as a string—is hidden within these runes. 
To unlock the treasure, you must verify if the incantation exists on the map.

The incantation is formed by linking runes that are directly next to each other 
either horizontally or vertically. Each rune on the map can only be used once in
the incantation.

Your Task:  
Given an m x n grid representing the treasure map and a string representing the 
incantation, return true if the incantation can be traced on the map; 
otherwise, return false.


Example 1:
----------
Input:  
3 4
ABCD
SFCS
ADEE
ABCCED

Output:
ABCCED can be traced

Explanation (check hint)
Treasure Map Grid:  
[
  ["A", "B", "C", "E"],
  ["S", "F", "C", "S"],
  ["A", "D", "E", "E"]
]

Incantation: "ABCCED" exists in map


Example 2:
----------
Input:
3 4
ABCE
SFCS
ADEE
ABCB

Output: 
ABCB cannot be traced

Explanation:
Treasure Map Grid:  

[
  ["A", "B", "C", "E"],
  ["S", "F", "C", "S"],
  ["A", "D", "E", "E"]
]

Incantation: "ABCB" does not exist in map


Constraints:

- m == the number of rows in the grid  
- n == the number of columns in the grid  
- 1 <= m, n <= 6  
- 1 <= incantation length <= 15  
- The grid and incantation consist only of uppercase and lowercase English letters.
 */
import java.util.*;
public class wordsearch {

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        sc.nextLine();
        char grid[][] = new char[m][n];
        for(int i=0;i<m;i++){
            String s = sc.nextLine();
            for(int j=0;j<n;j++){
                grid[i][j] = s.charAt(j);
            }
        }
        String s = sc.nextLine();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==s.charAt(0)){
                    if(find(i,j,0,grid,s)){
                        System.out.println(s+" can be traced");
                        return;
                    }
                }
            }
        }
        
            System.out.println(s+" cannot be traced");
        
    }
    public static boolean find(int i,int j, int id,char grid[][], String s){
        int m = grid.length;
        int n = grid[0].length;
        if(id==s.length()){
            return true;
        }
        if(i<0 || i>=m || j<0 || j>=n || grid[i][j]!=s.charAt(id) || grid[i][j]=='#'){
            return false;
        }
        char temp = grid[i][j];
        grid[i][j] = '#';
        boolean left = find(i-1,j,id+1,grid,s);
        boolean right = find(i,j-1,id+1,grid,s);
        boolean top = find(i+1,j,id+1,grid,s);
        boolean down = find(i,j+1,id+1,grid,s);
        grid[i][j] = temp;
        return left||right||top||down;
    }

}