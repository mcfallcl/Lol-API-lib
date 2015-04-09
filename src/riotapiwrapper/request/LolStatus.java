package riotapiwrapper.request;

import riotapiwrapper.LolAPI;
import riotapiwrapper.Regions;

/**
 * Implements the lol-status-v1.0 end point for the League of Legends public
 * API.
 * <p>
 * This class consists of static methods only, which build the API request to
 * be sent when {@code this.send()} is called. This enables you to have multiple
 * requests be built without sending the calls to the API, letting you throttle
 * the requests or save them for later.
 * 
 * @author  Christopher McFall
 * @see     riotapiwrapper.util.RequestArbiter
 * @see     Request#send()
 */
public class LolStatus extends Request {
    
    private static final String base = "http://status.leagueoflegends.com/"
            + "shards";
    
    /**
     * Creates a request for the server status of all servers.
     * 
     * @return  A request for the server status of all servers.
     */
    public static LolStatus allServers() {
        return new LolStatus();
    }
    
    /**
     * Creates a request for the server status of the current region.
     * 
     * @return  A request for the server status of the current region.
     */
    public static LolStatus currentRegion() {
        return new LolStatus(LolAPI.getCurrentRegion());
    }
    
    /**
     * Creates a request for the server status of a specified region. Not all
     * regions can be requested. If the request region cannot be requested, a 
     * {@code HTTPstatus.FORBIDDEN} response will be retrieved.
     * 
     * @param region    The specified region's server status to request
     * @return          A request for the server status of the specified region.
     */
    public static LolStatus Region(Regions region) {
        return new LolStatus(region);
    }
    
    public RequestType type() {
        return RequestType.LOL_STATUS;
    }
    
    public boolean hasSubtype() {
        return false;
    }

    private LolStatus() {
        rateLimited = false;
        url.append(base);
    }
    
    private LolStatus(Regions r) {
        rateLimited = false;
        url.append(base)
                .append("/")
                .append(r.ABREV);
    }

}
