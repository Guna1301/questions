/*Imagine you’re managing a busy cafe where every order is logged. You have a 
record—a list of dish names ordered throughout the day—and you want to determine
which dishes are the most popular. Given an list of strings representing the dish
names and an integer P, your task is to return the P most frequently ordered dishes.

The results must be sorted by the number of orders, from the most frequent to 
the least. If two dishes have been ordered the same number of times, they should
be listed in alphabetical order.

Input Format:
-------------
Line-1: comma separated line of words, list of words.
Line-2: An integer P, number of words to display. 

Output Format:
--------------
Print P number of most common used words.

Example 1:
----------
Input=
coffee,sandwich,coffee,tea,sandwich,muffin
2
Output=
[coffee, sandwich]

Explanation: "coffee" and "sandwich" are the two most frequently ordered items. 
Although both appear frequently, "coffee" is placed before "sandwich" because 
it comes earlier alphabetically.

Example 2:
----------
Input=
bagel,muffin,scone,bagel,bagel,scone,scone,muffin,muffin
3
Output=
[bagel, muffin, scone] 

Explanation: "bagel", "muffin", and "scone" are the three most popular dishes 
with order frequencies of 3, 3, and 2 respectively. Since "bagel" and "muffin" 
have the same frequency, they are ordered alphabetically.

Constraints:

- 1 ≤ orders.length ≤ 500  
- 1 ≤ orders[i].length ≤ 10  
- Each orders[i] consists of lowercase English letters.  
- P is in the range [1, The number of unique dish names in orders]. */
import java.util.*;
class trien{
    trien children[];
    boolean end;
    int count;
    trien(){
        children = new trien[26];
        end = false;
        count=0;
    }

}

class trie{
    trien root = new trien();
    public void insert(String s){
        trien curr = root;
        for(char ch:s.toCharArray()){
            int id = ch-'a';
            if(curr.children[id]==null){
                curr.children[id]  = new trien();
            }
            curr = curr.children[id];
        }
        curr.end = true;
        curr.count++;
    }
    public int search(String s){
        trien curr = root;
        boolean flag = false;
        for(char ch:s.toCharArray()){
            int id = ch-'a';
            if(curr.children[id]==null){
                return 0;
            }
            curr = curr.children[id];
        }
        
        return curr.count;
    }
}
class p2{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s1[] = sc.nextLine().split(",");
        int p = sc.nextInt();
        trie t = new trie();
        for(String s:s1){
            t.insert(s);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->b[1]==a[1]?(s1[a[0]].compareTo(s1[b[0]])):b[1]-a[1]);
        
        int i=-1;
        Set<String> set = new HashSet<>();
        for(String s:s1){
            if(set.contains(s))continue;
            int freq = t.search(s);
            pq.offer(new int[]{i,freq});
            i++;
            set.add(s);
        }
        List<String> ans = new ArrayList<>();
        while(p>0){
            int id = pq.poll()[0];
            ans.add(s1[id]);
            p--;
        }
        System.out.println(ans);
        
    }

}