package dominion.card;

import java.util.Iterator;
import java.util.List;
import dominion.util.CardUtil;

public enum Card
{
    // Base set
    ESTATE(2, CardSet.COMMON, CardType.VICTORY, 0, 1),
    DUCHY(5, CardSet.COMMON, CardType.VICTORY, 0, 3),
    PROVINCE(8, CardSet.COMMON, CardType.VICTORY, 0, 6),
    CURSE(0, CardSet.COMMON, CardType.VICTORY, 0, -1),
    COPPER(0, CardSet.COMMON, CardType.TREASURE, 1, 0),
    SILVER(3, CardSet.COMMON, CardType.TREASURE, 2, 0),
    GOLD(6, CardSet.COMMON, CardType.TREASURE, 3, 0),

    // Dominion set
    ADVENTURER(6, CardSet.DOMINION, CardType.ACTION, 0, 0),
    BUREAUCRAT(4, CardSet.DOMINION, CardType.ACTION_ATTACK, 0, 0),
    CELLAR(2, CardSet.DOMINION, CardType.ACTION, 0, 0),
    CHANCELLOR(3, CardSet.DOMINION, CardType.ACTION, 2, 0),
    CHAPEL(2, CardSet.DOMINION, CardType.ACTION, 0, 0),
    COUNCILROOM(5, CardSet.DOMINION, CardType.ACTION, 0, 0),
    FEAST(4, CardSet.DOMINION, CardType.ACTION, 0, 0),
    FESTIVAL(5, CardSet.DOMINION, CardType.ACTION, 2, 0),
    GARDENS(4, CardSet.DOMINION, CardType.VICTORY, 0, 0) {
        @Override
        public int getVictoryPointValue(List<Card> iFinalDeck)
        {
            return (int) Math.floor(iFinalDeck.size() / 10);
        }
    },
    LABORATORY(5, CardSet.DOMINION, CardType.ACTION, 0, 0),
    LIBRARY(5, CardSet.DOMINION, CardType.ACTION, 0, 0),
    MARKET(5, CardSet.DOMINION, CardType.ACTION, 1, 0),
    MILITIA(4, CardSet.DOMINION, CardType.ACTION_ATTACK, 2, 0),
    MINE(5, CardSet.DOMINION, CardType.ACTION, 0, 0),
    MOAT(2, CardSet.DOMINION, CardType.ACTION_REACTION, 0, 0),
    MONEYLENDER(4, CardSet.DOMINION, CardType.ACTION, 0, 0),
    REMODEL(4, CardSet.DOMINION, CardType.ACTION, 0, 0),
    SMITHY(4, CardSet.DOMINION, CardType.ACTION, 0, 0),
    SPY(4, CardSet.DOMINION, CardType.ACTION_ATTACK, 0, 0),
    THIEF(4, CardSet.DOMINION, CardType.ACTION_ATTACK, 0, 0),
    THRONEROOM(4, CardSet.DOMINION, CardType.ACTION, 0, 0),
    VILLAGE(3, CardSet.DOMINION, CardType.ACTION, 0, 0),
    WITCH(5, CardSet.DOMINION, CardType.ACTION_ATTACK, 0, 0),
    WOODCUTTER(3, CardSet.DOMINION, CardType.ACTION, 2, 0),
    WORKSHOP(3, CardSet.DOMINION, CardType.ACTION, 0,0),

