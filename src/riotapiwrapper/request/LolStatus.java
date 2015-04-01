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
class LolStatus extends Request {
    
    private static final String base = "http://status.leagueoflegends.com/"
            + "shards";
    
    /**
     * 
     * @return
     */
    public static LolStatus allServers() {
        return new LolStatus();
    }
    
    /**
     * 
     * @return
     */
    public static LolStatus currentRegion() {
        return new LolStatus(LolAPI.getCurrentRegion());
    }
    
    /**
     * 
     * @param region
     * @return
     */
    public static LolStatus Region(Regions region) {
        return new LolStatus(region);
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
