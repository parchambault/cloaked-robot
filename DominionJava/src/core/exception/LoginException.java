package core.exception;

import java.rmi.RemoteException;

public class LoginException
    extends RemoteException
{
    private static final long serialVersionUID = 8338247135520890478L;

    public LoginException()
    {
        super();
    };

    public LoginException(String iErrorMessage)
    {
        super(iErrorMessage);
    };

    public LoginException(String iErrorMessage,
                          Throwable iCause)
    {
        super(iErrorMessage,
              iCause);
    };
}
