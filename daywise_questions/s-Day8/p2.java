/*Indus Infra Ltd purchased a land of size L * W acres, for their upcoming venture.
The land is divided into rectangular plots, using fences. They have kept some 
H horizontal fences as hfences[] and V vertical fences as vfences[] on the land,
where hfence[i] is the distance from the top of the land to the i-th horizontal
fence and, vfence[j] is the distance from the top of the land to the j-th 
vertical fence. Each 1*1 cell is one acre plot.

Mr.RGV wants to purchase the biggest plot available to build a Guest-house.
Your task is to help Mr.RGV to find the biggest plot vailable after the fences 
are setup in the venture.
NOTE: The answer can be a large number, return the modulo of 10^9 + 7.

Input Format:
-------------
Line-1: 4 space separated integers, L,W,H and V
Line-2: H space separated integers, hfence[] in the range [0, L]
Line-3: V space sepaarted integers, vfence[] in the range [0, W]

Output Format:
--------------
Print an integer result, the area of biggest plot.


Sample Input-1:
---------------
5 6 2 2
2 3
2 5

Sample Output-1:
----------------
6


Sample Input-2:
---------------
5 6 1 1
3
4

Sample Output-2:
----------------
12
 */

 import java.util.*;
class Solution{
    static int MOD = 1_000_000_007;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        int w = sc.nextInt();
        int h = sc.nextInt();
        int v = sc.nextInt();
        int hfence[] = new int[h];
        int vfence[] = new int[v];
        for(int i=0;i<h;i++){
            hfence[i] = sc.nextInt();
        }
        for(int j=0;j<v;j++){
            vfence[j] = sc.nextInt();
        }
        System.out.println(max(l,w,hfence,vfence));
        sc.close();
    }
    public static int max(int l, int w, int hfence[], int vfence[]){
        List<Integer> h = new ArrayList<>();
        List<Integer> v = new ArrayList<>();
        h.add(0);
        v.add(0);
        for(int b:hfence){
            h.add(b);
        }
        h.add(l);
        for(int b:vfence){
            v.add(b);
        }
        v.add(w);
        Collections.sort(h);
        Collections.sort(v);
        int maxh = 0;
        int maxv = 0;
        for(int i=1;i<h.size();i++){
            maxh = Math.max(maxh,h.get(i)-h.get(i-1));
        }
        for(int j=1;j<v.size();j++){
            maxv = Math.max(maxv,v.get(j)-v.get(j-1));
        }
        return (maxh*maxv)%MOD;
    }
}