    // Intrigue set
    BARON(4, CardSet.INTRIGUE, CardType.ACTION, 0, 0),
    BRIDGE(4, CardSet.INTRIGUE, CardType.ACTION, 1, 0),
    CONSPIRATOR(4, CardSet.INTRIGUE, CardType.ACTION, 2, 0),
    COPPERSMITH(4, CardSet.INTRIGUE, CardType.ACTION, 0, 0),
    COURTYARD(2, CardSet.INTRIGUE, CardType.ACTION, 0, 0),
    DUKE(5, CardSet.INTRIGUE, CardType.VICTORY, 0, 0) {
        @Override
        public int getVictoryPointValue(List<Card> iFinalDeck)
        {
            int wVictoryPt = 0;
            Iterator<Card> wDeckIterator = iFinalDeck.iterator();
            while (wDeckIterator.hasNext())
            {
                if (wDeckIterator.next() == Card.DUCHY)
                {
                    wVictoryPt++;
                }
            }

            return wVictoryPt;
        }
    },
    GREATHALL(3, CardSet.INTRIGUE, CardType.ACTION_VICTORY, 0, 1),
    HAREM(6, CardSet.INTRIGUE, CardType.TREASURE_VICTORY, 2, 2),
    IRONWORKS(4, CardSet.INTRIGUE, CardType.ACTION, 0, 0),
    MASQUERADE(3, CardSet.INTRIGUE, CardType.ACTION, 0, 0),
    MININGVILLAGE(4, CardSet.INTRIGUE, CardType.ACTION, 0, 0),
    MINION(5, CardSet.INTRIGUE, CardType.ACTION_ATTACK, 0, 0),
    NOBLES(6, CardSet.INTRIGUE, CardType.ACTION_VICTORY, 0, 2),
    PAWN(2, CardSet.INTRIGUE, CardType.ACTION, 0, 0),
    SABOTEUR(5, CardSet.INTRIGUE, CardType.ACTION_ATTACK, 0, 0),
    SCOUT(4, CardSet.INTRIGUE, CardType.ACTION, 0, 0),
    SECRETCHAMBER(2, CardSet.INTRIGUE, CardType.ACTION_REACTION, 0, 0),
    SHANTYTOWN(3, CardSet.INTRIGUE, CardType.ACTION, 0, 0),
    STEWARD(3, CardSet.INTRIGUE, CardType.ACTION, 0, 0),
    SWINDLER(3, CardSet.INTRIGUE, CardType.ACTION_ATTACK, 2, 0),
    TORTURER(5, CardSet.INTRIGUE, CardType.ACTION_ATTACK, 0, 0),
    TRADINGPOST(5, CardSet.INTRIGUE, CardType.ACTION, 0, 0),
    TRIBUTE(5, CardSet.INTRIGUE, CardType.ACTION, 0, 0),
    UPGRADE(5, CardSet.INTRIGUE, CardType.ACTION, 0, 0),
    WISHINGWELL(3, CardSet.INTRIGUE, CardType.ACTION, 0, 0),

    // Seaside set
    AMBASSADOR(3, CardSet.SEASIDE, CardType.ACTION_ATTACK, 0, 0),
    BAZAAR(5, CardSet.SEASIDE, CardType.ACTION, 1, 0),
    CARAVAN(4, CardSet.SEASIDE, CardType.ACTION_DURATION, 0, 0),
    CUTPURSE(4, CardSet.SEASIDE, CardType.ACTION_ATTACK, 2, 0),
    EMBARGO(2, CardSet.SEASIDE, CardType.ACTION, 2, 0),
    EXPLORER(5, CardSet.SEASIDE, CardType.ACTION, 0, 0),
    FISHINGVILLAGE(3, CardSet.SEASIDE, CardType.ACTION_DURATION, 1, 0),
    GHOSTSHIP(5, CardSet.SEASIDE, CardType.ACTION_ATTACK, 0, 0),
    HAVEN(2, CardSet.SEASIDE, CardType.ACTION_DURATION, 0, 0),
    ISLAND(4, CardSet.SEASIDE, CardType.ACTION_VICTORY, 0, 2),
    LIGHTHOUSE(2, CardSet.SEASIDE, CardType.ACTION_DURATION, 1, 0),
    LOOKOUT(3, CardSet.SEASIDE, CardType.ACTION, 0, 0),
    MERCHANTSHIP(5, CardSet.SEASIDE, CardType.ACTION_DURATION, 2, 0),
    NATIVEVILLAGE(2, CardSet.SEASIDE, CardType.ACTION, 0, 0),
    NAVIGATOR(4, CardSet.SEASIDE, CardType.ACTION, 2, 0),
    OUTPOST(5, CardSet.SEASIDE, CardType.ACTION_DURATION, 0, 0),
    PEARLDIVER(2, CardSet.SEASIDE, CardType.ACTION, 0, 0),
    PIRATESHIP(4, CardSet.SEASIDE, CardType.ACTION_ATTACK, 0, 0),
    SALVAGER(4, CardSet.SEASIDE, CardType.ACTION, 0, 0),
    SEAHAG(4, CardSet.SEASIDE, CardType.ACTION_ATTACK, 0, 0),
    SMUGGLERS(3, CardSet.SEASIDE, CardType.ACTION, 0, 0),
    TACTICIAN(5, CardSet.SEASIDE, CardType.ACTION_DURATION, 0, 0),
    TREASUREMAP(4, CardSet.SEASIDE, CardType.ACTION, 0, 0),
    TREASURY(5, CardSet.SEASIDE, CardType.ACTION, 1, 0),
    WAREHOUSE(3, CardSet.SEASIDE, CardType.ACTION, 0, 0),
    WHARF(5, CardSet.SEASIDE, CardType.ACTION_DURATION, 0, 0),

