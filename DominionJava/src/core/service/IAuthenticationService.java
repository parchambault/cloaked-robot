package core.service;

import java.rmi.RemoteException;
import core.valueobj.LoginInfo;
import core.valueobj.PlayerInfo;
import exchange.rmi.service.IRmiService;

public interface IAuthenticationService
    extends IRmiService
{
    public PlayerInfo login(LoginInfo iClientInfo)
        throws RemoteException;

    public void logout(PlayerInfo iPlayerInfo)
        throws RemoteException;
}
