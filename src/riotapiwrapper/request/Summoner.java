package riotapiwrapper.request;

import riotapiwrapper.LolAPI;

/**
 * Implements the summoner-v1.4 end point for the League of Legends public API.
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
public class Summoner extends Request {

    private static final String base = "/v1.4/summoner/";
    
    /**
     * Creates a request for a list of summoners' ids.
     * 
     * @param   names The names of the summoners' whose ids are requested.
     * @return  A request for a list of summoners' ids.
     */
    public static Summoner byName(String... names) {
        Summoner summoner = new Summoner();
        summoner.build(names);
        return summoner;
    }
    
    /**
     * Creates a request for a list of summoners' basic data.
     * 
     * @param ids   Ids of the summoners' whose data is requested
     * @return      A request for a list of summoners' basic data.
     */
    public static Summoner byIds(int... ids) {
        Summoner summoners = new Summoner();
        summoners.build(ids);
        return summoners;
    }
    
    /**
     * Creates a request for a list of summoners' current masteries.
     * 
     * @param summonerIds   Ids of the summoners' whose masteries are requested
     * @return              A request for a list of summoners' current masteries.
     */
    public static Summoner masteries(int... summonerIds) {
        Summoner summoners = new Summoner();
        summoners.build("masteries", summonerIds);
        return summoners;
    }
    
    /**
     * Creates a request for a list of summoners' current runes.
     * 
     * @param summonerIds   Ids of the summoners' whose runes are requested
     * @return              A request for a list of summoners' current runes.
     */
    public static Summoner runes(int... summonerIds) {
        Summoner summoners = new Summoner();
        summoners.build("runes", summonerIds);
        return summoners;
    }
    
    /**
     * Creates a request for a list of summoners' current names.
     * 
     * @param summonerIds   Ids of the summoners' whose names are requested
     * @return              A request for a list of summoners' current names.
     */
    public static Summoner name(int... summonerIds) {
        Summoner summoners = new Summoner();
        summoners.build("name", summonerIds);
        return summoners;
    }
    
    private void build(String spec, int... summonerIds) {
        if (summonerIds.length > 40) {
            throw new IllegalArgumentException("the max ammount of ids is 40");
        }
        begin();
        url.append(LolAPI.getCurrentRegion().ABREV)
                .append(base)
                .append(summonerIds[0]);
        if (summonerIds.length > 1) {
            for (int i = 1; i < summonerIds.length; i++) {
                url.append(',')
                        .append(summonerIds[i]);
            }
        }
        url.append('/')
                .append(spec)
                .append('?');
        end();
    }
    
    private void build(int... summonerIds) {
        if (summonerIds.length > 40) {
            throw new IllegalArgumentException("the max ammount of ids is 40");
        }
        begin();
        url.append(LolAPI.getCurrentRegion().ABREV)
                .append(base)
                .append(summonerIds[0]);
        if (summonerIds.length > 1) {
            for (int i = 1; i < summonerIds.length; i++) {
                url.append(',')
                        .append(summonerIds[i]);
            }
        }
        url.append('?');
        end();
    }
    
    private void build(String... names) {
        if (names.length > 40) {
            throw new IllegalArgumentException("the max ammount of names is "
                    + "40");
        }
        begin();
        url.append(LolAPI.getCurrentRegion().ABREV)
                .append(base)
                .append("by-name/")
                .append(names[0]);
        if (names.length > 1) {
            for (int i = 1; i < names.length; i++) {
                url.append(',')
                        .append(names[i]);
            }
        }
        url.append('?');
        end();
    }
    
    private Summoner() {
        rateLimited = true;
    }

}
