import java.util.*;
import java.util.concurrent.*;

public class Threads{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HiHelloGame hg = new HiHelloGame(n);
        Thread a = new Thread(()->{
            try {
                hg.hi();
            } catch (Exception e) {
                
            }
        });
        Thread b = new Thread(()->{
            try {
                hg.hello();
            } catch (Exception e) {
                
            }
        });
        Thread c = new Thread(()->{
            try {
                hg.hihello();
            } catch (Exception e) {
                
            }
        });
        Thread d = new Thread(()->{
            try {
                hg.number();
            } catch (Exception e) {
                
            }
        });
        a.start();
        b.start();
        c.start();
        d.start();
    }
}
class HiHelloGame{
    int curr = 1;
    int n;
    HiHelloGame(int n){
        this.n = n;
    }
    public synchronized void hi() throws InterruptedException {
        while(curr<=n){
            if(curr%3==0 && curr%5!=0){
                System.out.print("hi ");
                curr++;
                notifyAll();
            }else{
                wait();
            }
        }
        
    }
    
    public synchronized void hello() throws InterruptedException{
        while(curr<=n){
            if(curr%3!=0 && curr%5==0){
                System.out.print("hello ");
                curr++;
                notifyAll();
            }else{
                wait();
            }
        }
    }
    
    public synchronized void hihello() throws InterruptedException{
        while(curr<=n){
            if(curr%3==0 && curr%5==0){
                System.out.print("hiHello ");
                curr++;
                notifyAll();
            }else{
                wait();
            }
        }
    }
    
    public synchronized void number() throws InterruptedException{
        while(curr<=n){
            if(curr%3!=0 && curr%5!=0){
                System.out.print(curr+" ");
                curr++;
                notifyAll();
            }else{
                wait();
            }
        }
    }
}