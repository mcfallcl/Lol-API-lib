package riotapiwrapper;

/**
 * Enums for every map for League of Legends.
 * 
 * @author Christopher McFall
 *
 */
public enum GameMaps {
    
    /**
     * The old summer Summoner's Rift.
     */
    SUMMONERS_RIFT_SUMMER(1, "Summoner's Rift"),
    
    /**
     * The old autumn Summoner's Rift.
     */
    SUMMONERS_RIFT_AUTUMN(2, "Summoner's Rift"),
    
    /**
     * I am not sure.
     */
    PROVING_GROUNDS(3, "Proving Grounds"),
    
    /**
     * The old Twisted Treeline.
     */
    TWISTED_TREELINE_OLD(4, "Twisted Treeline"),
    
    /**
     * The Crystal Scar, used for the Dominion and Ascension game modes.
     */
    CRYSTAL_SCAR(8, "Crystal Scar"),
    
    /**
     * The current iteration of the Twisted Treeline, used for all 3v3 game
     * modes, and a version of Hexakill.
     */
    TWISTED_TREELINE_NEW(10, "Twisted Treeline"),
    
    /**
     * The current iteration of Summoner's Rift.
     */
    SUMMONERS_RIFT_NEW(11, "Summoner's Rift"),
    
    /**
     * The Howling Abyss, used for ARAMs and a few other special game modes.
     */
    HOWLING_ABYSS(12, "Howling Abyss");
    
    /**
     * The ID of the map
     */
    public final int ID;
    
    /**
     * The name of the map.
     */
    public final String NAME;
    
    private GameMaps(int id, String name) {
        this.ID = id;
        this.NAME = name;
    }
    
    /**
     * Takes the numerical ID and returns the associated League of Legends map.
     * 
     * @param id    The ID of the map requested.
     * @return      The {@code GameMap} enum with the given id.
     */
    public static GameMaps getMap(int id) {
        GameMaps map;
        switch(id) {
            case 1:
                map = SUMMONERS_RIFT_SUMMER;
                break;
            case 2:
                map = SUMMONERS_RIFT_AUTUMN;
                break;
            case 3:
                map = PROVING_GROUNDS;
                break;
            case 4:
                map = TWISTED_TREELINE_OLD;
                break;
            case 8:
                map = CRYSTAL_SCAR;
                break;
            case 10:
                map = TWISTED_TREELINE_NEW;
                break;
            case 11:
                map = SUMMONERS_RIFT_NEW;
                break;
            case 12:
                map = HOWLING_ABYSS;
                break;
            default:
                throw new IllegalArgumentException("Not a valid ID");
        }
        return map;
    }
    
}
