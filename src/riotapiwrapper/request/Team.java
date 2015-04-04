package riotapiwrapper.request;

import riotapiwrapper.LolAPI;

public class Team extends Request {

    private static final String base = "/v2.4/team/";
    
    public static Team bySummonerId(int... ids) {
        Team team = new Team();
        team.build(ids);
        return team;
    }
    
    public static Team byTeamId(String... ids) {
        Team team = new Team();
        team.build(ids);
        return team;
    }
    
    private void build(String... ids) {
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
        end();
    }
    
    private Team() {
        rateLimited = true;
    }

}
