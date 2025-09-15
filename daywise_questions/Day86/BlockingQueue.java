import java.util.*;


public class BlockingQueue {
    int capacity;
    Queue<Integer> q = new LinkedList<>();
    BlockingQueue(int n){
        this.capacity = n;
    }

    public void enqueue(int val){
        if(q.size()==capacity){
            return;
        }
        q.offer(val);
    }

    public int dequeue(){
        if(q.isEmpty()){
            return -1;
        }
        return q.poll();
    }

}
