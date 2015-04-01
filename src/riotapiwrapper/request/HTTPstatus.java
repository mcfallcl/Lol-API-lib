package riotapiwrapper.request;

/**
 * Enums for the possible responses from the API server.
 * 
 * @author Christopher McFall
 *
 */
public enum HTTPstatus {
    
    /**
     * The Request was successful.
     */
    SUCCESSFUL(200, "Request successful."),
    
    /**
     * This error indicates that there is a syntax error in the request and the 
     * request has been denied. The client should not continue to make similar 
     * requests without modifying the syntax of the requests being made.
     */
    BAD_REQUEST(400, "Invalid request. The URL may be formatted incorrectly."),
    
    /**
     * This error indicates that the API request being made did not contain the 
     * necessary authentication credentials and was denied. If authentication 
     * credentials were already included then the Unauthorized response 
     * indicates that authorization has been refused for those credentials. In 
     * the case of the API, authorization credentials refer to your API key.
     */
    UNAUTHORIZED(401, "An API key was not supplied with the request, the key "
            + "supplied was invalid, or the request was for an incorrect "
            + "or unsupported path."),
            
    /**
     * This error indicates that the server has not found a match for the API 
     * request being made. No indication is given whether the condition is 
     * temporary or permanent.
     */
    DATA_NOT_FOUND(404, "The data you requested could not be found."),
    
    /**
     * This error indicates that the application has exhausted its maximum 
     * number of allotted API calls allowed for a given duration.
     */
    RATE_LIMIT_EXCEEDED(429, "You API key's rate limit has been exceeded."),
    
    /**
     * This error indicates an unexpected condition or exception which 
     * prevented the server from fulfilling an API request.
     */
    INTERNAL_SERVER_ERROR(500, "There is an issue with the Riot API service."),
    
    /**
     * This error indicates the server is currently unavailable to handle 
     * request because of an unknown reason.
     */
    SERVICE_UNAVAILABLE(503, "The Riot API service is unavailible.");
    
    /**
     * The three digit error code.
     */
    public final int CODE;
    
    /**
     * The error in string format.
     */
    public final String ERROR;
    
    private HTTPstatus(int code, String error) {
        this.CODE = code;
        this.ERROR = code + " Error: " + error;
    }
    
    /**
     * Returns a flag indicating if the error was a client side error. useful 
     * for troubleshooting or logging errors which only occur on either client 
     * or server side.
     * 
     * @return  A flag indicating if the error was a client side error.
     */
    public boolean isClientError() {
        if (this.CODE >= 400 && this.CODE < 500) return true;
        return false;
    }
    
    @Override
    public String toString() {
        return this.ERROR;
    }
    
    static HTTPstatus getStatus(int code) {
        HTTPstatus status;
        switch(code) {
            case 200:
                status = SUCCESSFUL;
                break;
            case 400:
                status = BAD_REQUEST;
                break;
            case 401:
                status = UNAUTHORIZED;
                break;
            case 404:
                status = DATA_NOT_FOUND;
                break;
            case 429:
                status = RATE_LIMIT_EXCEEDED;
                break;
            case 500:
                status = INTERNAL_SERVER_ERROR;
                break;
            case 503:
                status = SERVICE_UNAVAILABLE;
                break;
            default:
                throw new IllegalArgumentException("unknown error code");
        }
        return status;
    }
    
}
