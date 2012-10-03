package rmi.service.definition;

import java.rmi.RemoteException;
import exchange.listener.IMessageListener;
import exchange.rmi.RmiConnectionInfo;
import exchange.rmi.service.IRmiService;

public interface IChatService
    extends IRmiService
{
    public void postMessage(String message,
                            boolean isLocalMessage)
        throws RemoteException;

    public void registerChatService(RmiConnectionInfo peerClientInfo)
        throws RemoteException;

    public void registerMessageListener(IMessageListener messageListener)
        throws RemoteException;

    public void unRegisterMessageListener(IMessageListener messageListener)
        throws RemoteException;
}
