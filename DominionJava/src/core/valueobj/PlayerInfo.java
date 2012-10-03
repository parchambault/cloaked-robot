package core.valueobj;

import java.io.Serializable;

public class PlayerInfo
    implements Serializable
{
    private static final long serialVersionUID = 1764924476844378539L;

    private int               mPlayerId;
    private String            mPlayerName;

    public PlayerInfo(int iPlayerId,
                      String iPlayerName)
    {
        mPlayerId = iPlayerId;
        mPlayerName = iPlayerName;
    }

    public int getPlayerId()
    {
        return mPlayerId;
    }

    public String getPlayerName()
    {
        return mPlayerName;
    }

    @Override
    public String toString()
    {
        return mPlayerName;
    }
}
