package riotapiwrapper.util;

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
public class DefaultThrottle extends RequestArbiter {
    
    private RequestQueue requestQueue;
    private String message;
    private ResponseHandler handler;
    private boolean workingQueue = false;
    
    /**
     * Creates a {@code DefaultThrottle} with the LoL API developer rate limits,
     * which are 10 requests/10 seconds and 500 requests/10 minutes(600 seconds).
     * All {@code Response}s received from the API server go through the given
     * {@code ResponseHandler}.
     * 
     * @param handler   The {@code ResponseHandler} used to handle 
     *                  {@code Response}s received from the API server.
     * @throws  NullPointerException if handler is null.
     */
    public DefaultThrottle(ResponseHandler handler) {
        this.handler = handler;
        requestQueue = new RequestQueue(10000);
    }
    
    /**
     * Creates a {@code DefaultThrottle} with specified rate limits. All
     * {@code Response}s received from the API server go through the given
     * {@code ResponseHandler}. If either N2 or T2 are 0, a second rate limit
     * will not be used.
     * 
     * @param handler   The {@code ResponseHandler} used to handle
     *                  {@code Response}s received from the API server.
     * @param N1        Number of requests per time for the first rate limit.
     * @param T1        Time limit for the first rate limit.
     * @param N2        Number of request per time for the second rate limit.
     *                  If 0, a second rate limit will not be used.
     * @param T2        Time limit for the second rate limit. if 0, a second
     *                  rate limit will not be used.
     * @throws  NullPointerException if handler is null.
     * @throws  IllegalArgumentException if N1, T1, N2 or T2 are < 0.
     */
    public DefaultThrottle(ResponseHandler handler, int N1, int T1,
            int N2, int T2) {
        this(handler, 100000, N1, T1, N2, T2);
    }
    
    /**
     * Creates a {@code DefaultThrottle} with specified rate limits and a max 
     * size for the {@code RequestQueue}. All {@code Response}s received from 
     * the API server go through the given {@code ResponseHandler}. If either 
     * N2 or T2 are 0, a second rate limit will not be used.
     * 
     * @param handler   The {@code ResponseHandler} used to handle
     *                  {@code Response}s received from the API server.
     * @param maxSize   The max size for the {@code RequestQueue}.
     * @param N1        Number of requests per time for the first rate limit.
     * @param T1        Time limit for the first rate limit.
     * @param N2        Number of request per time for the second rate limit.
     *                  If 0, a second rate limit will not be used.
     * @param T2        Time limit for the second rate limit. if 0, a second
     *                  rate limit will not be used.
     * @throws  NullPointerException if handler is null.
     * @throws  IllegalArgumentException if maxSize, N1, T1, N2 or T2 are < 0.
     */
    public DefaultThrottle(ResponseHandler handler, int maxSize,
            int N1, int T1, int N2, int T2) {
        if (handler == null) {
            throw new NullPointerException();
        }
        this.handler = handler;
        requestQueue = new RequestQueue(maxSize);
        addLimit(N1, T1);
        if (N2 != 0 || T2 != 0) addLimit(N2, T2);
    }
    
    @Override
    public void arbitrate(Request request) {
        if (!request.isRateLimited()) {
            handler.operate(request.send());
            return;
        }
        if (isAtLimit()) {
            requestQueue.add(request);
            workQueue();
            return;
        }
        send(request);
    }
    
    @Override
    public int backlog() {
        return requestQueue.size();
    }
    
    private void sendNextInQueue() {
        if (requestQueue.isEmpty()) {
            throw new NullPointerException("the queue is empty");
        }
        send(requestQueue.remove());
    }
    
    /*
     * Determines if the queue is going to be where further requests will be
     * sent from.
     */
    private void workQueue() {
        //makes sure the queue isn't being worked more than once.
        if (workingQueue == true) return;
        if (requestQueue.isEmpty()) {
            message = "The queue is empty";
            return;
        }
        workingQueue = true;
        message = "Working the queue.";
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
    
    private void send(Request request) {
        handler.operate(request.send());
        addToLimits();
    }
    
    /**
     * Returns the current message from the {@code DefaultThrottle}.
     * 
     * @return  The current message from the {@code DefaultThrottle}.
     */
    public String getMessage() {
        return message;
    }
    
}
