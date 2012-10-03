package core.service.impl;

import java.rmi.RemoteException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import core.CoreServer;
import core.exception.LoginException;
import core.service.IAuthenticationService;
import core.valueobj.LoginInfo;
import core.valueobj.PlayerInfo;

public class AuthenticationService
    implements IAuthenticationService
{
    private static final Logger LOG            = LogManager.getLogger(AuthenticationService.class);
    public static final String  SERVICE_NAME   = "AuthenticationService";

    private static int          mClientCounter = 1;

    @Override
    public PlayerInfo login(LoginInfo iClientInfo)
        throws LoginException
    {
        LOG.info("Client info: " + iClientInfo.getUsername() + " is logging in.");

        try
        {
            // TODO : add database validation and throw LoginException if user isn't authenticated
            PlayerInfo wPlayerInfo = new PlayerInfo(mClientCounter++,
                                                    iClientInfo.getUsername());
            CoreServer.getInstance()
                      .getChatController()
                      .newClientRegistered(wPlayerInfo);
            LOG.info("Client info: " + iClientInfo.getUsername() + " successfully logged in as playerId: "
                    + wPlayerInfo.getPlayerId());
            return wPlayerInfo;
        }
        catch (Throwable ex)
        {
            LOG.error("Unable to login client info: " + iClientInfo.getUsername(),
                      ex);
            throw new LoginException("Unable to login",
                                     ex);
        }
    }

    @Override
    public String getServiceName()
        throws RemoteException
    {
        return SERVICE_NAME;
    }

    @Override
    public void logout(PlayerInfo iPlayerInfo)
    {
        CoreServer.getInstance()
                  .getChatController()
                  .clientLogout(iPlayerInfo);
        LOG.info("Player: " + iPlayerInfo.getPlayerName() + " id: " + iPlayerInfo.getPlayerId()
                + " successfully logged out.");
    }
}
