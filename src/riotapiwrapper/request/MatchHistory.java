package riotapiwrapper.request;

import riotapiwrapper.LolAPI;

/**
 * Implements the matchhistory-v2.2 end point for the League of Legends public
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
public class MatchHistory extends Request {

    private static final String base = "/v2.2/matchhistory/";
    
    /**
     * Creates an API request for the 15 most recent matches for a specified
     * summoner. If ranked queues are specified, matches not from those queues
     * will not be received.
     * 
     * @param summonerId    ID of the summoner whose match history is being 
     *                      requested.
     * @param rankedQueues  The ranked queues which the recent games should be
     *                      drawn from.
     * @return              API request for the 15 most recent matches in 
     *                      the specified summoner's match history.
     * @throws  IllegalStateException if an API key has not been set.
     */
    public static MatchHistory mostRecent(int summonerId,
            String... rankedQueues) {
        MatchHistory history = new MatchHistory();
        history.build(summonerId, 0, 0, rankedQueues);
        return history;
    }
    
    /**
     * Creates an API request for a specific subset of the specified summoner's 
     * match history. If ranked queues are specified, matches not from those 
     * queues will not be received.
     * <p>
     * The subset is chosen with the beginIndex and endIndex parameters, with
     * a maximum amount of 15. If the difference between beginIndex and 
     * endIndex is greater than 15, the 15 starting from beginIndex will be
     * requested from the API server.
     * 
     * @param summonerId    The summoner's whose match history is being 
     *                      requested.
     * @param beginIndex    The index of the most recent game requested.
     * @param endIndex      The index of the oldest game requested.
     * @param rankedQueues  The ranked queues which the recent games should be
     *                      drawn from.
     * @return              An API request for a subset of a summoner's match 
     *                      history.
     * @throws  IllegalStateException if an API key has not been set.
     * @throws  IllegalArgumentException if either beginIndex or endIndex are
     *          below 0.
     */
    public static MatchHistory mostRecentIndexed(int summonerId,
            int beginIndex, int endIndex, String... rankedQueues) {
        MatchHistory history = new MatchHistory();
        history.build(summonerId, beginIndex, endIndex, rankedQueues);
        return history;
    }
    
    private void build(int summonerId, int beginIndex, int endIndex,
            String... rankedQueues) {
        if (beginIndex < 0 || endIndex < 0) {
            throw new IllegalArgumentException("beginIndex and endIndex cannot "
                    + " be below 0.");
        }
        begin();
        url.append(LolAPI.getCurrentRegion().ABREV)
                .append(base)
                .append(summonerId)
                .append('?');
        if (beginIndex != 0 && endIndex != 0) {
            url.append("beginIndex=")
                    .append(beginIndex)
                    .append('&')
                    .append("endIndex=")
                    .append(endIndex)
                    .append('&');
        }
        end();
    }
    
    private MatchHistory() {
        rateLimited = true;
    }

}
