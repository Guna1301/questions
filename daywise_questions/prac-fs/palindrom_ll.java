/*
Cliff Shaw is working on the singly linked list.
He is given a list of boxes arranged as singly linked list,
where each box is printed a positive number on it.

Your task is to help Mr Cliff to find the given list is equivalent to 
the reverse of it or not. If yes, print "true", otherwise print "false"

Input Format:
-------------
Line-1: space separated integers, boxes as list.

Output Format:
-------------- 
Print a boolean a value.


Sample Input-1:
---------------
3 6 2 6 3

Sample Output-1:
----------------
true


Sample Input-2:
---------------
3 6 2 3 6

Sample Output-2:
----------------
false
*/ 


import java.util.*;
class node{
    int val;
    node next;
    node(int val){
        this.val = val;
        this.next = null;
    }
}
class ll{
    public node build(int arr[]){
        int id = 1;
        node head = new node(arr[0]);
        node temp= head;
        while(id<arr.length){
            temp.next =  new node(arr[id]);
            temp = temp.next;
            id++;
            
        }
        return head;
    }
    public boolean find(node root){
        if(root==null){
            return true;
        }
        node first = null;
        node second = root;
        node fast = root;
        node temp;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            temp = second.next;
            second.next = first;
            first  = second;
            second = temp;
        }
        if(fast!=null){
            second = second.next;
        }
        while(first!=null && second!=null){
            if(first.val!=second.val){
                return false;
            }
            first = first.next;
            second = second.next;
        }
        return true;
    }
    public void print(node root){
        node temp = root;
        while(temp!=null){
            System.out.print(root.val);
            temp =temp.next;
        }
    }
    
}
public class palindrom_ll {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String vals[] = sc.nextLine().split(" ");
        int n = vals.length;
        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(vals[i]);
        }
        ll l = new ll();
        node root = l.build(arr);
        System.out.println(l.find(root));
        sc.close();
        
    }
}