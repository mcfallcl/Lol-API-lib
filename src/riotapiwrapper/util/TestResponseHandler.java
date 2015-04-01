package riotapiwrapper.util;

import riotapiwrapper.request.Response;

/**
 * Responses through here are printed to the console to ensure the rest of a
 * RequestArbiter is working correctly.
 * 
 * @author Christopher McFall
 *
 */
public class TestResponseHandler implements ResponseHandler {

    
    @Override
    public void operate(Response response) {
        System.out.println(response);
    }

}
