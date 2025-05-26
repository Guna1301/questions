import java.util.*;
public class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int f = sc.nextInt();
        int n = sc.nextInt();
        int[][] food = new int[f][2];
        int[][] nest = new int[n][2];
        for(int i = 0; i<f; i++){
            food[i][0] = sc.nextInt();
            food[i][1] = sc.nextInt();
        }
        for(int i = 0; i<n; i++){
            nest[i][0] = sc.nextInt();
            nest[i][1] = sc.nextInt();
        }
        int[] home = new int[2];
        for(int i = 0; i<2; i++){
            home[i] = sc.nextInt();
        }
        double timelimit = sc.nextInt();
        boolean[] vis = new boolean[f];
        int maxfood = find(food, vis, nest, home, home, timelimit);
        System.out.println(maxfood);
        
    }
    static int find(int[][] food,boolean[] vis, int[][] nest, int[] home, int[] curr, double timelimit){
        if(timelimit<=0){
            return 0;
        }
        int ans = 0;
        for(int i = 0; i<food.length; i++){
            int findfood = 0;
            if(!vis[i]){
                vis[i] = true;
                timelimit -= distance(curr, food[i]);
                int[] nextnest = findminindex(nest, food[i]);
                if(nextnest[0]==-1 && nextnest[1]==-1){
                    return 0;
                }else{
                    findfood = 1+find(food,vis, nest, home, nextnest, timelimit);
                }
                vis[i]= false;
                timelimit += distance(curr, food[i]);
            }
            ans = Math.max(ans, findfood);
        }
        return ans;
        
    }
    
    static double distance(int[] x, int [] y){
        return Math.sqrt(Math.pow((x[1] - x[0]),2)+ Math.pow((y[1]-y[0]),2));
    }
    
    static int[] findminindex(int[][] nest, int[] food){
        int mini[] = {-1,-1};
        double mindistance = 0;
        for(int i =0; i<nest.length; i++){
            double dist = distance(nest[i], food);
            if(dist<mindistance){
                mindistance = dist;
                mini[0] = nest[i][0];
                mini[1] = nest[i][1];
            }
        }
        return mini;
    }
}