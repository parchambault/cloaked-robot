package common.startup;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class AbstractStartupClass
{
    private static final Logger LOG = LogManager.getLogger(AbstractStartupClass.class);
    protected static String     mClassNameStartup;

    public abstract void startup()
        throws Exception;

    // TODO : sand-box implementation, have to be reviewed/rewritten
    private void logStartingArguments(String[] args)
    {
        // String indent = "    ";
        // StringBuilder sb = new StringBuilder();
        // sb.append("Application starting properties:")
        // .append(GlobalDefs.END_OF_LINE);
        // for (Entry<Object, Object> propertyEntry : System.getProperties()
        // .entrySet())
        // {
        // sb.append(indent)
        // .append(String.format("%1$-30s - %2$s",
        // propertyEntry.getKey(),
        // propertyEntry.getValue()))
        // .append(GlobalDefs.END_OF_LINE);
        // }
        //
        // sb.append("Application arguments:")
        // .append(GlobalDefs.END_OF_LINE);
        // for (String applicationArgEntry : args)
        // {
        // sb.append(indent)
        // .append(String.format("%1$-30s - %2$s",
        // applicationArgEntry,
        // ""))
        // .append(GlobalDefs.END_OF_LINE);
        // }
        //
        // sb.append("Application environment:")
        // .append(GlobalDefs.END_OF_LINE);
        // for (Entry<String, String> environmentEntry : System.getenv()
        // .entrySet())
        // {
        // sb.append(indent)
        // .append(String.format("%1$-30s - %2$s",
        // environmentEntry.getKey(),
        // environmentEntry.getValue()))
        // .append(GlobalDefs.END_OF_LINE);
        // }
        //
        // LOG.info(sb.toString());
    }

    public static void main(String[] args)
    {
        try
        {
            AbstractStartupClass startupClass = (AbstractStartupClass) ClassLoader.getSystemClassLoader()
                                                                                  .loadClass(mClassNameStartup)
                                                                                  .newInstance();
            startupClass.logStartingArguments(args);
            startupClass.startup();
        }
        catch (Throwable ex)
        {
            LOG.fatal("Unable to start application.",
                      ex);
        }
    }
}
