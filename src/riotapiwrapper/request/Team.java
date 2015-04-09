package riotapiwrapper.request;

import riotapiwrapper.LolAPI;

/**
 * Implements the team-v2.4 end point for the League of Legends public API.
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
public class Team extends Request {

    private static final String base = "/v2.4/team/";
    
    /**
     * Creates a request for a list of teams for a list of specified summoners.
     * 
     * @param ids   list of specified summoners.
     * @return      A request for a list of teams for a list of specified 
     *              summoners.
     * @throws IllegalArgumentException if more than 10 summoners are requested.
     */
    public static Team bySummonerId(int... ids) {
        Team team = new Team();
        team.build(ids);
        System.out.println(team);
        return team;
    }
    
    /**
     * Creates a request for a list of teams by their ids.
     * 
     * @param ids   The ids of the teams requested
     * @return      A request for a list of teams.
     * @throws IllegalArgumentException if more than 10 teams are requested.
     */
    public static Team byTeamId(String... ids) {
        Team team = new Team();
        team.build(ids);
        System.out.println(team);
        return team;
    }
    
    public RequestType type() {
        return RequestType.TEAM;
    }
    
    private void build(String... ids) {
        if (ids.length > 10) throw new IllegalArgumentException("The max "
                + "length of ids is 10");
        begin();
        url.append(LolAPI.getCurrentRegion().ABREV)
                .append(base)
                .append(ids[0]);
        if (ids.length > 1) {
            for (int i = 1; i < ids.length; i++) {
                url.append(',')
                        .append(ids[i]);
            }
        }
        url.append('?');
        end();
    }
    
    private void build(int... ids) {
        if (ids.length > 10) throw new IllegalArgumentException("The max "
                + "length of ids is 10");
        begin();
        url.append(LolAPI.getCurrentRegion().ABREV)
                .append(base)
                .append("by-summoner/")
                .append(ids[0]);
        if (ids.length > 1) {
            for (int i = 1; i < ids.length; i++) {
                url.append(',')
                        .append(ids[i]);
            }
        }
        url.append('?');
        end();
    }
    
    private Team() {
        rateLimited = true;
    }

}
