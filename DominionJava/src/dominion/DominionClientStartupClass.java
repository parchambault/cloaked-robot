package dominion;

import common.startup.AbstractStartupClass;

public class DominionClientStartupClass
    extends AbstractStartupClass
{
    static
    {
        mClassNameStartup = DominionClientStartupClass.class.getName();
    }

    @Override
    public void startup()
    {
        DominionClient.getInstance();
    }
}
