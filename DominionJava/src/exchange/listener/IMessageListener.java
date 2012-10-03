package exchange.listener;

import exchange.Message;

public interface IMessageListener<T extends Message>
{
    public void processMessage(T iMessageReceived);
}
