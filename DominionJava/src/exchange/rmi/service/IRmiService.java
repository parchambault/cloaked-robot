package exchange.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRmiService
    extends Remote
{
    public String getServiceName()
        throws RemoteException;
}
