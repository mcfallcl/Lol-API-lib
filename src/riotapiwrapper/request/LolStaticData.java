package riotapiwrapper.request;

import riotapiwrapper.Locales;
import riotapiwrapper.LolAPI;

/**
 * Implements the lol-static-data-v1.2 end point for the League of Legends 
 * public API.
 * <p>
 * This class consists of static methods only, which build the API request to
 * be sent when {@code this.send()} is called. This enables you to have multiple
 * requests built without sending the calls to the API, letting you throttle
 * the requests or save them for later.
 * <p>
 * All requests to this endpoint do not count against your API key's rate limit.
 * 
 * @author  Christopher McFall
 * @see     riotapiwrapper.util.RequestArbiter
 * @see     Request#send()
 */
public class LolStaticData extends Request {

    private final static String base1 = "static-data/";
    private final static String base2 = "/v1.2/";
    
    private static Locales locale;
    private static String version;
    
    private Subtype subtype;
    
    /**
     * Creates an API request for the full list of current champions' data 
     * based on the currently selected version and the default region. 
     * <p>
     * Additional data beyond the default should be specified in the champData
     * parameter. Having "all" in the champData parameter retrieves all 
     * additional champion data. If no additional data is requested in 
     * champData, the minimum data will be retrieved from the API server.
     * 
     * @param   byId        Flag indicating weather the champions should be 
     *                      ordered by their championId's.
     * @param   champData   List of additional data to be requested from the 
     *                      API server.
     * @return  An API request for all champions' data and in game statistics.
     */
    public static LolStaticData champions(boolean byId, String... champData) {
        Subtype type = Subtype.CHAMPION;
        LolStaticData data = new LolStaticData(type);
        data.build(type, byId, champData);
        return data;
    }
    
    /**
     * Creates an API request for a specific champion's data, requested by the
     * champion's id based on the current version and the default region. 
     * Additional data beyond the default should be specified in 
     * the champData parameter. Having "all" in the champData parameter 
     * retrieves all additional champion data. If no additional data is 
     * requested in champData, the minimum data will be retrieved from the API 
     * server.
     * 
     * @param   id          The id of the champion requested.
     * @param   champData   List of additional data to be requested from the 
     *                      API server.
     * @return  An API request for all champions' data and in game statistics.
     */
    public static LolStaticData champion(int id, String... champData) {
        Subtype type = Subtype.CHAMPION;
        LolStaticData data = new LolStaticData(type);
        data.build(type, id, champData);
        return data;
    }
    
    /**
     * Creates an API request for all items' data based on the current region's
     * version and its default locale. Additional data beyond the default should
     * be specified in the itemData parameter. Having "all" in the itemData 
     * parameter retrieves all additional item data. If no additional data is
     * requested in itemData, the minimum data will be retrieved from the API
     * server.
     * 
     * @param   itemData    List of additional data to be requested from the
     *                      API server.
     * @return  an API request for all items' data and in game statistics.
     */
    public static LolStaticData items(String... itemData) {
        Subtype type = Subtype.ITEM;
        LolStaticData data = new LolStaticData(type);
        data.build(type, false, itemData);
        return data;
    }
    
    /**
     * Creates an API request for a specific item's data based on the current
     * region's version and its default locale. Additional data beyond the
     * default should be specified in the itemData parameter. Having "all" in 
     * the itemData parameter retrieves all additional item data. If no 
     * additional data is request in itemData, the minimum data will be 
     * retrieved from the API server.
     * 
     * @param id        id of the item requested
     * @param itemData  List of additional data to be requested from the API
     *                  server.
     * @return  An API request for a specific item's data and in game 
     *          statistics.
     */
    public static LolStaticData item(int id, String... itemData) {
        Subtype type = Subtype.ITEM;
        LolStaticData data = new LolStaticData(type);
        data.build(type, id, itemData);
        return data;
    }
    
