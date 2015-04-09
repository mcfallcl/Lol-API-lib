package riotapiwrapper.request;

import riotapiwrapper.LolAPI;
import riotapiwrapper.QueueTypes;

/**
 * Implements the matchhistory-v2.2 end point for the League of Legends public
 * API.
 * <p>
 * This class consists of static methods only, which build the API request to
 * be sent when {@code this.send()} is called. This enables you to have multiple
 * requests be built without sending the calls to the API, letting you throttle
 * the requests or save them for later.
 * <p>
 * This library does not currently support requests for a match history with 
 * specific champions.
 * 
 * @author  Christopher McFall
 * @see     riotapiwrapper.util.RequestArbiter
 * @see     Request#send()
 */
public class MatchHistory extends Request {

    private static final String base = "/v2.2/matchhistory/";
    private static QueueTypes[] rankedQueues;
    
    /**
     * Creates an API request for the 15 most recent matches for a specified
     * summoner. If ranked queues are specified, matches not from those queues
     * will not be received.
     * 
     * @param summonerId    ID of the summoner whose match history is being 
     *                      requested.
     * @param championIds   A list of champion ids to return the summoner's 
     *                      match history from.
     * @return              API request for the 15 most recent matches in 
     *                      the specified summoner's match history.
     * @throws  IllegalStateException if an API key has not been set.
     */
    public static MatchHistory mostRecent(int summonerId,
            int... championIds) {
        MatchHistory history = new MatchHistory();
        history.build(summonerId, 0, 0, championIds);
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
     * @param championIds   A list of champion ids to return the summoner's 
     *                      match history from.
     * @return              An API request for a subset of a summoner's match 
     *                      history.
     * @throws  IllegalStateException if an API key has not been set.
     * @throws  IllegalArgumentException if either beginIndex or endIndex are
     *          below 0.
     */
    public static MatchHistory mostRecentIndexed(int summonerId,
            int beginIndex, int endIndex, int... championIds) {
        MatchHistory history = new MatchHistory();
        history.build(summonerId, beginIndex, endIndex, championIds);
        return history;
    }
    
    /**
     * Sets the ranked queues for future requests.
     * <p>
     * Allowed Values:
     * QueueTypes.RANKED_SOLO_5x5
     * QueueTypes.RANKED_TEAM_5x5
     * QueueTypes.RANKED_TEAM_3x3
     * 
     * @param queues    The list of ranked queues to request match history from.
     */
    public static void setRankedQueues(QueueTypes... queues) {
        if (queues.length > 3) {
            throw new IllegalArgumentException("There are only 3 availible "
                    + "ranked queues");
        }
        for (QueueTypes queue : queues) {
            if (!queue.isRanked()) {
                throw new IllegalArgumentException("You may only select ranked "
                        + "queues.");
            }
        }
        rankedQueues = queues;
    }
    
    public RequestType type() {
        return RequestType.MATCHHISTORY;
    }
    
    public boolean hasSubtype() {
        return false;
    }
    
    private void evaluateRankedQueues() {
        if (rankedQueues == null) return;
        url.append("rankedQueues=");
        url.append(rankedQueues[0]);
        for (int i = 1; i < rankedQueues.length; i++) {
            url.append(',');
            url.append(rankedQueues[i]);
        }
        url.append('&');
    }
    
    private void build(int summonerId, int beginIndex, int endIndex,
            int... championIds) {
        if (beginIndex < 0 || endIndex < 0) {
            throw new IllegalArgumentException("beginIndex and endIndex cannot "
                    + " be below 0.");
        }
        begin();
        url.append(LolAPI.getCurrentRegion().ABREV)
                .append(base)
                .append(summonerId)
                .append('?');
        if (championIds != null) {
            url.append("championIds=")
                    .append(championIds[0]);
            if (championIds.length > 1) {
                for (int i = 1; i < championIds.length; i++) {
                    url.append(',')
                            .append(championIds[i]);
                }
            }
            url.append('&');
        }
        evaluateRankedQueues();
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
