package riotapiwrapper.request;

import riotapiwrapper.LolAPI;

/**
 * Implements the current-game-v1.0 end point for the League of Legends public
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
public class CurrentGame extends Request {

    private static final String base = ".api.pvp.net/observer-mode/"
            + "rest/consumer/getSpectatorGameInfo/";
    
    /**
     * Creates an API request for the current game of the summoner specified
     * with summonerIdwhen sent.
     * <p>
     * When sent, if that summoner is not in a game, the API server will respond
     * with a 404 error.
     * 
     * @param summonerId    ID of the summoner
     * @return              API request for current game info of the summoner,
     *                      including spectator data.
     * @throws  IllegalStateException if an API key has not been set.
     */
    public static CurrentGame get(int summonerId) {
        CurrentGame game = new CurrentGame();
        game.build(summonerId);
        return game;
    }
    
    private void build(int summonerId) {
        url.append("https://")
                .append(LolAPI.getCurrentRegion().ABREV)
                .append(base)
                .append(LolAPI.getCurrentRegion().PLATFORM_ID)
                .append('/')
                .append(summonerId)
                .append('?');
        end();
    }
    
    private CurrentGame() {
        rateLimited = true;
    }

}
