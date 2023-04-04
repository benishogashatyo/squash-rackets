package database;
import java.sql.*;
import static database.Environment.*;

public class AuthenticateDAO {

    private String userID;
    private Exception error;

    public String getUserID() {
        return userID;
    }

    public Exception getError() {
        return error;
    }

    public AuthenticateDAO(String userID, String password){
        Connection con = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            Class.forName(getDBDriver());
            con = DriverManager.getConnection(getDBPath(), getDBUser(), getDBPassword());
            String query = "SELECT id AS ID FROM login WHERE id = ? AND password = ?;";
            prst = con.prepareStatement(query);
            prst.setString(1, userID);
            prst.setString(2, password);
            rs = prst.executeQuery();
            if (rs.next()) {
                this.userID = rs.getString("ID");
            }
        } catch (ClassNotFoundException | SQLException e) {
            this.error = e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignored) {
                }
            }
            if (prst != null) {
                try {
                    prst.close();
                } catch (SQLException ignored) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }
}