    /**
     * Creates an API request for the language strings for the current region's 
     * default locale.
     * 
     * @return  An API request for the language strings for the current region's
     *          default locale.
     */
    public static LolStaticData languageStrings() {
        Subtype type = Subtype.LANGUAGE_STRINGS;
        LolStaticData data = new LolStaticData(type);
        data.bareBuild(type);
        return data;
    }
    
    /**
     * Creates an API request for all languages the API supports.
     * 
     * @return  An API request for all languages the API supports.
     */
    public static LolStaticData languages() {
        Subtype type = Subtype.LANGUAGES;
        LolStaticData data = new LolStaticData(type);
        data.bareBuild(type);
        return data;
    }
    
    /**
     * Creates an API request for the current region's data for all in-game 
     * maps.
     * 
     * @return  An API request for the current region's data for all in-game 
     *          maps.
     */
    public static LolStaticData map() {
        Subtype type = Subtype.MAP;
        LolStaticData data = new LolStaticData(type);
        data.bareBuild(type);
        return data;
    }
    
    /**
     * Creates an API request for the current region's and current version's 
     * masteries.
     * 
     * @return  A request for the current region's and version's masteries.
     */
    public static LolStaticData masteries() {
        Subtype type = Subtype.MASTERY;
        LolStaticData data = new LolStaticData(type);
        data.build(type);
        return data;
    }
    
    /**
     * Creates an API request for a specified matery's data for the current
     * region and version.
     * 
     * @param id    id of the requested mastery.
     * @return      A request for a specific mastery.
     */
    public static LolStaticData mastery(int id) {
        Subtype type = Subtype.MASTERY;
        LolStaticData data = new LolStaticData(type);
        data.build(type, id);
        return data;
    }
    
    /**
     * Creates an API request for the current region's realm data.
     * 
     * @return  An API request for the current region's realm data.
     */
    public static LolStaticData realm() {
        Subtype type = Subtype.REALM;
        LolStaticData data = new LolStaticData(type);
        data.bareBuild(type);
        return data;
    }
    
    /**
     * Creates an API request for the current region's and current version's 
     * runes.
     * 
     * @return  A request for the current region's and version's runes.
     */
    public static LolStaticData runes() {
        Subtype type = Subtype.RUNE;
        LolStaticData data = new LolStaticData(type);
        data.build(type);
        return data;
    }
    
    /**
     * Creates an API request for a specified rune's data for the current
     * region and version.
     * 
     * @param id    id of the requested rune.
     * @return      A request for a specific rune.
     */
    public static LolStaticData rune(int id) {
        Subtype type = Subtype.RUNE;
        LolStaticData data = new LolStaticData(type);
        data.build(type, id);
        return data;
    }
    
    /**
     * Creates an API request for the current region's and current version's 
     * summoner spells.
     * 
     * @return  A request for the current region's and version's summoner spells.
     */
    public static LolStaticData summonerSpells() {
        Subtype type = Subtype.SUMMONER_SPELL;
        LolStaticData data = new LolStaticData(type);
        data.build(type);
        return data;
    }
    
    /**
     * Creates an API request for a specified summoner spell's data for the 
     * current region and version.
     * 
     * @param id    id of the requested summoner spell.
     * @return      A request for a specific summoner spell.
     */
    public static LolStaticData summonerSpell(int id) {
        Subtype type = Subtype.SUMMONER_SPELL;
        LolStaticData data = new LolStaticData(type);
        data.build(type, id);
        return data;
    }
    
    /**
     * Creates an API request for all versions for the current region.
     * 
     * @return  An API request for all versions for the current region.
     */
    public static LolStaticData versions() {
        Subtype type = Subtype.VERSIONS;
        LolStaticData data = new LolStaticData(type);
        data.bareBuild(type);
        return data;
    }
    
    /**
     * Sets the locale for future requests to the specified locale.
     * 
     * @param   locale  Language locale to be used for future requests. If null,
     *                  future requests will be made with the current region's
     *                  default locale.
     * @see     Locales
     */
    public static void setLocale(Locales locale) {
        LolStaticData.locale = locale;
    }
    
