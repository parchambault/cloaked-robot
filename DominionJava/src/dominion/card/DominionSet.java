package dominion.card;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import dominion.card.comparator.CostComparator;

public enum DominionSet
{
    FIRST_GAME(CardSet.DOMINION,
               new Card[] {
            Card.WORKSHOP,
            Card.WOODCUTTER,
            Card.CELLAR,
            Card.MOAT,
            Card.SMITHY,
            Card.MARKET,
            Card.MILITIA,
            Card.MINE,
            Card.REMODEL,
            Card.VILLAGE}),
    TREASURES(CardSet.DOMINION,
              new Card[] {
            Card.ADVENTURER,
            Card.BUREAUCRAT,
            Card.CHANCELLOR,
            Card.CHAPEL,
            Card.FEAST,
            Card.LABORATORY,
            Card.MARKET,
            Card.MINE,
            Card.MONEYLENDER,
            Card.THRONEROOM}),
    INTERACTION(CardSet.DOMINION,
                new Card[] {
            Card.LIBRARY,
            Card.BUREAUCRAT,
            Card.COUNCILROOM,
            Card.CHANCELLOR,
            Card.MOAT,
            Card.SPY,
            Card.FESTIVAL,
            Card.MILITIA,
            Card.VILLAGE,
            Card.THIEF}),
    GROWTH(CardSet.DOMINION,
           new Card[] {
            Card.WORKSHOP,
            Card.WOODCUTTER,
            Card.CELLAR,
            Card.CHAPEL,
            Card.FEAST,
            Card.GARDENS,
            Card.LABORATORY,
            Card.WITCH,
            Card.VILLAGE,
            Card.THIEF}),
    VILLAGE_SQUARE(CardSet.DOMINION,
                   new Card[] {
            Card.LIBRARY,
            Card.WOODCUTTER,
            Card.BUREAUCRAT,
            Card.CELLAR,
            Card.FESTIVAL,
            Card.SMITHY,
            Card.MARKET,
            Card.REMODEL,
            Card.THRONEROOM,
            Card.VILLAGE}),
    VICTORY_DANCE(CardSet.INTRIGUE,
                  new Card[] {
            Card.DUKE,
            Card.SCOUT,
            Card.IRONWORKS,
            Card.GREATHALL,
            Card.HAREM,
            Card.MASQUERADE,
            Card.UPGRADE,
            Card.NOBLES,
            Card.PAWN,
            Card.BRIDGE}),
    CONSPIRATION(CardSet.INTRIGUE,
                 new Card[] {
            Card.CONSPIRATOR,
            Card.TRADINGPOST,
            Card.SWINDLER,
            Card.IRONWORKS,
            Card.HAREM,
            Card.TRIBUTE,
            Card.STEWARD,
            Card.PAWN,
            Card.SABOTEUR,
            Card.SHANTYTOWN}),
    BEST_WISHES(CardSet.INTRIGUE,
                new Card[] {
            Card.TORTURER,
            Card.COPPERSMITH,
            Card.TRADINGPOST,
            Card.COURTYARD,
            Card.SCOUT,
            Card.STEWARD,
            Card.MASQUERADE,
            Card.UPGRADE,
            Card.WISHINGWELL,
            Card.SHANTYTOWN}),
    DEMOLITION(CardSet.INTRIGUE,
               new Card[] {
            Card.TORTURER,
            Card.SECRETCHAMBER,
            Card.SWINDLER,
            Card.SABOTEUR,
            Card.BRIDGE,
            Card.MININGVILLAGE,
            Card.SPY,
            Card.REMODEL,
            Card.THRONEROOM,
            Card.THIEF}),
    CORRUPTING_POWER(CardSet.INTRIGUE,
                     new Card[] {
            Card.TORTURER,
            Card.COURTYARD,
            Card.STEWARD,
            Card.MINION,
            Card.NOBLES,
            Card.BUREAUCRAT,
            Card.COUNCILROOM,
            Card.CHANCELLOR,
            Card.MILITIA,
            Card.MINE}),
    UNDERTAKER(CardSet.INTRIGUE,
               new Card[] {
            Card.BARON,
            Card.STEWARD,
            Card.MINION,
            Card.MASQUERADE,
            Card.NOBLES,
            Card.PAWN,
            Card.LIBRARY,
            Card.CELLAR,
            Card.FESTIVAL,
            Card.WITCH}),
    HIGH_SEAS(CardSet.SEASIDE,
              new Card[] {
            Card.PIRATESHIP,
            Card.BAZAAR,
            Card.CARAVAN,
            Card.SMUGGLERS,
            Card.EMBARGO,
            Card.EXPLORER,
            Card.HAVEN,
            Card.ISLAND,
            Card.WHARF,
            Card.LOOKOUT}),
    SHIPWRECK(CardSet.SEASIDE,
              new Card[] {
            Card.SMUGGLERS,
            Card.WAREHOUSE,
            Card.NAVIGATOR,
            Card.MERCHANTSHIP,
            Card.PEARLDIVER,
            Card.SALVAGER,
            Card.SEAHAG,
            Card.TREASURY,
            Card.GHOSTSHIP,
            Card.NATIVEVILLAGE}),
    BURIED_TREASURES(CardSet.SEASIDE,
                     new Card[] {
            Card.AMBASSADOR,
            Card.OUTPOST,
            Card.TREASUREMAP,
            Card.CUTPURSE,
            Card.WAREHOUSE,
            Card.LIGHTHOUSE,
            Card.PEARLDIVER,
            Card.WHARF,
            Card.TACTICIAN,
            Card.FISHINGVILLAGE}),
    GIVE_AND_TAKE(CardSet.SEASIDE,
                  new Card[] {
            Card.AMBASSADOR,
            Card.SMUGGLERS,
            Card.HAVEN,
            Card.ISLAND,
            Card.SALVAGER,
            Card.FISHINGVILLAGE,
            Card.LIBRARY,
            Card.MARKET,
            Card.MONEYLENDER,
            Card.WITCH}),
    ADVENTURING_CAREER(CardSet.SEASIDE,
                       new Card[] {
            Card.TREASUREMAP,
            Card.CUTPURSE,
            Card.SEAHAG,
            Card.GHOSTSHIP,
            Card.LOOKOUT,
            Card.ADVENTURER,
            Card.CELLAR,
            Card.COUNCILROOM,
            Card.SPY,
            Card.VILLAGE}),
    REPETITION(CardSet.SEASIDE,
               new Card[] {
            Card.OUTPOST,
            Card.PIRATESHIP,
            Card.CARAVAN,
            Card.EXPLORER,
            Card.PEARLDIVER,
            Card.TREASURY,
            Card.WORKSHOP,
            Card.CHANCELLOR,
            Card.FESTIVAL,
            Card.MILITIA}),
            ;

    private List<Card>                 mCardList             = new ArrayList<Card>();
    private CardSet                    mCardSet;
    private TreeSet<Card> mPreselectedGameSetup = new TreeSet<Card>(new CostComparator());

    private DominionSet(CardSet iCardSet,
                        Card[] iPreselectedCards)
    {
        mCardSet = iCardSet;
        for (Card cardEntry : Card.values())
        {
            if (cardEntry.getCardSet() == getCardSet())
            {
                addCard(cardEntry);
            }
        }
        
        for (Card presetEntry : iPreselectedCards)
        {
            mPreselectedGameSetup.add(presetEntry);
        }
    }

    private void addCard(Card iCard)
    {
        mCardList.add(iCard);
    }
    
    public List<Card> getCardList()
    {
        return mCardList;
    }

    public CardSet getCardSet()
    {
        return mCardSet;
    }

    public TreeSet<Card> getPreselectedGameSetup()
    {
        return mPreselectedGameSetup;
    }
}
