package riotapiwrapper.request;

import riotapiwrapper.LolAPI;


public class Stats extends Request {

    private static final String base = "/v1.3/stats/by-summoner/";
    
    public static Stats ranked(int summonerId) {
        Stats stats = new Stats();
        stats.build(summonerId, true, 5);
        return stats;
    }
    
    public static Stats ranked(int summonerId, int season) {
        Stats stats = new Stats();
        stats.build(summonerId, true, season);
        return stats;
    }
    
    public static Stats summary(int summonerId) {
        Stats stats = new Stats();
        stats.build(summonerId, false, 5);
        return stats;
    }
    
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