    // Alchemy set
    ALCHEMIST(3, CardSet.ALCHEMY, CardType.ACTION, 0, 0) {
        @Override
        public boolean isPotionCost()
        {
            return true;
        }
    },
    APOTHECARY(2, CardSet.ALCHEMY, CardType.ACTION, 0, 0) {
        @Override
        public boolean isPotionCost()
        {
            return true;
        }
    },
    APPRENTICE(5, CardSet.ALCHEMY, CardType.ACTION, 0, 0),
    FAMILIAR(3, CardSet.ALCHEMY, CardType.ACTION_ATTACK, 0, 0) {
        @Override
        public boolean isPotionCost()
        {
            return true;
        }
    },
    GOLEM(4, CardSet.ALCHEMY, CardType.ACTION, 0, 0) {
        @Override
        public boolean isPotionCost()
        {
            return true;
        }
    },
    HERBALIST(2, CardSet.ALCHEMY, CardType.ACTION, 1, 0),
    PHILOSOPHERSSTONE(3, CardSet.ALCHEMY, CardType.TREASURE, 0, 0) {
        @Override
        public int getGoldValue(List<Card> iCombinedDeckAndGraveyard)
        {
            return (int) Math.floor(iCombinedDeckAndGraveyard.size() / 5);
        }

        @Override
        public boolean isPotionCost()
        {
            return true;
        }
    },
    POSSESSION(6, CardSet.ALCHEMY, CardType.ACTION, 0, 0) {
        @Override
        public boolean isPotionCost()
        {
            return true;
        }
    },
    POTION(4, CardSet.ALCHEMY, CardType.TREASURE, 0, 0),
    SCRYINGPOOL(2, CardSet.ALCHEMY, CardType.ACTION_ATTACK, 0, 0) {
        @Override
        public boolean isPotionCost()
        {
            return true;
        }
    },
    TRANSMUTE(0, CardSet.ALCHEMY, CardType.ACTION, 0, 0) {
        @Override
        public boolean isPotionCost()
        {
            return true;
        }
    },
    UNIVERSITY(2, CardSet.ALCHEMY, CardType.ACTION, 0, 0) {
        @Override
        public boolean isPotionCost()
        {
            return true;
        }
    },
    VINEYARD(0, CardSet.ALCHEMY, CardType.VICTORY, 0, 0) {
        @Override
        public int getVictoryPointValue(List<Card> iFinalDeck)
        {
            int wActionCardCount = 0;
            Iterator<Card> wDeckIterator = iFinalDeck.iterator();
            while (wDeckIterator.hasNext())
            {
                if (CardUtil.isCardAction(wDeckIterator.next()))
                {
                    wActionCardCount++;
                }
            }

            return (int) Math.floor(wActionCardCount / 3);
        }

        @Override
        public boolean isPotionCost()
        {
            return true;
        }
    },

    // Promotional cards
    BLACKMARKET(3, CardSet.PROMO, CardType.ACTION, 2, 0),
    ENVOY(4, CardSet.PROMO, CardType.ACTION, 0, 0),
    STASH(4, CardSet.PROMO, CardType.TREASURE, 2, 0),

    ;

    private int      mCardCost;
    private CardSet  mCardSet;
    private CardType mCardType;
    private int      mGoldValue;
    private int      mVictoryPointValue;

    private Card(int iCardCost,
                 CardSet iCardSet,
                 CardType iCardType,
                 int iGoldValue,
                 int iVictoryPointValue)
    {
        mCardCost = iCardCost;
        mCardSet = iCardSet;
        mCardType = iCardType;
        mGoldValue = iGoldValue;
        mVictoryPointValue = iVictoryPointValue;
    }

    public int getCardCost()
    {
        return mCardCost;
    }

    public CardSet getCardSet()
    {
        return mCardSet;
    }

    public CardType getCardType()
    {
        return mCardType;
    }

    public int getGoldValue(List<Card> iCombinedDeckAndGraveyard)
    {
        return mGoldValue;
    }

    public int getVictoryPointValue(List<Card> iFinalDeck)
    {
        return mVictoryPointValue;
    }

    public boolean isPotionCost()
    {
        return false;
    }
}
