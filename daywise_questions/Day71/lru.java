/*You are building memory management for a smart home hub where apps are loaded on-demand. 
The hub has limited memory (cache size). If an app is not in memory (cache miss), 
it is loaded and a page fault occurs. If memory is full, the Least Recently Used 
(LRU) app is removed.

Simulate the LRU page replacement and count total page faults.

Input Format:
-------------
- Cache size
- Space-separated app access sequence (e.g., Thermostat Camera Lights)

Output Format:
--------------
Total page faults
Final cache contents

Sample Input:
-------------
3
Thermostat Camera Lights Thermostat Camera Doorbell Lights Thermostat

Sample Output:
--------------
Total Page Faults: 6
Final Cache: [Doorbell, Lights, Thermostat]

Sample Input:
--------------
2
AC Light Fan AC Heater Light

Sample Output:
--------------
Total Page Faults: 6
Final Cache: [Heater, Light]
 */
import java.util.*;
class lru{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int cap = sc.nextInt();
        sc.nextLine();
        String words[] = sc.nextLine().split(" ");
        find(words,cap);
    }
    public static void find(String words[], int cap){
        Set<String> set = new LinkedHashSet<>();
        int ans = 0;
        for(int i=0;i<words.length;i++){
            if(set.contains(words[i])){
                set.remove(words[i]);
                set.add(words[i]);
            }else{
                ans++;
                if(set.size()<cap){
                    set.add(words[i]);
                }else{
                    Iterator<String> it = set.iterator();
                    if(it.hasNext()){
                        String f = it.next();
                        set.remove(f);
                        set.add(words[i]);
                    }
                    
                }
                
            }
        }
        System.out.println("Total Page Faults: "+ans);
        System.out.println("Final Cache: "+set);
        
    }
}