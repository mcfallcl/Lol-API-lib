package riotapiwrapper.util;

import riotapiwrapper.request.Response;

/**
 * Interface for how a RequestArbiter handles the responses when a request is 
 * sent.
 * 
 * @author Christopher McFall
 *
 */
public interface ResponseHandler {

    /**
     * Does the action required by the implementing class with the response.
     * 
     * @param response  {@code Response} to be handled.
     */
    public void operate(Response response);
    
}
