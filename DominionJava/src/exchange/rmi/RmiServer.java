package exchange.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import exchange.rmi.service.IRmiService;

public class RmiServer
{
    private static final Logger LOG = LogManager.getLogger(RmiServer.class);

    private Registry            mRmiRegistry;
    private RmiConnectionInfo   mRmiServerInfo;

    public RmiServer(RmiConnectionInfo iServerConnectionInfo)
        throws RemoteException
    {
        mRmiServerInfo = iServerConnectionInfo;
        mRmiRegistry = LocateRegistry.createRegistry(iServerConnectionInfo.getRmiPort());
        LOG.info("Created RMI registry on port: " + iServerConnectionInfo.getRmiPort());
    }

    public RmiServer(RmiConnectionInfo iServerConnectionInfo,
                     IRmiService... iRmiServiceToRegister)
        throws RemoteException
    {
        this(iServerConnectionInfo);
        for (IRmiService rmiServiceEntry : iRmiServiceToRegister)
        {
            registerRmiService(rmiServiceEntry);
        }
    }

    public void registerRmiService(IRmiService iServiceToRegister)
        throws RemoteException
    {
        try
        {
            IRmiService wServiceStub = (IRmiService) UnicastRemoteObject.exportObject(iServiceToRegister,
                                                                                      0);
            mRmiRegistry.rebind(iServiceToRegister.getServiceName(),
                                wServiceStub);
            LOG.info("Registered RMI service: " + iServiceToRegister.getServiceName());
        }
        catch (Throwable ex)
        {
            LOG.error("Unable to register RMI service: " + iServiceToRegister.getServiceName(),
                      ex);
        }
    }

    public void unregisterRmiService(String iServiceName)
    {
        try
        {
            mRmiRegistry.unbind(iServiceName);
            LOG.info("Successfully unbound RMI service: " + iServiceName);
        }
        catch (Throwable ex)
        {
            LOG.error("Unable to unbind RMI service: " + iServiceName,
                      ex);
        }
    }

    public void stopServer()
    {
        try
        {
            for (String wRegisteredServiceNameEntry : mRmiRegistry.list())
            {
                unregisterRmiService(wRegisteredServiceNameEntry);
            }

            mRmiRegistry = null;
            LOG.info("Successfully stopped RMI server on port: " + mRmiServerInfo.getRmiPort());
        }
        catch (Throwable ex)
        {
            LOG.error("Unable to stopServer",
                      ex);
        }
    }
}
