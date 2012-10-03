package monitoring;

import gui.util.GlobalDefs;
import java.util.ArrayList;

public class Telemetry
{
    private ArrayList<TelemetryEntry> listEntries = new ArrayList<TelemetryEntry>();

    public void addTelemetry(String entryTag)
    {
        TelemetryEntry newTelemEntry = new TelemetryEntry(entryTag);
        listEntries.add(newTelemEntry);
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
          .append("Telemetry logging:\n");

        TelemetryEntry previousEntry = null;
        for (TelemetryEntry currentEntry : listEntries)
        {
            if (previousEntry == null)
            {
                sb.append(indent + indent)
                  .append(String.format("%1$6d - %2$s - %3$-1.50s",
                                        0l,
                                        currentEntry.getEntryTimeAsString(),
                                        currentEntry.getEntryDescription()));
            }
            else
            {
                sb.append(indent + indent)
                  .append(String.format("%1$6d - %2$s - %3$-1.50s",
                                        currentEntry.getEntryTime() - previousEntry.getEntryTime(),
                                        currentEntry.getEntryTimeAsString(),
                                        currentEntry.getEntryDescription()));
            }
            previousEntry = currentEntry;
            sb.append(GlobalDefs.END_OF_LINE);
        }
    }
}
