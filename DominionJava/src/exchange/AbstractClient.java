package exchange;

import java.rmi.RemoteException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import exchange.listener.IMessageListener;
import exchange.rmi.RmiClient;
import exchange.rmi.RmiConnectionInfo;
import exchange.rmi.exception.ServiceNotFoundException;
import exchange.rmi.service.ICommunication;
import exchange.thread.MessageReceiver;

public abstract class AbstractClient<T extends Message>
    implements IMessageListener<T>
{
    private static final Logger LOG = LogManager.getLogger(AbstractClient.class);

    private ICommunication<T>   mServerInterface;
    private Thread              mMsgReceivingThread;

    public AbstractClient()
    {
    }

    public final void startExchange(RmiConnectionInfo iRmiCommunicationServerInfo)
        throws RemoteException, ServiceNotFoundException
    {
        RmiClient wRmiClient = new RmiClient(iRmiCommunicationServerInfo);
        mServerInterface = (ICommunication<T>) wRmiClient.getRemoteService(getRemoteCommunicationInterfaceName());

        MessageReceiver wMessageReceiver = new MessageReceiver(mServerInterface,
                                                               this);
        mMsgReceivingThread = new Thread(wMessageReceiver,
                                         getThreadReceiverUniqueId());
        mMsgReceivingThread.setDaemon(true);
        mMsgReceivingThread.start();
        LOG.info("started thread: " + getThreadReceiverUniqueId());
    }

    public final void sendMessage(T iMessage)
        throws Exception
    {
        if (mServerInterface == null)
        {
            throw new Exception("Exchange client isn't started!");
        }
        mServerInterface.postMessage(iMessage);
    }

    @Override
    public abstract void processMessage(T iMessageToProcess);

    /**
     * @return {@link String} - The implementer must return the specific interface name to connect to on the Server.
     */
    protected abstract String getRemoteCommunicationInterfaceName();

    /**
     * @return {@link String} - The implementer must return the receiving thread Id. This should be unique as it is the
     *         name we'll see when debugging.
     */
    protected abstract String getThreadReceiverUniqueId();
}
