package db;

import java.io.Serializable;

/**
 *
 * @author mahmoud_fouad
 */
public class UserAccount implements Serializable {

    transient public static final int UNAVILABLE = 0;
    transient public static final int BUSY = 1;
    transient public static final int AVAILABLE = 2;
    transient public static final int HIDDEN = 3;

    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private int status;

    public UserAccount() {
    }

    public UserAccount(int id, String firstName, String lastName, String userName, String password, String email, int status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
