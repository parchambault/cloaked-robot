package core;

import java.rmi.RemoteException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import core.controller.ChatController;
import core.service.impl.AuthenticationService;
import exchange.rmi.RmiConnectionInfo;
import exchange.rmi.RmiServer;

public class CoreServer
{
    private static final Logger   LOG                   = LogManager.getLogger(CoreServer.class);

    public static CoreServer      mSingletonInstance;

    private RmiServer             mRmiServer;
    private RmiConnectionInfo     mServerConnectionInfo;

    // service list
    private AuthenticationService mAuthenticationServer = new AuthenticationService();

    // controller list
    private ChatController        mChatController       = new ChatController();

    private CoreServer()
    {
    }

    public static CoreServer getInstance()
    {
        if (mSingletonInstance == null)
        {
            LOG.debug("Creating singleton instance of CoreServer.");
            mSingletonInstance = new CoreServer();
            mSingletonInstance.initialize();
        }
        return mSingletonInstance;
    }

    private void initialize()
    {
    }

    public void startServer(RmiConnectionInfo iServerConnectionInfo)
        throws RemoteException
    {
        mServerConnectionInfo = iServerConnectionInfo;
        mRmiServer = new RmiServer(mServerConnectionInfo,
                                   mAuthenticationServer);
    }

    public void stopServer()
    {
        if (mRmiServer != null)
        {
            mRmiServer.stopServer();
        }
    }

    public RmiServer getRmiServer()
    {
        return mRmiServer;
    }

    public AuthenticationService getAuthenticationServer()
    {
        return mAuthenticationServer;
    }

    public ChatController getChatController()
    {
        return mChatController;
    }
}
