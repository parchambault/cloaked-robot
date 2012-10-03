package core.service.impl;

import java.rmi.RemoteException;
import core.CoreServer;
import core.message.ChatPost;
import core.service.IChatService;
import core.valueobj.PlayerInfo;
import exchange.AbstractServer;

public class ServerChatService
    extends AbstractServer<ChatPost>
    implements IChatService<ChatPost>
{
    public static final String SERVICE_NAME = "ServerChatService-";

    private PlayerInfo         mPlayerInfo;

    public ServerChatService(PlayerInfo iPlayerInfo)
    {
        mPlayerInfo = iPlayerInfo;
    }

    @Override
    public String getServiceName()
        throws RemoteException
    {
        return SERVICE_NAME + mPlayerInfo.getPlayerId();
    }

    @Override
    public void postMessage(ChatPost iSentMessage)
    {
        CoreServer.getInstance()
                  .getChatController()
                  .messageReceived(iSentMessage);
    }

    public PlayerInfo getPlayerInfo()
    {
        return mPlayerInfo;
    }
}
