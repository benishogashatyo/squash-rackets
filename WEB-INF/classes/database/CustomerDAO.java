package database;

import static database.Environment.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.dto.CustomerDTO;

public class CustomerDAO {

    private Exception error;

    public Exception getError() {
        return error;
    }

    public void AddCustomer(List<CustomerDTO> dto) {
        Connection con = null;
        PreparedStatement prst = null;
        try {
            Class.forName(getDBDriver());
            con = DriverManager.getConnection(getDBPath(), getDBUser(), getDBPassword());
            for (CustomerDTO record : dto) {
                String query = "INSERT INTO client(id, name, birthdate, gender, like_weight, like_balance, like_hardness, like_maker) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
                prst = con.prepareStatement(query);
                prst.setInt(1, record.getUserID());
                prst.setString(2, record.getUserName());
                prst.setDate(3, record.getBirthdate());
                prst.setInt(4, record.getGender());
                prst.setInt(5, record.getRacket_weight());
                prst.setInt(6, record.getRacket_balance());
                prst.setInt(7, record.getHardness());
                prst.setInt(8, record.getManufacturer());
                prst.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException e) {
            this.error = e;
        } finally {
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

    public List<CustomerDTO>  GetCustomer() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName(getDBDriver());
            con = DriverManager.getConnection(getDBPath(), getDBUser(), getDBPassword());
            st = con.createStatement();
            String sql = " SELECT c.id AS id, c.name AS name, c.birthdate AS birthdate, c.gender AS gender, w.weight AS like_weight, b.balance AS like_balance, h.hardness AS like_hardness, m.name AS like_maker " +
                    "FROM client c " +
                    "INNER JOIN balance_category b ON c.like_balance = b.id " +
                    "INNER JOIN hardness_category h ON c.like_hardness = h.id " +
                    "INNER JOIN manufacturer_category m ON c.like_maker = m.id " +
                    "INNER JOIN weight_category w ON c.like_weight = w.id;";
            rs = st.executeQuery(sql);
            List<CustomerDTO> customerArray = new ArrayList<>();
            while (rs.next()) {
                var dto = new CustomerDTO();
                dto.setUserID(rs.getString("id"));
                dto.setUserName(rs.getString("name"));
                dto.setBirthdate(rs.getString("birthdate"));
                dto.setGender(rs.getString("gender"));
                dto.setRacket_weight(rs.getString("like_weight"));
                dto.setRacket_balance(rs.getString("like_balance"));
                dto.setHardness(rs.getString("like_hardness"));
                dto.setManufacturer(rs.getString("like_maker"));
                customerArray.add(dto);
            }
            return customerArray;
        } catch (ClassNotFoundException | SQLException e) {
            this.error = e;
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignored) {
                }
            }
            if (st != null) {
                try {
                    st.close();
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
