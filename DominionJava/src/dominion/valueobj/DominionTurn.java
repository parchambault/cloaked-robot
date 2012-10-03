package dominion.valueobj;

import java.util.ArrayList;
import dominion.card.Card;

public class DominionTurn
{
    private int             mNbrAvailableAction;
    private int             mNbrAvailablePurchase;
    private int             mNbrAvailableGold;
    private ArrayList<Card> mPlayedCardList = new ArrayList<Card>();
    private DominionPlayer  mCurrentPlayer;

}
