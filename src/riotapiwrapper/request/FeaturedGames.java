package riotapiwrapper.request;

import riotapiwrapper.LolAPI;

/**
 * Implements the featured-games-v1.0 end point for the League of Legends public
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
public class FeaturedGames extends Request {

    private static final String base = ".api.pvp.net/observer-mode/rest/"
            + "featured?";
    
    /**
     * Creates an API request for the current featured games for the current
     * region.
     * 
     * @return  request URL for current featured games for the current region.
     * @throws  IllegalStateException if an API key has not been set.
     */
    public static FeaturedGames get() {
        FeaturedGames games = new FeaturedGames();
        games.build();
        return games;
    }
    
    private void build() {
        url.append("https://")
                .append(LolAPI.getCurrentRegion().ABREV)
                .append(base);
        end();
    }
    
    private FeaturedGames() {
        rateLimited = true;
    }

}
