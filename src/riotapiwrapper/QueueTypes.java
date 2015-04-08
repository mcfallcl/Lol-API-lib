package riotapiwrapper;

/**
 * Enums for the various types of games in League of Legends.
 * 
 * @author Christopher McFall
 *
 */
public enum QueueTypes {
    
    /**
     * Custom games.
     */
    CUSTOM(0, "Custom games"),
    
    /**
     * Unranked Summoner's Rift blind pick.
     */
    NORMAL_5x5_BLIND(2, "Normal 5v5 Blind Pick games"),
    
    /**
     * Historical Summoner's Rift bot games.
     */
    BOT_5x5(7, "Historical Summoner's Rift Coop vs AI games"),
    
    /**
     * Summoner's Rift against bots with introductory level AI.
     */
    BOT_5x5_INTRO(31, "summoner's Rift Coop vs AI Intro Bot games"),
    
    /**
     * Summoner's Rift against bots with beginner level AI.
     */
    BOT_5x5_BEGINNER(32, "Summoner's Rift Coop vs AI Beginner Bot games"),
    
    /**
     * Summoner's Rift against bots with intermediate level AI.
     */
    BOT_5x5_INTERMEDIATE(33, "Historical Summoner's Rift Coop vs AI "
            + "Intermediate Bot games"),
            
    /**
     * Unranked Twisted Treeline blind pick.
     */
    NORMAL_3x3(8, "Normal 3v3 games"),
    
    /**
     * Unranked Summoner's Rift draft pick.
     */
    NORMAL_5x5_DRAFT(14, "Normal 5v5 Draft Pick games"),
    
    /**
     * Unranked Dominion blind pick.
     */
    ODIN_5x5_BLIND(16, "Dominion 5v5 Blind Pick games"),
    
    /**
     * Unranked Dominion draft pick.
     */
    ODIN_5x5_DRAFT(17, "Dominion 5v5 Draft Pick games"),
    
    /**
     * Dominion game against bots.
     */
    BOT_ODIN_5x5(25, "Dominion Coop vs AI games"),
    
    /**
     * Summoner's Rift ranked solo queue game.
     */
    RANKED_SOLO_5x5(4, "Ranked Solo 5v5 games"),
    
    /**
     * Unsure, but I believe it could be historical Twisted Treeline ranked team 
     * games.
     */
    RANKED_PREMADE_3x3(9, "Ranked Premade 3v3 games"),
    
    /**
     * Unsure, but I believe it could be historical Summoner's Rift ranked team
     * games.
     */
    RANKED_PREMADE_5x5(6, "Ranked Premade 5v5 games"),
    
    /**
     * Team ranked Twisted Treeline game.
     */
    RANKED_TEAM_3x3(41, "Ranked Team 3v3 games"),
    
    /**
     * Team ranked Summoner's Rift game.
     */
    RANKED_TEAM_5x5(42, "Ranked Team 5v5 games"),
    
    /**
     * Twisted Treeline game against bots.
     */
    BOT_TT_3x3(52, "Twisted Treeline Coop vs AI games"),
    
    /**
     * Team Builder game.
     */
    GROUP_FINDER_5x5(61, "Team Builder games"),
    
    /**
     * All Random All Mid game on the Howling Abyss.
     */
    ARAM_5x5(65, "ARAM games"),
    
    /**
     * One For All special game type on Summoner's Rift.
     */
    ONEFORALL_5x5(70, "One for All games"),
    
    /**
     * 1v1 game on Howling Abyss.
     */
    FIRSTBLOOD_1x1(72, "Snowdown Showdown 1v1 games"),
    
    /**
     * 2v2 game on Howling Abyss.
     */
    FIRSTBLOOD_2x2(73, "Snowdown Showdown 2v2 games"),
    
    /**
     * Hexakill special game type on Summoner's Rift.
     */
    SR_6x6(75, "Summoner's Rift 6x6 Hexakill games"),
    
    /**
     * Ultra Rapid Fire special game type on Summoner's Rift.
     */
    URF_5x5(76, "Ultra Rapid Fire games"),
    
    /**
     * Ultra Rapid Fire special game type against bots on Summoner's Rift.
     */
    BOT_URF_5x5(83, "Ultra Rapid Fire games played against AI games"),
    
    /**
     * Nightmare Bots special game type with level 1 difficulty on Summoner's 
     * Rift.
     */
    NIGHTMARE_BOT_5x5_RANK1(91, "Doom Bots Rank 1 games"),
    
    /**
     * Nightmare Bots special game type with level 2 difficulty on Summoner's 
     * Rift.
     */
    NIGHTMARE_BOT_5x5_RANK2(92, "Doom Bots Rank 2 games"),
    
    /**
     * Nightmare Bots special game type with level 5 difficulty on Summoner's 
     * Rift.
     */
    NIGHTMARE_BOT_5x5_RANK5(93, "Doom Bots Rank 5 games"),
    
    /**
     * Ascension special game type on The Crystal Scar.
     */
    ASCENSION_5x5(96, "Ascension games"),
    
    /**
     * Hexakill special game type on the Twisted Treeline.
     */
    HEXAKILL(98, "Twisted Treeline 6x6 Hexakill games"),
    
    /**
     * King Poro special game type on The Howling Abyss.
     */
    KING_PORO_5x5(300, "King Poro games"),
    
    /**
     * Nemesis Draft special game type on Summoner's Rift.
     */
    COUNTER_PICK(310, "Nemesis games");
    
    /**
     * The ID of the {@code QueueType}.
     */
    public final int CONFIG_ID;
    
    /**
     * The string representation of the {@code QueueType}.
     */
    public final String DESCRIPTION;
    
    private QueueTypes(int id, String description) {
        this.CONFIG_ID = id;
        this.DESCRIPTION = description;
    }
    
    /**
     * Returns a flag indicating if the queue type has a challenger tier league.
     * 
     * @return  A flag indicating if the queue type has a challenger tier
     *          league.
     */
    public boolean hasChallengerTier() {
        if (this == RANKED_SOLO_5x5 ||
                this == RANKED_TEAM_3x3 ||
                this == RANKED_TEAM_5x5){
            return true;
        }
        return false;
    }
    
    /**
     * Returns a flag indicating if the queue type is a ranked matchmaking
     * queue.
     * 
     * @return  A flag indicating if the queue type is a ranked matchmaking
     *          queue.
     */
    public boolean isRanked() {
        if (this == RANKED_SOLO_5x5 ||
                this == RANKED_TEAM_3x3 ||
                this == RANKED_TEAM_5x5){
            return true;
        }
        return false;
    }
    
}