    /**
     * Sets the version for future requests to the specified version.
     * 
     * @param version   Version for future requests. If null, future requests
     *                  will be made with the most recent version for the 
     *                  current region.
     */
    public static void setVersion(String version) {
        //verify version is valid here.
        LolStaticData.version = version;
    }
    
    public RequestType type() {
        return RequestType.LOL_STATIC_DATA;
    }
    
    /**
     * Returns the subtype of the request.
     * 
     * @return  The subtype of the request.
     */
    public String subtype() {
        return subtype.toString();
    }
    
    public boolean hasSubtype() {
        return true;
    }
    
    private void build(Subtype type, int id, String... data) {
        begin();
        url.append(base1)
                .append(LolAPI.getCurrentRegion().ABREV)
                .append(base2)
                .append(type)
                .append('/')
                .append(id)
                .append('?');
        evaluateLocale();
        evaluateVersion();
        if (type == Subtype.ITEM) evaluateItemData(data);
        if (type == Subtype.CHAMPION) evaluateChampData(data);
        end();
    }
    
    private void build(Subtype type) {
        begin();
        url.append(base1)
                .append(LolAPI.getCurrentRegion().ABREV)
                .append(base2)
                .append(type)
                .append('?');
        evaluateLocale();
        evaluateVersion();
        end();
    }
    
    private void build(Subtype type, int id) {
        begin();
        url.append(base1)
                .append(LolAPI.getCurrentRegion().ABREV)
                .append(base2)
                .append(type)
                .append('/')
                .append(id)
                .append('?');
        evaluateLocale();
        evaluateVersion();
        end();
    }
    
    private void build(Subtype type, boolean byId,
            String... data) {
        begin();
        url.append(base1)
                .append(LolAPI.getCurrentRegion().ABREV)
                .append(base2)
                .append(type)
                .append('?');
        evaluateLocale();
        evaluateVersion();
        if (byId) {
            url.append("dataById=true&");
        }
        if (type == Subtype.ITEM) evaluateItemData(data);
        if (type == Subtype.CHAMPION) evaluateChampData(data);
        end();
    }
    
    /*
     * Used for requests that do not base their response on versions or locale.
     */
    private void bareBuild(Subtype type) {
        begin();
        url.append(base1)
                .append(LolAPI.getCurrentRegion().ABREV)
                .append(base2)
                .append(type)
                .append('?');
        end();
    }
    
    private void evaluateLocale() {
        if (locale == null) return;
        url.append("locale=")
                .append(locale.toString())
                .append('&');
    }
    
    private void evaluateVersion() {
        if (version == null || version == "") return;
        url.append("version=")
                .append(version)
                .append('&');
    }
    
    private void evaluateChampData(String... data) {
        if (data.length == 0) return;
        url.append("champData=")
                .append(data[0]);
        for (int i = 1; i < data.length; i++) {
            url.append(',')
                    .append(data[i]);
        }
        url.append('&');
    }
    
    private void evaluateItemData(String... data) {
        if (data.length == 0) return;
        url.append("itemData=")
                .append(data[0]);
        for (int i = 1; i < data.length; i++) {
            url.append(',')
                    .append(data[i]);
        }
        url.append('&');
    }
    
    private LolStaticData(Subtype subtype) {
        rateLimited = false;
        this.subtype = subtype;
    }
    
    private enum Subtype {
        
        CHAMPION,
        ITEM,
        LANGUAGE_STRINGS,
        LANGUAGES,
        MAP,
        MASTERY,
        REALM,
        RUNE,
        SUMMONER_SPELL,
        VERSIONS;
        
        @Override
        public String toString() {
            switch (this) {
                case LANGUAGE_STRINGS:
                    return "language-strings";
                case SUMMONER_SPELL:
                    return "summoner-spell";
                default:
                    return this.name().toLowerCase();
            }
        }
        
    }

}
