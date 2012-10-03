package dominion.util;

import dominion.card.Card;
import dominion.card.CardType;

public class CardUtil
{
    private CardUtil()
    {
    }

    public static boolean isCardAction(Card iCard)
    {
        return iCard.getCardType() == CardType.ACTION || iCard.getCardType() == CardType.ACTION_ATTACK
                || iCard.getCardType() == CardType.ACTION_DURATION || iCard.getCardType() == CardType.ACTION_REACTION
                || iCard.getCardType() == CardType.ACTION_VICTORY;
    }

    public static boolean isCardTreasure(Card iCard)
    {
        return iCard.getCardType() == CardType.TREASURE || iCard.getCardType() == CardType.TREASURE_VICTORY;
    }

    public static boolean isCardVictory(Card iCard)
    {
        return iCard.getCardType() == CardType.ACTION_VICTORY || iCard.getCardType() == CardType.TREASURE_VICTORY
                || iCard.getCardType() == CardType.VICTORY;
    }

    public static int getStartingVictoryCount(int iNbrPlayer)
    {
        switch (iNbrPlayer)
        {
            case 2:
                return 8;
            case 3:
            case 4:
                return 12;
            case 5:
            case 6:
                return 16;
            default:
                throw new IllegalArgumentException("Invalid number of players in game: " + iNbrPlayer);
        }
    }

    public static int getStartingSpecialVictoryCount(int iNbrPlayer)
    {
        switch (iNbrPlayer)
        {
            case 2:
                return 8;
            case 3:
            case 4:
            case 5:
            case 6:
                return 12;
            default:
                throw new IllegalArgumentException("Invalid number of players in game: " + iNbrPlayer);
        }
    }

    public static int getStartingCurseCount(int iNbrPlayer)
    {
        return (10 * iNbrPlayer) - 10;
    }
}
