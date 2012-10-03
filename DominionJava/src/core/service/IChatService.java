package core.service;

import exchange.Message;
import exchange.rmi.service.ICommunication;

public interface IChatService<T extends Message>
    extends ICommunication<T>
{

}
