package riotapiwrapper.request;

import riotapiwrapper.LolAPI;

/**
 * Implements the match-v2.2 end point for the League of Legends public API.
 * <p>
 * This class consists of static methods only, which build the API request to 
 * be sent when {@code this.send()} is called. This enables you to have multiple
 * requests be build without sending the calls to the API, letting you throttle
 * the requests or save them for later.
 * 
 * @author Christopher McFall
 * @see riotapiwrapper.util.RequestArbiter
 * @see Request#send()
 */
public class Match extends Request {

    private static final String base = "/v2.2/match/";
    
    /**
     * Creates a request for a specified match's data. The match timeline may be 
     * included by request.
     * 
     * @param id                The id of the match requested
     * @param includeTimeline   A flag indicating if the match's timeline is
     *                          requested.
     * @return                  A request for a specified match's data.
     */
    public static Match match(int id, boolean includeTimeline) {
        Match match = new Match();
        match.build(id, includeTimeline);
        return match;
    }
    
    public RequestType type() {
        return RequestType.MATCH;
    }
    
    private void build(int id, boolean includeTimeline) {
        begin();
        url.append(LolAPI.getCurrentRegion().ABREV)
                .append(base)
                .append(id)
                .append('?');
        if (includeTimeline) {
            url.append("includeTimeline=true&");
        }
        end();
    }
    
    private Match() {
        rateLimited = true;
    }

}
