package dominion.card.comparator;

import java.util.Comparator;
import dominion.card.Card;

public class CostComparator
    implements Comparator<Card>
{

    @Override
    public int compare(Card iCard1,
                       Card iCard2)
    {
        int wResult = -1;
        wResult = new Integer(iCard1.getCardCost()).compareTo(new Integer(iCard2.getCardCost()));
        if (wResult == 0)
        {
            wResult = iCard1.toString()
                            .compareTo(iCard2.toString());
        }
        return wResult;
    }

}
