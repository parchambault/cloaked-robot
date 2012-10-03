package core.service;

import core.message.ChatPost;
import core.service.impl.ServerChatService;
import core.valueobj.PlayerInfo;
import exchange.AbstractClient;
import exchange.listener.IMessageListener;

public class ClientChatService
    extends AbstractClient<ChatPost>
{
    private static final String        CLIENT_CHAT_SERVICE_NAME = "ClientChatMsgReceiver-";

    private PlayerInfo                 mPlayerInfo;
    private IMessageListener<ChatPost> mMessageProcessor;

    public ClientChatService(PlayerInfo iPlayerInfo,
                             IMessageListener<ChatPost> iMessageListener)
    {
        mPlayerInfo = iPlayerInfo;
        mMessageProcessor = iMessageListener;
    }

    @Override
    protected String getRemoteCommunicationInterfaceName()
    {
        return ServerChatService.SERVICE_NAME + mPlayerInfo.getPlayerId();
    }

    @Override
    protected String getThreadReceiverUniqueId()
    {
        return CLIENT_CHAT_SERVICE_NAME + mPlayerInfo.getPlayerId();
    }

    @Override
    public void processMessage(ChatPost iMessageToProcess)
    {
        mMessageProcessor.processMessage(iMessageToProcess);
    }

    public PlayerInfo getPlayerInfo()
    {
        return mPlayerInfo;
    }
}
