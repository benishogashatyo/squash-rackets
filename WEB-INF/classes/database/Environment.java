package database;

public class Environment {

    static String getDBDriver() {
        return "com.mysql.jdbc.Driver";
    }

    static String getDBPath() {
        return "jdbc:mysql://localhost:3306/hew?characterEncoding=utf8&serverTimezone=JST";
    }

    static String getDBUser() {
        return "root";
    }

    static String getDBPassword() {
        return "";
    }
}
