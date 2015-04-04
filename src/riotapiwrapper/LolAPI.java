package riotapiwrapper;

import riotapiwrapper.request.Request;
import riotapiwrapper.util.DefaultThrottle;
import riotapiwrapper.util.RequestArbiter;
import riotapiwrapper.util.ResponseHandler;

/**
 * Top level class for the API library. Handles the API key used to make
 * requests and for which region. Requests made through {@code makeRequest()}
 * use the {@code RequestArbiter} and {@code ResponseHandler} designated for
 * handling the requests. If a {@code RequestArbiter} is not given, the 
 * {@code DefaultThrottle} will be used. If a ResponseHandler is not designated
 * {@code makeRequest()} will throw a {@code IllegalStateException()} Requests
 * made without {@code makeRequest()} risk going over your API key's rate limit
 * as the requests will not be tracked.
 * 
 * @author  Christopher McFall
 * @see     RequestArbiter
 * @see     ResponseHandler
 */
public class LolAPI {
    
    private static Regions region = Regions.NA;
    private static API api;
    
    /*
     * Static class
     */
    private LolAPI() { }
    
    /**
     * Sends a request through the API key's {@code RequestArbiter} and
     * {@code ResponseHandler}.
     * 
     * @param request   The {@code Request} to be sent.
     */
    public static void makeRequest(Request request) {
        api.arbitrate(request);
    }
    
    /**
     * Sets the API key without designating a {@code RequestArbiter} and
     * {@code ResponseHandler}. Requests submitted this way will not be tracked
     * and risk violating your API key's rate limits. Only meant to be used for
     * testing purposes or if the requests and responses will be handled
     * independent of this library.
     * 
     * @param   key     The LoL API key to be used to make requests.
     * @throws  IllegalArgumentException if the key given is not correctly
     *                  formatted.
     */
    public static void set(String key) {
        api = new API(key);
    }
    
    /**
     * Sets the API key using the {@code DefaultThrottle} as the
     * {@code RequestArbiter} with developer rate limits and the designated 
     * {@code ResponseHandler} to handle request received from the API server.
     * 
     * @param   key         The LoL API key to be used to make requests.
     * @param   handler     The {@code ResponseHandler} used to handle responses
     *                      received from the API server.
     * @throws  IllegalArgumentException if the key given is not correctly
     *                      formatted.
     * @see     DefaultThrottle
     * @see     ResponseHandler
     */
    public static void set(String key, ResponseHandler handler) {
        api = new API(key, handler);
    }
    
    /**
     * Sets the API key using the {@code DefaultThrottle} as the
     * {@code RequestArbiter} with the rate limits supplied, and the designated
     * {@code ResponseHandler} to handle requests received from the API server.
     * If either N2 or T2 are 0, a second rate limit will not be used.
     * 
     * @param key       The LoL API key to be used to make requests.
     * @param handler   The {@code ResponseHandler} used to handle responses.
     * @param N1        Number of requests per time for the first rate limit.
     * @param T1        Time limit for the first rate limit.
     * @param N2        Number of requests per time for the second rate limit.
     *                  If 0, a second rate limit will not be used.
     * @param T2        Time limit for the second rate limit. If 0, a second 
     *                  rate limit will not be used.
     * @throws          IllegalArgumentException if N1, T1, N2 or T2 are < 0, or
     *                  if the key given is not correctly formatted.
     * @see ResponseHandler
     */
    public static void set(String key, ResponseHandler handler, int N1,
            int T1, int N2, int T2) {
        api = new API(key, handler, N1, T1, N2, T2);
    }
    
    /**
     * Sets the API key using the {@code RequestArbiter} given.
     * 
     * @param key       The LoL API key to be used to make requests.
     * @param arbiter   The {@code RequestArbiter} used to arbitrate requests.
     * @throws  IllegalArgumentException if the key given is not correctly
     *                  formatted.
     * @see RequestArbiter
     */
    public static void set(String key, RequestArbiter arbiter) {
        api = new API(key, arbiter);
    }
    
    /**
     * Returns the API key currently being used.
     * 
     * @return  The API key currently being used.
     */
    public static API getApi() {
        return api;
    }
    
    /**
     * Sets the current {@code Region} to newRegion, which will be used for 
     * future requests.
     * 
     * @param newRegion    The {@code Region} to be used for future requests.
     */
    public static void setRegion(Regions newRegion) {
        region = newRegion;
    }
    
    /**
     * Returns the current region being used to make requests.
     * 
     * @return  The current region being used to make requests.
     */
    public static Regions getCurrentRegion() {
        return region;
    }
    
}
