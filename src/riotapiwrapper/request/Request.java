package riotapiwrapper.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import riotapiwrapper.LolAPI;


/**
 * Abstract class for creating, submitting and handling API requests.
 * 
 * @author  Christopher McFall
 * @see     Response
 */
public abstract class Request {
    
    /**
     * The {@code StringBuilder} object used to build the request's URL to the
     * API
     * 
     * @see StringBuilder
     */
    protected StringBuilder url = new StringBuilder();
    
    /**
     * A flag indicating weather a request will count against your API key's
     * rate limits.
     */
    protected boolean rateLimited;
    
    /**
     * Builds the beginning of most API requests' URLs, using the current
     * region selected with {@code LolAPI#setRegion()}. If one has not been
     * selected, {@code Region#NA} will be used.
     * 
     * @see riotapiwrapper.Regions
     */
    protected void begin() {
        url.append("https://")
                .append(LolAPI.getCurrentRegion().ABREV)
                .append(".api.pvp.net/api/lol/");
    }
    
    /**
     * Completes the URL request's URL for most API requests, adding the API
     * key given with {@code LolAPI.setKey()}. If a key has not yet been given
     * any requests that require a key will receive a 
     * {@code HTTPstatus@UNAUTHORIZED} response.
     * 
     * @see HTTPstatus#UNAUTHORIZED
     * @throws  IllegalStateException if an API key has not been set.
     */
    protected void end() {
        if (LolAPI.getApi() == null) {
            throw new IllegalStateException("An API has not been provided to "
                    + "make this call.");
        }
        url.append("api_key=")
                .append(LolAPI.getApi().KEY);
    }
    
    /**
     * String value of the Request URL.
     * 
     * @return  The request URL to the API server.
     */
    @Override
    public String toString() {
        return url.toString();
    }
    
    /**
     * This method is meant to be used in a {@code RequestArbiter}.
     * <p>
     * Sends the request to the API server and returns the response. manually 
     * submitting a request with this method bypasses your API key's designated 
     * {@code RequestArbiter} and can risk violating your rate limit.
     * 
     * @return  The {@code Response} from the API server.
     * @see     Response
     * @see     riotapiwrapper.util.RequestArbiter
     */
    public Response send() {
        return read(toString(), type());
    }
    
    /**
     * Returns a flag indicating if the request will count against your API
     * key's rate limits.
     * 
     * @return  A flag indicating if the request will count against your API
     *          key's rate limits.
     */
    public boolean isRateLimited() {
        return rateLimited;
    }
    
    /**
     * Returns the request's {@code RequestTypes}
     * 
     * @return  The request's {@code RequestTypes}
     * @see     RequestType
     */
    public abstract RequestType type();
    
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = rd.read()) != -1) {
            sb.append((char) c);
        }
        return sb.toString();
    }
    
    private static Response read(String url, RequestType type) {
        try {
            InputStream is = new URL(url).openStream();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is,
                        Charset.forName("UTF-8")));
                return new Response(url, readAll(rd), HTTPstatus.SUCCESSFUL, 
                        type);
            } catch (IOException e) {
                System.err.println("IOException: " + e.getMessage());
                return new Response(url, "NO GOOD " + e.getMessage(), type);
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    
                }
            }
        } catch (IOException e) {
            return new Response(url, "NO GOOD " + e.getMessage(), type);
        }
    }
    
}
