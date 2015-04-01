package riotapiwrapper.request;

import riotapiwrapper.LolAPI;

/**
 * Implements the game-v1.3 end point for the League of Legends public
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
public class Games extends Request {

    private final static String base = "/v1.3/game/by-summoner/";
    
    /**
     * Creates an API request for the specified summoner's last 10 games.
     * 
     * @param summonerId    Specified summoner's id.
     * @return              API request for the specified summoner's last 10
     *                      games.
     * @throws  IllegalStateException if an API key has not been set.
     */
    public static Games recent(int summonerId) {
        Games game = new Games();
        game.build(summonerId);
        return game;
    }
    
    private Games() {
        rateLimited = true;
    }
    
    private void build(int summonerId) {
        begin();
        url.append(LolAPI.getCurrentRegion().ABREV)
                .append(base)
                .append(summonerId)
                .append("/recent?");
        end();
    }

}
