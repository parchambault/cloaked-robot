package core.controller;

import java.rmi.RemoteException;
import java.util.HashMap;
import core.CoreServer;
import core.message.ChatPost;
import core.message.ChatPost.ChatMsgType;
import core.service.impl.ServerChatService;
import core.valueobj.PlayerInfo;

public class ChatController
{
    private HashMap<Integer, ServerChatService> mChatServiceMap = new HashMap<Integer, ServerChatService>();

    public void newClientRegistered(PlayerInfo iPlayerInfo)
        throws RemoteException
    {
        ServerChatService wChatService = new ServerChatService(iPlayerInfo);
        CoreServer.getInstance()
                  .getRmiServer()
                  .registerRmiService(wChatService);

        notifyUserOfNewPlayer(iPlayerInfo);

        notifyNewPlayerOfCurrentUser(wChatService);
        mChatServiceMap.put(iPlayerInfo.getPlayerId(),
                            wChatService);
    }

    public void clientLogout(PlayerInfo iPlayerInfo)
    {
        CoreServer.getInstance()
                  .getRmiServer()
                  .unregisterRmiService(ServerChatService.SERVICE_NAME + iPlayerInfo.getPlayerId());
        mChatServiceMap.remove(iPlayerInfo.getPlayerId());

        notifyUserOfPlayerLeft(iPlayerInfo);
    }

    public void messageReceived(ChatPost iMessageReceived)
    {
        for (ServerChatService wChatEntry : mChatServiceMap.values())
        {
            wChatEntry.addMessageToQueue(iMessageReceived);
        }
    }

    private void notifyUserOfNewPlayer(PlayerInfo iNewPlayer)
    {
        ChatPost wNewPlayerMsg = new ChatPost(ChatMsgType.PLAYER_JOIN);
        wNewPlayerMsg.setPlayerInfo(iNewPlayer);

        for (ServerChatService wChatEntry : mChatServiceMap.values())
        {
            wChatEntry.addMessageToQueue(wNewPlayerMsg);
        }
    }

    private void notifyUserOfPlayerLeft(PlayerInfo iLeavingPlayer)
    {
        ChatPost wPlayerLeftMsg = new ChatPost(ChatMsgType.PLAYER_LEFT);
        wPlayerLeftMsg.setPlayerInfo(iLeavingPlayer);

        for (ServerChatService wChatEntry : mChatServiceMap.values())
        {
            wChatEntry.addMessageToQueue(wPlayerLeftMsg);
        }
    }

    private void notifyNewPlayerOfCurrentUser(ServerChatService iNewPlayerChatService)
    {
        for (ServerChatService wChatEntry : mChatServiceMap.values())
        {
            ChatPost wCurrentPlayerMsg = new ChatPost(ChatMsgType.PLAYER_JOIN);
            wCurrentPlayerMsg.setPlayerInfo(wChatEntry.getPlayerInfo());

            iNewPlayerChatService.addMessageToQueue(wCurrentPlayerMsg);
        }
    }
}
