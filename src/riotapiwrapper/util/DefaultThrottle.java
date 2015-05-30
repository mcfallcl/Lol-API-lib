package riotapiwrapper.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import riotapiwrapper.request.Request;

/**
 * A default {@code RequestArbiter} for the user to use if they do not wish to
 * use their own. Keeps any back logged {@code Request}s in a 
 * {@code RequestQueue} until the rate limit opens up, then it sends the next
 * in queue.
 * 
 * @author Christopher McFall
 *
 */
public class DefaultThrottle implements RequestArbiter {
    
    private LinkedList<RequestHandlerPair> requestQueue;
    private Collection<RateLimit> rateLimits;
    
    private boolean workingQueue = false;
    
    /**
     * Creates a {@code DefaultThrottle} with the LoL API developer rate limits,
     * which are 10 requests/10 seconds and 500 requests/10 minutes(600 seconds).
     * All {@code Response}s received from the API server go through the given
     * {@code ResponseHandler}.
     * 
     * @throws  NullPointerException if handler is null.
     */
    public DefaultThrottle() {
        requestQueue = new LinkedList<RequestHandlerPair>();
    }
    
    
    /**
     * Creates a {@code DefaultThrottle} with specified rate limits. 
     * All {@code Response}s received from the API server go through the given 
     * {@code ResponseHandler}. If either 
     * N2 or T2 are 0, a second rate limit will not be used.
     * 
     * @param N1        Number of requests per time for the first rate limit.
     * @param T1        Time limit for the first rate limit.
     * @param N2        Number of request per time for the second rate limit.
     *                  If 0, a second rate limit will not be used.
     * @param T2        Time limit for the second rate limit. if 0, a second
     *                  rate limit will not be used.
     * @throws  NullPointerException if handler is null.
     * @throws  IllegalArgumentException if maxSize, N1, T1, N2 or T2 are < 0.
     */
    public DefaultThrottle(int N1, int T1, int N2, int T2) {
        requestQueue = new LinkedList<RequestHandlerPair>();
        addLimit(N1, T1);
        if (N2 != 0 || T2 != 0) addLimit(N2, T2);
    }
    
    @Override
    public void arbitrate(Request request, ResponseHandler handler) {
        if (!request.isRateLimited()) {
            handler.operate(request.send());
            return;
        }
        if (isAtLimit()) {
            requestQueue.add(new RequestHandlerPair(request, handler));
            workQueue();
            return;
        }
        handler.operate(request.send());
        addToLimits();
    }
    
    @Override
    public int backlog() {
        return requestQueue.size();
    }
    
    @Override
    public boolean isOpen() {
        for (RateLimit limit : rateLimits) {
            if (limit.isFull()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public void addLimit(int N, int T) {
        rateLimits.add(new RateLimit(N, T));
    }
    
    @Override
    public int numLimits() {
        return rateLimits.size();
    }
    
    private void sendNextInQueue() {
        if (requestQueue.isEmpty()) {
            throw new NullPointerException("the queue is empty");
        }
        RequestHandlerPair pair = requestQueue.remove();
        send(pair);
    }
    
    /*
     * Determines if the queue is going to be where further requests will be
     * sent from.
     */
    private void workQueue() {
        //makes sure the queue isn't being worked more than once.
        if (workingQueue == true) return;
        if (requestQueue.isEmpty()) {
            return;
        }
        workingQueue = true;
        Timer timer = new Timer("queue worker thread", true);
        /*
         * Sets a timer to check to see if a request can be sent every 200 ms.
         * 
         * When the queue is emptied, sets working queue to false until it is
         * required for to hold requests again.
         */
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (requestQueue.isEmpty()) {
                    timer.cancel();
                    workingQueue = false;
                } else if (isOpen()) {
                    sendNextInQueue();
                }
            }
        }, 0, 200);
    }
    
    private boolean isAtLimit() {
        if (!requestQueue.isEmpty()) {
            return true;
        }
        if (!isOpen()) {
            return true;
        }
        return false;
    }
    
    private void addToLimits() {
        for (RateLimit limit : rateLimits) {
            limit.add();
        }
    }
    
    private void send(RequestHandlerPair pair) {
        pair.handler.operate(pair.request.send());
    }
    
    /*
     * Simple class for keeping a request and it's handler paired through the
     * queue.
     */
    private class RequestHandlerPair {
        
        public final Request request;
        public final ResponseHandler handler;
        
        public RequestHandlerPair(Request r, ResponseHandler h) {
            this.request = r;
            this.handler = h;
        }
        
    }
    
}
