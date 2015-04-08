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
        LolStaticData data = new LolStaticData();
        data.build("champion", byId, champData);
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
        LolStaticData data = new LolStaticData();
        data.build("champion", id, champData);
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
        LolStaticData data = new LolStaticData();
        data.build("item", false, itemData);
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
        LolStaticData data = new LolStaticData();
        data.build("item", id, itemData);
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
        LolStaticData data = new LolStaticData();
        data.bareBuild("language-strings");
        return data;
    }
    
    /**
     * Creates an API request for all languages the API supports.
     * 
     * @return  An API request for all languages the API supports.
     */
    public static LolStaticData languages() {
        LolStaticData data = new LolStaticData();
        data.bareBuild("languages");
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
        LolStaticData data = new LolStaticData();
        data.bareBuild("map");
        return data;
    }
    
    /**
     * 
     * @return
     */
    public static LolStaticData masteries() {
        LolStaticData data = new LolStaticData();
        data.build("mastery");
        return data;
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public static LolStaticData mastery(int id) {
        LolStaticData data = new LolStaticData();
        data.build("mastery", id);
        return data;
    }
    
    /**
     * Creates an API request for the current region's realm data.
     * 
     * @return  An API request for the current region's realm data.
     */
    public static LolStaticData realm() {
        LolStaticData data = new LolStaticData();
        data.bareBuild("realm");
        return data;
    }
    
    /**
     * 
     * @return
     */
    public static LolStaticData runes() {
        LolStaticData data = new LolStaticData();
        data.build("rune");
        return data;
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public static LolStaticData rune(int id) {
        LolStaticData data = new LolStaticData();
        data.build("rune", id);
        return data;
    }
    
    /**
     * 
     * @return
     */
    public static LolStaticData summonerSpells() {
        LolStaticData data = new LolStaticData();
        data.build("summoner-spell");
        return data;
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public static LolStaticData summonerSpell(int id) {
        LolStaticData data = new LolStaticData();
        data.build("summoner-spell", id);
        return data;
    }
    
    /**
     * Creates an API request for all versions for the current region.
     * 
     * @return  An API request for all versions for the current region.
     */
    public static LolStaticData versions() {
        LolStaticData data = new LolStaticData();
        data.bareBuild("versions");
        return data;
    }
    
    /**
     * Sets the locale for future requests to be the current region's default.
     * 
     * @see Locales
     */
    public static void defaultLocale() {
        locale = null;
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
     * 
     * @param version
     */
    public static void setVersion(String version) {
        //verify version is valid here.
        LolStaticData.version = version;
    }
    
    private void build(String type, int id, String... data) {
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
        evaluateData(data);
        end();
    }
    
    private void build(String type) {
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
    
    private void build(String type, int id) {
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
    
    private void build(String type, boolean byId,
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
        evaluateData(data);
        end();
    }
    
    /*
     * Used for requests that do not base their response on versions or locale.
     */
    private void bareBuild(String type) {
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
    
    private void evaluateData(String... data) {
        if (data.length == 0) return;
        url.append("champData=")
                .append(data[0]);
        for (int i = 1; i < data.length; i++) {
            url.append(',')
                    .append(data[i]);
        }
        url.append('&');
    }
    
    private LolStaticData() {
        rateLimited = false;
    }

}
