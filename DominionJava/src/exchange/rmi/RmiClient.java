package exchange.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import exchange.rmi.exception.ServiceNotFoundException;
import exchange.rmi.service.IRmiService;

public class RmiClient
{
    private static final Logger LOG = LogManager.getLogger(RmiClient.class);

    private Registry            mRemoteRegistry;
    private RmiConnectionInfo   mRemoteServerInfo;

    public RmiClient(RmiConnectionInfo iRemoteConnectionInfo)
        throws RemoteException
    {
        mRemoteServerInfo = iRemoteConnectionInfo;
        mRemoteRegistry = LocateRegistry.getRegistry(iRemoteConnectionInfo.getRmiHost(),
                                                     iRemoteConnectionInfo.getRmiPort());
    }

    public IRmiService getRemoteService(String iServiceName)
        throws ServiceNotFoundException
    {
        try
        {
            for (String wEntry : mRemoteRegistry.list())
            {
                LOG.debug("rmiClient registry list: " + wEntry);
            }
            return (IRmiService) mRemoteRegistry.lookup(iServiceName);
        }
        catch (Throwable ex)
        {
            throw new ServiceNotFoundException("Unable to locate RMI service: " + iServiceName + " on RMI server: "
                                                       + mRemoteServerInfo.getRmiHost() + ":"
                                                       + mRemoteServerInfo.getRmiPort(),
                                               ex);
        }
    }
}
