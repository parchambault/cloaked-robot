package exchange;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import exchange.rmi.service.ICommunication;

public abstract class AbstractServer<T extends Message>
    implements ICommunication<T>
{
    private static final Logger LOG                       = LogManager.getLogger(AbstractServer.class);

    private Lock                mLock                     = new ReentrantLock();
    private Condition           mMessageReceivedCondition = mLock.newCondition();

    private LinkedList<T>       mMessageReceivedList      = new LinkedList<T>();

    @Override
    public T getMessage()
    {
        if (mMessageReceivedList.isEmpty())
        {
            try
            {
                mLock.lock();
                mMessageReceivedCondition.await();
            }
            catch (InterruptedException ex)
            {
                LOG.error("Interrupted exception!!",
                          ex);
            }
            finally
            {
                mLock.unlock();
            }
        }

        return mMessageReceivedList.removeFirst();
    }

    public void addMessageToQueue(T iMessage)
    {
        mMessageReceivedList.add(iMessage);

        try
        {
            mLock.lock();
            mMessageReceivedCondition.signalAll();
        }
        finally
        {
            mLock.unlock();
        }
    }
}
