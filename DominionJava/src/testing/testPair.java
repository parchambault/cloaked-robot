package testing;

import java.util.ArrayList;
import container.Pair;

public class testPair
{
    public static void main(String[] args)
    {
        ArrayList<Pair> pairsList = new ArrayList<Pair>();

        pairsList.add(new Pair("Hello",
                               "World"));
        pairsList.add(new Pair(35,
                               "WapitiStreet"));
        pairsList.add(new Pair(new Integer(2),
                               new Long(4)));
        pairsList.add(new Pair(2,
                               4));

        StringBuilder sb = new StringBuilder();
        for (Pair pairEntry : pairsList)
        {
            sb.append(pairEntry.toString())
              .append("\n");
        }
        System.out.println(sb.toString());

        Pair pairSearch = new Pair(35,
                                   "WapitiStreet");
        Pair pairMatcher1 = new Pair(2,
                                     4);
        Pair pairMatcher2 = new Pair(2l,
                                     4);
        System.out.println("Is 35 WapitiStreet contained in pairslist? " + pairsList.contains(pairSearch));

        System.out.println("Index of pair(2, 4)? " + pairsList.indexOf(pairMatcher1));
        System.out.println("Index of pair(2l, 4)? " + pairsList.indexOf(pairMatcher2));
    }
}
