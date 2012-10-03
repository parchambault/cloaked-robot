package exchange.rmi.exception;

public class ServiceNotFoundException
    extends Exception
{
    private static final long serialVersionUID = 8338247135520890478L;

    public ServiceNotFoundException()
    {
        super();
    };

    public ServiceNotFoundException(String iErrorMessage)
    {
        super(iErrorMessage);
    };

    public ServiceNotFoundException(String iErrorMessage,
                                    Throwable iCause)
    {
        super(iErrorMessage,
              iCause);
    };
}
