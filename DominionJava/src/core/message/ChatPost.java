package core.message;

import java.io.Serializable;
import core.valueobj.PlayerInfo;
import exchange.Message;

public class ChatPost
    extends Message
    implements Serializable
{
    private static final long serialVersionUID = 6019430266875136599L;

    public enum ChatMsgType
    {
        POST_MESSAGE,
        PLAYER_JOIN,
        PLAYER_LEFT,
    }

    private ChatMsgType mMsgType;
    private String      mChatMessage;
    private PlayerInfo  mPlayerInfo;

    public ChatPost(ChatMsgType iMsgType)
    {
        mMsgType = iMsgType;
    }

    public ChatMsgType getType()
    {
        return mMsgType;
    }

    public String getChatMessage()
    {
        return mChatMessage;
    }

    public void setChatMessage(String iChatMessage)
    {
        mChatMessage = iChatMessage;
    }

    public PlayerInfo getPlayerInfo()
    {
        return mPlayerInfo;
    }

    public void setPlayerInfo(PlayerInfo iPlayerInfo)
    {
        this.mPlayerInfo = iPlayerInfo;
    }
}
