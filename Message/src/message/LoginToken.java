package message;

import java.io.Serializable;

/**
 *
 * @author mahmoud_fouad
 */
public class LoginToken implements Serializable {

    private String userName;
    private String password;

    public LoginToken() {
    }

    public LoginToken(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
