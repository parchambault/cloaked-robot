package exchange.rmi.service;

import java.rmi.RemoteException;
import exchange.Message;

public interface ICommunication<T extends Message>
    extends IRmiService
{
    public void postMessage(T iSentMessage)
        throws RemoteException;

    public T getMessage()
        throws RemoteException;
}
