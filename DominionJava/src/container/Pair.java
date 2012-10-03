package container;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Pair<T1, T2>
    implements Comparable<Pair<T1, T2>>
{
    private static final Logger LOG = LogManager.getLogger(Pair.class);

    private T1                  mFirst;
    private T2                  mSecond;

    public Pair()
    {
    }

    public Pair(T1 iFirstMember,
                T2 mSecondMember)
    {
        setFirst(iFirstMember);
        setSecond(mSecondMember);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("First: ")
          .append(getFirst() != null ? getFirst().toString() : null)
          .append(" Second: ")
          .append(getSecond() != null ? getSecond().toString() : null);

        return sb.toString();
    }

    @Override
    public int compareTo(Pair<T1, T2> toCompare)
    {
        int result = -1;

        try
        {
            if (getFirst() != null && getFirst() instanceof Comparable)
            {
                result = ((Comparable) getFirst()).compareTo(toCompare.getFirst());
                if (result == 0 && getSecond() != null && getSecond() instanceof Comparable)
                {
                    result = ((Comparable) getSecond()).compareTo(toCompare.getSecond());
                }
            }
        }
        catch (Throwable ex)
        {
            LOG.debug("Caught an exception while comparing pairs.",
                      ex);
        }

        return result;
    }

    @Override
    public boolean equals(Object toEquals)
    {
        boolean result = false;
        if (toEquals instanceof Pair<?, ?>)
        {
            Pair<T1, T2> castedToEquals = (Pair<T1, T2>) toEquals;
            result = this.compareTo(castedToEquals) == 0;
        }
        return result;
    }

    public T1 getFirst()
    {
        return mFirst;
    }

    public void setFirst(T1 iFirst)
    {
        this.mFirst = iFirst;
    }

    public T2 getSecond()
    {
        return mSecond;
    }

    public void setSecond(T2 iSecond)
    {
        this.mSecond = iSecond;
    }
}
