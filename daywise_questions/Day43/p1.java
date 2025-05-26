/*You are managing a fleet of exploratory spacecraft, each carrying containers 
composed of two types of critical resources: 
fuel units (represented by '0') and oxygen tanks (represented by '1'). 
You're given a list 'containers', where each container is represented by a 
binary string indicating its resource composition, 
along with two integers: 'fuelLimit' (maximum allowed fuel units) and 
'oxygenLimit' (maximum allowed oxygen tanks).

Your goal is to select the largest possible subset of containers such that the 
total number of fuel units does not exceed 'fuelLimit' and the total number of 
oxygen tanks does not exceed 'oxygenLimit'.

Input format:
-------------
Line 1: Space seperated strings, represents the container strings
Line 2: Two space separated integers, represents fuelLimit & oxygenLimit

Output format:
--------------
An integer, largest possible subset of containers.


Example 1:
----------
Input=
10 0001 111001 1 0
5 3

Output=
4

Explanation: The largest subset meeting the constraints is {"10", "0001", "1", "0"}.
- Total fuel units = 5 (within limit)
- Total oxygen tanks = 3 (within limit)
Container "111001" cannot be included as it exceeds the oxygen tank limit.


Example 2:
----------
Input=
10 0 1
1 1

Output=
2

Explanation: The largest subset meeting the constraints is {"0", "1"}.
- Total fuel units = 1
- Total oxygen tanks = 1


Constraints:
- 1 <= containers.length <= 600
- 1 <= containers[i].length <= 100
- 'containers[i]' consists only of digits '0' and '1'.
- 1 <= fuelLimit, oxygenLimit <= 100
 */
import java.util.*;
class p1{
    static Map<String,Integer> dp = new HashMap<>();
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String cons[] = sc.nextLine().split(" ");
        int fl = sc.nextInt();
        int ol = sc.nextInt();
        
        System.out.println(find(0,cons,fl,ol));
        sc.close();
    }
    public static int find(int id, String cons[], int fl, int ol){
        if(id==cons.length){
            return 0;
        }
        String key = id+","+fl+","+ol;
        if(dp.containsKey(key)){
            return dp.get(key);
        }
        
        int nottake = find(id+1,cons,fl,ol);
        int take = 0;
        int zeros=0;
        int ones = 0;
        for(char ch:cons[id].toCharArray()){
            if(ch=='1'){
                ones++;
            }else{
                zeros++;
            }
        }
        if(ones<=ol && zeros<=fl){
            take = 1+find(id+1,cons,fl-zeros,ol-ones);
        }
        dp.put(key,Math.max(take,nottake));
        return dp.get(key);
    }
    
}