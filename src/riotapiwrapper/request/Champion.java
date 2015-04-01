package riotapiwrapper.request;

import riotapiwrapper.LolAPI;

/**
 * Implements the champion-v1.2 end point for the League of Legends public API.
 * <p>
 * This class consists of static methods only, which build the API request to
 * be sent when {@code this.send()} is called. This enables you to have multiple
 * requests built without sending the calls to the API, letting you throttle
 * the requests or save them for later.
 * 
 * @author  Christopher McFall
 * @see     riotapiwrapper.util.RequestArbiter
 * @see     Request#send()
 */
public class Champion extends Request {

    private static final String base = "/v1.2/champion";
    
    /**
     * Creates an API request for every champion's playability status.
     * 
     * @return  An API request for every champion's playability status.
     * @throws  IllegalStateException if an API key has not been set.
     */
    public static Champion all() {
        Champion all = new Champion();
        all.build(false);
        return all;
    }
    
    /**
     * Creates an API request for all free to play champions.
     * 
     * @return  An API request for all free to play champions.
     * @throws  IllegalStateException if an API key has not been set.
     */
    public static Champion freeToPlay() {
        Champion free = new Champion();
        free.build(true);
        return free;
    }
    
    /**
     * Creates an API request for a specific champion's playability status.
     * <p>
     * If there isn't a champion with the specific ID, the API server will
     * respond with a 404 error.
     * 
     * @param id    Specified champion's ID
     * @return      An API request for a specific champion's playability status.
     * @throws      IllegalStateException if an API key has not been set.
     */
    public static Champion byId(int id) {
        Champion champ = new Champion();
        champ.build(id);
        return champ;
    }
    
    private Champion() {
        rateLimited = true;
    }
    
    private void build(boolean freeToPlay) {
        begin();
        url.append(LolAPI.getCurrentRegion().ABREV)
                .append(base)
                .append('?');
        if (freeToPlay) url.append("freeToPlay=true&");
        end();
    }
    
    private void build(int champId) {
        begin();
        url.append(LolAPI.getCurrentRegion().ABREV)
                .append(base)
                .append('/')
                .append(champId)
                .append('?');
        end();
    }

}
