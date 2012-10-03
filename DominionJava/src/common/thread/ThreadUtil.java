//package common.thread;
//
//public class ThreadUtil
//{
//    private ThreadUtil()
//    {
//    };
//
//    public static Thread getThread(final String name)
//    {
//        ThreadGroup root = Thread.currentThread().getThreadGroup().getParent();
//        while (root.getParent() != null) {
//            root = root.getParent();
//        }
//        
//        if (name == null)
//        {
//            throw new NullPointerException("Null name");
//        }
//
//        final Thread[] threads = Thread. getAllThreads();
//        for (Thread thread : threads)
//        {
//            if (thread.getName()
//                      .equals(name))
//            {
//                return thread;
//            }
//        }
//        return null;
//    }
// }
