package riotapiwrapper.request;

import riotapiwrapper.LolAPI;


public class Summoner extends Request {

    private static final String base = "/v1.4/summoner/";
    
    public static Summoner byName(String... names) {
        Summoner summoner = new Summoner();
        summoner.build(names);
        return summoner;
    }
    
    public static Summoner byIds(int... ids) {
        Summoner summoners = new Summoner();
        summoners.build(ids);
        return summoners;
    }
    
    public static Summoner masteries(int... summonerIds) {
        Summoner summoners = new Summoner();
        summoners.build("masteries", summonerIds);
        return summoners;
    }
    
    public static Summoner runes(int... summonerIds) {
        Summoner summoners = new Summoner();
        summoners.build("runes", summonerIds);
        return summoners;
    }
    
    public static Summoner name(int... summonerIds) {
        Summoner summoners = new Summoner();
        summoners.build("name", summonerIds);
        return summoners;
    }
    
    private void build(String spec, int... summonerIds) {
        if (summonerIds.length > 40) {
            throw new IllegalArgumentException("the max ammount of names is "
                    + "40");
        }
        begin();
        url.append(LolAPI.getCurrentRegion().ABREV)
                .append(base)
                .append('/')
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
            throw new IllegalArgumentException("the max ammount of names is "
                    + "40");
        }
        begin();
        url.append(LolAPI.getCurrentRegion().ABREV)
                .append(base)
                .append('/')
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
    
    //testing only
    public static void main(String[] args) {
        
    }

}
