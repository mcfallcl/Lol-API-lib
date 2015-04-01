package riotapiwrapper;

import riotapiwrapper.request.Request;
import riotapiwrapper.util.DefaultThrottle;
import riotapiwrapper.util.RequestArbiter;
import riotapiwrapper.util.ResponseHandler;


/**
 * 
 * @author takronix
 *
 */
public class API {
    
    /**
     * 
     */
    public final String KEY;
    private RequestArbiter arbiter;
    
    API(String key) {
        checkKey(key);
        this.KEY = key;
    }
    
    API(String key, ResponseHandler handler) {
        checkKey(key);
        this.KEY = key;
        arbiter = new DefaultThrottle(handler);
    }
    
    API(String key, RequestArbiter arbiter) {
        checkKey(key);
        this.KEY = key;
        this.arbiter = arbiter;
    }
    
    API(String key, ResponseHandler handler, int N1, int T1, int N2, int T2) {
        checkKey(key);
        this.KEY = key;
        arbiter = new DefaultThrottle(handler, N1, T1, N2, T2);
    }
    
    void arbitrate(Request request) {
        arbiter.arbitrate(request);
    }
    
    void addLimit(int N, int T) {
        if (arbiter == null) {
            throw new IllegalStateException("there no arbiter for the limit");
        }
        arbiter.addLimit(N, T);
    }
    
    private void checkKey(String key) {
        if (key.length() != 36) {
            throw new IllegalArgumentException("Key is not formatted correctly.");
        }
        if (key.charAt(8) != '-' || key.charAt(13) != '-' || 
                key.charAt(18) != '-' || key.charAt(23) != '-') {
            throw new IllegalArgumentException("Key is not formatted correctly.");
        }
    }
    
}
