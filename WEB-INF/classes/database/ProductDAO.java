package database;
import static database.Environment.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.dto.ProductDTO;

public class ProductDAO {

    private Exception error;
    public Exception getError() {
        return error;
    }

    public List<ProductDTO> GetProducts() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName(getDBDriver());
            con = DriverManager.getConnection(getDBPath(), getDBUser(), getDBPassword());
            st = con.createStatement();
            String sql = "SELECT product_id AS id, product_name AS name, w.weight AS weight, b.balance AS balance, h.hardness AS hardness, m.name AS manufacturer, product_price AS price, product_img AS img, product_des AS description FROM products p " +
                    "INNER JOIN weight_category w ON w.id = p.product_weight " +
                    "INNER JOIN balance_category b ON b.id = p.product_balance " +
                    "INNER JOIN hardness_category h ON h.id = p.product_hardness " +
                    "INNER JOIN manufacturer_category m ON m.id = p.product_maker;";
            rs = st.executeQuery(sql);
            List<ProductDTO> productArray = new ArrayList<>();
            while (rs.next()) {
                var dto = new ProductDTO();
                dto.setID(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setWeight(rs.getString("weight"));
                dto.setBalance(rs.getString("balance"));
                dto.setHardness(rs.getString("hardness"));
                dto.setManufacturer(rs.getString("manufacturer"));
                dto.setPrice(rs.getInt("price"));
                dto.setImg(rs.getString("img"));
                dto.setDescription(rs.getString("description"));
                productArray.add(dto);
            }
            return productArray;
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
