package database;

import static database.Environment.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.dto.BalanceCategoryDTO;
import database.dto.HardnessCategoryDTO;
import database.dto.ManufacturerCategoryDTO;
import database.dto.WeightCategoryDTO;

public class CategoryDAO {

    private List<WeightCategoryDTO> weightArray;
    private List<BalanceCategoryDTO> balanceArray;
    private List<HardnessCategoryDTO> hardnessArray;
    private List<ManufacturerCategoryDTO> manufacturerArray;
    private Exception error;

    public CategoryDAO(){
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName(getDBDriver());
            con = DriverManager.getConnection(getDBPath(), getDBUser(), getDBPassword());
            st = con.createStatement();
            String sql = "SELECT id AS ID, weight AS weight FROM weight_category;";
            rs = st.executeQuery(sql);
            List<WeightCategoryDTO> weightArray = new ArrayList<>();
            while (rs.next()) {
                var dto = new WeightCategoryDTO();
                dto.setId(rs.getInt("ID"));
                dto.setWeight(rs.getString("weight"));
                weightArray.add(dto);
            }
            this.weightArray = weightArray;
            sql = "SELECT id AS ID, balance AS balance FROM balance_category;";
            rs = st.executeQuery(sql);
            List<BalanceCategoryDTO> balanceArray = new ArrayList<>();
            while (rs.next()) {
                var dto = new BalanceCategoryDTO();
                dto.setId(rs.getInt("ID"));
                dto.setBalance(rs.getString("balance"));
                balanceArray.add(dto);
            }
            this.balanceArray = balanceArray;
            sql = "SELECT id AS ID, hardness AS hardness FROM hardness_category;";
            rs = st.executeQuery(sql);
            List<HardnessCategoryDTO> hardnessArray = new ArrayList<>();
            while (rs.next()) {
                var dto = new HardnessCategoryDTO();
                dto.setId(rs.getInt("ID"));
                dto.setHardness(rs.getString("hardness"));
                hardnessArray.add(dto);
            }
            this.hardnessArray = hardnessArray;
            sql = "SELECT id AS ID, name AS name FROM manufacturer_category ;";
            rs = st.executeQuery(sql);
            List<ManufacturerCategoryDTO> manufacturerArray = new ArrayList<>();
            while (rs.next()) {
                var dto = new ManufacturerCategoryDTO();
                dto.setId(rs.getInt("ID"));
                dto.setName(rs.getString("name"));
                manufacturerArray.add(dto);
            }
            this.manufacturerArray = manufacturerArray;
        } catch (ClassNotFoundException | SQLException e) {
            this.error = e;
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

    public List<WeightCategoryDTO> getWeightArray() {
        return weightArray;
    }

    public List<BalanceCategoryDTO> getBalanceArray() {
        return balanceArray;
    }

    public List<HardnessCategoryDTO> getHardnessArray() {
        return hardnessArray;
    }

    public List<ManufacturerCategoryDTO> getManufacturerArray() {
        return manufacturerArray;
    }

    public Exception getError() {
        return error;
    }
}
