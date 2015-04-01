package riotapiwrapper;

/**
 * Enums for each region's servers for the League of Legends API.
 * 
 * @author Christopher McFall
 *
 */
public enum Regions {
    
    /**
     * Brazil.
     */
    BR("Brazil", "br", "BR1"),
    
    /**
     * Nordic and East Europe.
     */
    EUNE("EU Nordic & East", "eune", "EUN1"),
    
    /**
     * West Europe.
     */
    EUW("EU West", "euw", "EUW1"),
    
    /**
     * South Korea.
     */
    KR("Korea", "kr", "KR"),
    
    /**
     * North Latin America.
     */
    LAN("Latin America North", "lan", "LA1"),
    
    /**
     * South Latin America.
     */
    LAS("Latin America South", "las", "LA2"),
    
    /**
     * North America.
     */
    NA("North America", "na", "NA1"),
    
    /**
     * Oceania.
     */
    OCE("Oceania", "oce", "OC1"),
    
    /**
     * Russia.
     */
    RU("Russia", "ru", "RU"),
    
    /**
     * Turkey.
     */
    TR("Turkey", "tr", "TR1"),
    
    /**
     * The Public Beta Enviornment.
     */
    PBE("Public Beta Enviornment", "global", "PBE1");
    
    /**
     * The region's name.
     */
    public final String NAME;
    
    /**
     * The region abbreviated, used for the Request URLs.
     */
    public final String ABREV;
    
    /**
     * The platform ID, used for spectating.
     */
    public final String PLATFORM_ID;
    
    private Regions(String name, String abr, String platform) {
        this.NAME = name;
        this.ABREV = abr;
        this.PLATFORM_ID = platform;
    }
    
}
