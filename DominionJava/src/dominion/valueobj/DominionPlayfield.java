package dominion.valueobj;

import java.util.ArrayList;
import java.util.LinkedList;
import dominion.card.Card;

public class DominionPlayfield
{
    private LinkedList<Card> mDeck               = new LinkedList<Card>();
    private ArrayList<Card>  mGraveyard          = new ArrayList<Card>();
    private ArrayList<Card>  mCardInHand         = new ArrayList<Card>();

    private ArrayList<Card>  mNativeVillagePlace = new ArrayList<Card>();
    private ArrayList<Card>  mIslandPlace        = new ArrayList<Card>();
    private ArrayList<Card>  mLastingActionPlace = new ArrayList<Card>();

}
