package exchange.rmi;

import java.io.Serializable;

public class RmiConnectionInfo
    implements Serializable
{
    private static final long serialVersionUID = 4283168424231962242L;

    private int               mRmiPort;
    private String            mRmiHost;
    private int               mRmiExportPort;

    public RmiConnectionInfo(int iPort,
                             String iHost)
    {
        mRmiPort = iPort;
        mRmiHost = iHost;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        toString(sb,
                 "");
        return sb.toString();
    }

    public void toString(StringBuilder sb,
                         String indent)
    {
        sb.append(indent)
          .append("Host: ")
          .append(getRmiHost())
          .append(":")
          .append(getRmiPort())
          .append(" ExportPort: ")
          .append(getRmiExportPort());
    }

    // ******************************
    // Getters / Setters
    // ******************************
    public int getRmiPort()
    {
        return mRmiPort;
    }

    public String getRmiHost()
    {
        return mRmiHost;
    }

    public int getRmiExportPort()
    {
        return mRmiExportPort;
    }

    public void setRmiExportPort(int iRmiExportPort)
    {
        this.mRmiExportPort = iRmiExportPort;
    }
}
