package monitoring;

import java.sql.Timestamp;

public class TelemetryEntry
{
    private String entryDescription;
    private long   entryTime;

    public String getEntryTimeAsString()
    {
        return new Timestamp(getEntryTime()).toString();
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        toString("",
                 sb);

        return sb.toString();
    }

    public void toString(String indent,
                         StringBuilder sb)
    {
        sb.append(indent)
          .append(entryDescription)
          .append(new Timestamp(entryTime).toString());
    }

    public TelemetryEntry(String description)
    {
        entryTime = System.currentTimeMillis();
        entryDescription = description;
    }

    public String getEntryDescription()
    {
        return entryDescription;
    }

    public long getEntryTime()
    {
        return entryTime;
    }
}
