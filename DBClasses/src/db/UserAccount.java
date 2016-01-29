package db;

import java.io.Serializable;

/**
 *
 * @author mahmoud_fouad
 */
public class UserAccount implements Serializable {

    public static final int UNAVILABLE = 0;
    public static final int BUSY = 1;
    public static final int AVAILABLE = 2;
    public static final int HIDDEN = 3;

    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private int status;

    public UserAccount() {
    }

    public UserAccount(UserAccount account) {
        this.id = account.id;
        this.firstName = account.firstName;
        this.lastName = account.lastName;
        this.userName = account.userName;
        this.password = account.password;
        this.email = account.email;
        this.status = account.status;
    }

    public UserAccount(int id, String firstName, String lastName,
            String userName, String password, String email, int status) {
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserAccount) {
            return id == ((UserAccount) obj).id;
        }
        return false;
    }

    @Override
    public String toString() {
        return id + "";
    }

}
