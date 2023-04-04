package database;

import java.sql.*;

import static database.Environment.*;
import static database.Environment.getDBPassword;

public class EmployeeDAO {

    private String name;
    private Date birthdate;
    private String address;
    private String tel;
    private Exception error;

    public String getName() {
        return name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getAddress() {
        return address;
    }

    public String getTel() {
        return tel;
    }

    public Exception getError() {
        return error;
    }

    public EmployeeDAO(String userID){
        Connection con = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            Class.forName(getDBDriver());
            con = DriverManager.getConnection(getDBPath(), getDBUser(), getDBPassword());
            String query = "SELECT name, birthdate, address, tel FROM employee WHERE id = ?;";
            prst = con.prepareStatement(query);
            prst.setString(1, userID);
            rs = prst.executeQuery();
            if (rs.next()) {
                this.name = rs.getString("name");
                this.birthdate = rs.getDate("birthdate");
                this.address = rs.getString("address");
                this.tel = rs.getString("tel");
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
