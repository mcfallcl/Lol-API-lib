package riotapiwrapper.request;

import riotapiwrapper.LolAPI;


public class Match extends Request {

    private static final String base = "/v2.2/match/";
    
    public static Match match(int id, boolean includeTimeline) {
        Match match = new Match();
        match.build(id, includeTimeline);
        return match;
    }
    
    private void build(int id, boolean includeTimeline) {
        begin();
        url.append(LolAPI.getCurrentRegion().ABREV)
                .append(base)
                .append(id)
                .append('?');
        if (includeTimeline) {
            url.append("includeTimeline=true&");
        }
        end();
    }
    
    private Match() {
        rateLimited = true;
    }

}
