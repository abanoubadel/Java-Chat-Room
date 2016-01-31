package handlers;

import db.UserAccount;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import message.LoginToken;
import org.postgresql.Driver;

/**
 *
 * @author mahmoud_fouad
 */
public class DBHandler {

    private Connection connection;
    private PreparedStatement loginCheck;
    private PreparedStatement createUser;
    private PreparedStatement updateUser;
    private PreparedStatement updateUserState;
    private PreparedStatement contactList;
    private PreparedStatement allUsers;
    private PreparedStatement validateEmail;
    private PreparedStatement validateUserName;

    public DBHandler() throws SQLException {
        DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());

        connection = DriverManager.getConnection("jdbc:mysql://localhost/Chat", "root", "");

        loginCheck = connection.prepareStatement("SELECT * FROM account WHERE user_name = ? and password = ? ;");
        createUser = connection.prepareStatement("INSERT INTO account (first_name, last_name, user_name, password, email, status) VALUES (?, ?, ?, ?, ?, ?) ");
        updateUser = connection.prepareStatement("UPDATE account SET first_name = ? , last_name = ? , password = ? , email = ? WHERE account_id = ? ;");
        updateUserState = connection.prepareStatement("UPDATE account SET status = ? WHERE account_id = ? ;");
        allUsers = connection.prepareStatement("SELECT * FROM account ORDER BY status , first_name");
        contactList = connection.prepareStatement("SELECT * FROM account WHERE account_id <> ? ORDER BY status , first_name");
        validateEmail = connection.prepareStatement("SELECT email FROM account WHERE email = ?");
        validateUserName = connection.prepareStatement("SELECT * FROM account WHERE user_name = ?");

    }

    public UserAccount login(LoginToken token) {
        try {
            loginCheck.setString(1, token.getUserName());
            loginCheck.setString(2, token.getPassword());

            ArrayList<UserAccount> data = generateList(loginCheck.executeQuery());

            if (data.isEmpty()) {
                return null;
            } else {
                return data.get(0);
            }

        } catch (SQLException ex) {
            return null;
        }
    }

    public boolean createUser(UserAccount account) {
        try {
            createUser.setString(1, account.getFirstName());
            createUser.setString(2, account.getLastName());
            createUser.setString(3, account.getUserName());
            createUser.setString(4, account.getPassword());
            createUser.setString(5, account.getEmail());
            createUser.setInt(6, UserAccount.AVAILABLE);

            if (createUser.executeUpdate() == 0) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean updateUser(UserAccount account) {
        try {
            updateUser.setString(1, account.getFirstName());
            updateUser.setString(2, account.getLastName());
            updateUser.setString(3, account.getPassword());
            updateUser.setString(4, account.getEmail());
            updateUser.setInt(5, account.getId());

            if (createUser.executeUpdate() == 0) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean updateUserStatus(UserAccount account) {
        try {
            updateUserState.setInt(1, account.getStatus());
            updateUserState.setInt(2, account.getId());

            if (updateUserState.executeUpdate() == 0) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<UserAccount> getContactList(UserAccount account) {
        try {
            contactList.setInt(1, account.getId());
            return generateList(contactList.executeQuery());
        } catch (SQLException ex) {
            return null;
        }
    }

    public ArrayList<UserAccount> getAllContacts() {
        try {
            return generateList(allUsers.executeQuery());
        } catch (SQLException ex) {
            return null;
        }
    }

    private ArrayList<UserAccount> generateList(ResultSet rs) throws SQLException {
        if (rs != null) {
            ArrayList<UserAccount> data = new ArrayList<>();

            while (rs.next()) {
                data.add(new UserAccount(rs.getInt("account_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getInt("status")));
            }

            return data;
        }

        return null;
    }

    public boolean validateMail(String email) {
        try {
            validateEmail.setString(1, email);

            ArrayList<UserAccount> data = generateList(validateEmail.executeQuery());

            if (data.isEmpty()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean validateUserName(String username) {
        try {
            validateUserName.setString(1, username);

            ArrayList<UserAccount> data = generateList(validateUserName.executeQuery());

            if (data.isEmpty()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            return false;
        }
    }

}
