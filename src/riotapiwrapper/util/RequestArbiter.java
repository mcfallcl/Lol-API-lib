package riotapiwrapper.util;

import riotapiwrapper.request.Request;


/**
 * Abstract class used to determine what is going to happen to requests made
 * based on what type they are and the rate limits given. Meant for its
 * subclasses to have some sort of container to hold requests until they can be
 * sent without violating the rate limit.
 * 
 * 
 * @author  Christopher McFall  
 *
 */
public interface RequestArbiter {
    
    
    /**
     * Returns the number of rate limits for the current arbiter.
     * 
     * @return The number of current rate limits for the current arbiter.
     */
    public int numLimits();
    
    /**
     * Adds a limit to the current arbiter.
     * 
     * @param N     The maximum amount of request allowed within the time 
     *              limit.
     * @param T     The amount of time limit in seconds.
     * @throws      IllegalStateException if there are already two limits in
     *              place.
     * @throws      IllegalArgumentException if either N or T are below 0.
     */
    public void addLimit(int N, int T);
    
    /**
     * Abstract method meant to be used to determine what is going to happen
     * with a request, based on the rate limit and type of request.
     * 
     * @param request   The request to be arbitrated.
     */
    public abstract void arbitrate(Request request);
    
    /**
     * Returns if a request can be sent without violating the rate limit.
     * 
     * @return  true if a request can be sent without violating the rate limit.
     */
    public boolean isOpen();
    
    /**
     * Returns the number of request waiting to be sent.
     * 
     * @return  The number of requests waiting to be sent.
     */
    public abstract int backlog();
    
}
