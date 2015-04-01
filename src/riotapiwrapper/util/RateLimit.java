package riotapiwrapper.util;

import java.util.Timer;
import java.util.TimerTask;


class RateLimit {

    final int max;
    final int time;                //in seconds
    private int current;
    private Timer timer;
    
    RateLimit(int N, int T) {
        if (N < 1 || T < 1) {
            throw new IllegalArgumentException("N and T cannot be negative");
        }
        this.max = N;
        this.time = T;
        this.current = 0;
    }
    
    void add() {
        if (isFull()) throw new IllegalStateException("added while full");
        if (current == 0) timer = new Timer();
        ++current;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                remove();
            }
        }, time * 1000);
    }
    
    private void remove() {
        --current;
        if (current == 0) timer.cancel();
    }
    
    boolean isFull() {
        return (current >= max);
    }
    
    public int current() {
        return current;
    }
    
    @Override
    public String toString() {
        return current + "/" + max;
    }
    
    public static void main(String[] args) {
        RateLimit limit = new RateLimit(5, 5);
        limit.add();
        limit.add();
        limit.add();
        limit.add();
        limit.add();
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                limit.add();
                t.cancel();
            }
        }, 6000);
    }
    
}
