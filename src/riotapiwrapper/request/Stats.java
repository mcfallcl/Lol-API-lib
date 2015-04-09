package riotapiwrapper.request;

import riotapiwrapper.LolAPI;

/**
 * Implements the stats-v1.3 end point for the League of Legends public API.
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
public class Stats extends Request {

    private static final String base = "/v1.3/stats/by-summoner/";
    
    /**
     * Creates a request for a specified summoner's ranked stats for the 
     * current season.
     * 
     * @param summonerId    The specified summoner whose ranked stats are to be
     *                      requested.
     * @return              A request for a specified summoners ranked stats for
     *                      the current season.
     */
    public static Stats ranked(int summonerId) {
        Stats stats = new Stats();
        stats.build(summonerId, true, 5);
        return stats;
    }
    
    /**
     * Creates a request for a specified summoner's ranked stats for the 
     * specified season.
     * 
     * @param summonerId    The specified summoner whose ranked stats are to be
     *                      requested.
     * @param season        The season to draw the stats from.
     * @return              A request for a specified summoners ranked stats for
     *                      the specified season.
     */
    public static Stats ranked(int summonerId, int season) {
        Stats stats = new Stats();
        stats.build(summonerId, true, season);
        return stats;
    }
    
    /**
     * Creates a request for a specified summoner's overall stats for the 
     * current season.
     * 
     * @param summonerId    The specified summoner whose overall stats are to be
     *                      requested.
     * @return              A request for a specified summoners overall stats 
     *                      for the current season.
     */
    public static Stats summary(int summonerId) {
        Stats stats = new Stats();
        stats.build(summonerId, false, 5);
        return stats;
    }
    
    /**
     * Creates a request for a specified summoner's overall stats for the 
     * specified season.
     * 
     * @param summonerId    The specified summoner whose overall stats are to be
     *                      requested.
     * @param season        The season to draw the stats from.
     * @return              A request for a specified summoners overall stats
     *                      for the specified season.
     */
    public static Stats summary(int summonerId, int season) {
        Stats stats = new Stats();
        stats.build(summonerId, false, season);
        return stats;
    }
    
    private void build(int summonerId, boolean ranked, int season) {
        begin();
        url.append(LolAPI.getCurrentRegion().ABREV)
                .append(base)
                .append(summonerId);
        if (ranked) url.append("/ranked");
        else url.append("/summary");
        url.append('?');
        switch (season) {
            case 3: url.append("season=SEASON3&");
                break;
            case 4: url.append("season=SEASON2014&");
                break;
            default:
                break;
        }
        end();
    }
    
    private Stats() {
        rateLimited = true;
    }

}
