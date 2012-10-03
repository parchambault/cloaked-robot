package core.valueobj;

import java.io.Serializable;

public class LoginInfo
    implements Serializable
{
    private static final long serialVersionUID = -4445601549660683128L;

    private String            mUsername;
    private String            mPassword;

    public LoginInfo(String iUsername,
                     String iPassword)
    {
        mUsername = iUsername;
        // TODO : add some kind of encryption to the password so it isn't sent plain text
        mPassword = iPassword;
    }

    public String getUsername()
    {
        return mUsername;
    }

    public String getPassword()
    {
        return mPassword;
    }
}
