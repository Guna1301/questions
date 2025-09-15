import java.util.*;
import java.util.concurrent.*;

class Friend extends Thread {
    public CyclicBarrier barrier;
    public int id;
    public int puzzles;

    Friend(CyclicBarrier barrier, int id, int puzzles) {
        this.barrier = barrier;
        this.id = id;
        this.puzzles = puzzles;
    }

    public void run() {
        Random rand = new Random();
        try {
            for (int p = 1; p <= puzzles; p++) {
                int time = rand.nextInt(3) + 1;
                System.out.println("start " + id +" with "+time);
                Thread.sleep(time*1000);

                System.out.println("end " + id + " finished puzzle " + p);
                barrier.await();
            }
        } catch (Exception e) {
        }
    }
}

public class p2 {
    public static void main(String[] args) {
        int n = 4;
        int puzzles = 3;

        long start = System.currentTimeMillis();

        CyclicBarrier barrier = new CyclicBarrier(n, () -> {
            long now = System.currentTimeMillis();
            System.out.println("time " + (now - start)/1000);
        });

        for (int i = 1; i <= n; i++) {
            new Friend(barrier, i, puzzles).start();
        }
    }
}
