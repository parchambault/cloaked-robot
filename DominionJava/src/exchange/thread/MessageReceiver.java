package exchange.thread;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import exchange.Message;
import exchange.listener.IMessageListener;
import exchange.rmi.service.ICommunication;

public class MessageReceiver
    implements Runnable
{
    private static final Logger LOG             = LogManager.getLogger(MessageReceiver.class);

    private ICommunication      mServerInterface;
    private IMessageListener    mMessageListener;

    private boolean             mIsShuttingDown = false;

    public MessageReceiver(ICommunication iServerInterface,
                           IMessageListener iMessageListener)
    {
        if (iServerInterface == null)
        {
            throw new IllegalArgumentException("Cannot specify a null ICommunication interface.");
        }
        if (iMessageListener == null)
        {
            LOG.warn("No message listener specified, message will be received and unprocessed!!");
        }
        mServerInterface = iServerInterface;
        mMessageListener = iMessageListener;
    }

    public void run()
    {
        while (!mIsShuttingDown)
        {
            try
            {
                Message wMessageReceived = mServerInterface.getMessage();
                mMessageListener.processMessage(wMessageReceived);
            }
            catch (Throwable ex)
            {
                LOG.error("Unable to getMessage.",
                          ex);
            }
        }
    }
}
