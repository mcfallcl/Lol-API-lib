package riotapiwrapper.request;

import riotapiwrapper.LolAPI;
import riotapiwrapper.QueueTypes;

/**
 * Implements the league-v2.5 end point for the League of Legends public API.
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
public class League extends Request {

    private final static String base = "/v2.5/league/";
    
    /**
     * Creates an API request for the challenger league for a specific ranked
     * queue.
     * 
     * @param   queue                       The ranked queue for the challenger
     *                                      league requested. Valid Strings: 
     *                                      RANKED_SOLO_5x5, RANKED_TEAM_5x5,
     *                                      and RANKED_TEAM_3x3.
     * @throws  IllegalArgumentException    If the queue does not have a 
     *                                      challenger tier.
     * @throws  IllegalStateException if an API key has not been set.
     * @return                              API Request for the requested
     *                                      challenger league.
     */
    public static League challenger(QueueTypes queue) {
        League league = new League();
        league.buildChallenger(queue);
        return league;
    }
    
    /**
     * Creates an API request for the leagues for a list of summoners, specified
     * by their SummonerIds. No more than 10 summoners can be requested at a
     * time.
     *   
     * @param id                        List of summoner IDs for this request.
     *                                  Max amount is 10.
     * @throws IllegalArgumentException If the number of summoner IDs is
     *                                  greater than 10.
     * @throws  IllegalStateException if an API key has not been set.
     * @return                          API Request for the requested summoners'
     *                                  leagues.
     */
    public static League bySummoner(int... id) {
        League league = new League();
        league.buildSummoner(true, id);
        return league;
    }
    
    /**
     * Creates an API request for the leagues and their entries for a list of 
     * summoners, specified by their SummonerIds. No more than 10 summoners can
     * be requested at a time.
     *   
     * @param id                        List of summoner IDs for this request.
     *                                  Max amount is 10.
     * @throws IllegalArgumentException If the number of summoner IDs is
     *                                  greater than 10.
     * @throws  IllegalStateException if an API key has not been set.
     * @return                          API Request for the requested summoners'
     *                                  leagues and their entries.
     */
    public static League entriesBySummoner(int... id) {
        League league = new League();
        league.buildSummoner(false, id);
        return league;
    }
    
    /**
     * Creates an API request for the leaguesfor a list of summoners, specified
     * by their SummonerIds. No more than 10 summoners can be requested at a
     * time.
     *   
     * @param id                        List of team IDs for this request. Max
     *                                  amount is 10.
     * @throws IllegalArgumentException If the number of team IDs is greater
     *                                  than 10.
     * @throws  IllegalStateException if an API key has not been set.
     * @return                          API Request for the requested teams'
     *                                  leagues.
     */
    public static League byTeam(String... id) {
        League league = new League();
        league.buildTeam(false, id);
        return league;
    }
    
    /**
     * Creates an API request for the leagues and their entries for a list of 
     * summoners, specified by their SummonerIds. No more than 10 summoners can
     * be requested at a time.
     *   
     * @param id                        List of team IDs for this request. Max
     *                                  amount is 10.
     * @throws IllegalArgumentException If the number of team IDs is greater
     *                                  than 10.
     * @throws  IllegalStateException if an API key has not been set.
     * @return                          API Request for the requested teams'
     *                                  leagues and their entries.
     */
    public static League entriesByTeam(String... id) {
        League league = new League();
        league.buildTeam(true, id);
        return league;
    }
    
    private League() {
        rateLimited = true;
    }
    
    private void buildSummoner(boolean entry, int... id) {
        if (id.length > 10) {
            throw new IllegalArgumentException("maximum of 10 entries allowed");
        }
        begin();
        url.append(LolAPI.getCurrentRegion().ABREV)
                .append(base)
                .append("by-summoner/")
                .append(id[0]);
        if (id.length > 1) {
            for (int i = 1; i < id.length; i++) {
                url.append(",")
                        .append(id[i]);
            }
        }
        if (entry) url.append("/entry?");
        else url.append('?');
        end();
    }
    
    private void buildTeam(boolean entry, String... id) {
        if (id.length > 10) {
            throw new IllegalArgumentException("maximum of 10 entries allowed");
        }
        begin();
        url.append(LolAPI.getCurrentRegion().ABREV)
                .append(base)
                .append("by-team/")
                .append(id[0]);
        if (id.length > 1) {
            for (int i = 1; i < id.length; i++) {
                url.append(",")
                        .append(id[i]);
            }
        }
        if (entry) url.append("/entry?");
        else url.append('?');
        end();
    }
    
    private void buildChallenger(QueueTypes queue) {
        if (!queue.hasChallengerTier()) {
            throw new IllegalArgumentException("Invalid queue type");
        }
        begin();
        url.append(LolAPI.getCurrentRegion().ABREV)
                .append(base)
                .append("challenger?type=")
                .append(queue)
                .append('&');
        end();
    }

}
