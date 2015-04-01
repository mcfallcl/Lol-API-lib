package riotapiwrapper.request;

import riotapiwrapper.responseobjects.ResponseObject;


/**
 * Maintains the data received from the API server. Objects of this type
 * cannot be created except by from the Request.send() method.
 * 
 * @author Christopher McFall
 * @see Request#send()
 *
 */
public class Response {
    
    /**
     * The request URL sent to the API server for the response.
     */
    public final String url;
    
    /**
     * The JSON data or error message recieved from the API server.
     */
    public final String data;
    
    /**
     * The HTTP status given from the server.
     * 
     * @see HTTPstatus
     */
    public final HTTPstatus status;
    
    Response(String url, String data, HTTPstatus code) {
        this.url = url;
        this.data = data;
        this.status = code;
    }
    
    /*
     * Handling the http status code is pretty hacky right now. Current plan
     * is to change the error code in the enum to a string.
     */
    Response(String url, String data) {
        this.url = url;
        if (!data.substring(0, 7).equals("NO GOOD")) {
            this.data = data;
            this.status = HTTPstatus.SUCCESSFUL;
        } else {
            this.data = data.substring(8);
            String errCode = data.substring(44, 47);
            boolean isInt;
            try {
                Integer.parseInt(errCode);
                isInt = true;
            } catch (NumberFormatException e) {
                isInt = false;
            }
            if (isInt) {
                this.status = HTTPstatus.getStatus(Integer.parseInt(errCode));
            } else {
                this.status = HTTPstatus.DATA_NOT_FOUND;
            }
            
        }
    }
    
    public ResponseObject toObject() {
        return null;
    }
    
    @Override
    public String toString() {
        if (status == HTTPstatus.SUCCESSFUL) {
            return data;
        } else {
            return status.ERROR;
        }
    }
    
}
