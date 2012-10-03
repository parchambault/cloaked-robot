package dominion.valueobj;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import dominion.card.Card;
import dominion.util.CardUtil;

public class DominionGame
{
    private HashMap<Card, Integer>    mCardAvailable     = new HashMap<Card, Integer>();
    private ArrayList<DominionPlayer> mPlayerList        = new ArrayList<DominionPlayer>();

    private boolean                   mIsPotionAvailable = false;

    public DominionGame(Collection<Card> iPlayingCard,
                        Collection<DominionPlayer> iPlayerList)
    {
        mPlayerList.addAll(iPlayerList);
        setupCardQuantity(iPlayingCard);
    }

    private void setupCardQuantity(Collection<Card> iPlayingCard)
    {
        setupCommonCardQuantity();
        setupCardInPlayQuantity(iPlayingCard);
    }

    private void setupCommonCardQuantity()
    {
        // TODO: UPDATER les valeurs des cartes pour les bonnes au départ de la partie
        mCardAvailable.put(Card.COPPER,
                           99);
        mCardAvailable.put(Card.SILVER,
                           99);
        mCardAvailable.put(Card.GOLD,
                           99);
        mCardAvailable.put(Card.POTION,
                           16);
        mCardAvailable.put(Card.ESTATE,
                           CardUtil.getStartingVictoryCount(getPlayerList().size()));
        mCardAvailable.put(Card.DUCHY,
                           CardUtil.getStartingVictoryCount(getPlayerList().size()));
        mCardAvailable.put(Card.PROVINCE,
                           CardUtil.getStartingVictoryCount(getPlayerList().size()));
        mCardAvailable.put(Card.CURSE,
                           CardUtil.getStartingCurseCount(getPlayerList().size()));
    }

    private void setupCardInPlayQuantity(Collection<Card> iPlayingCard)
    {
        for (Card wCardToAdd : iPlayingCard)
        {
            if (!mIsPotionAvailable && wCardToAdd.isPotionCost())
            {
                mIsPotionAvailable = true;
            }
            mCardAvailable.put(wCardToAdd,
                               CardUtil.isCardVictory(wCardToAdd)
                                       ? CardUtil.getStartingSpecialVictoryCount(getPlayerList().size()) : 10);
        }
    }

    public Integer getCardCount(Card iCard)
    {
        if (!mCardAvailable.containsKey(iCard))
        {
            return 0;
        }
        return mCardAvailable.get(iCard);
    }

    public HashMap<Card, Integer> getCardAvailable()
    {
        return mCardAvailable;
    }

    public ArrayList<DominionPlayer> getPlayerList()
    {
        return mPlayerList;
    }

    public boolean isPotionAvailable()
    {
        return mIsPotionAvailable;
    }
}
