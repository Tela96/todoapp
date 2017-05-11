package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHiDao implements HiDao {

    private final Connection conn;

    public DatabaseHiDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public String getMsg(String lang) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = String.format("SELECT `msg` FROM `hi` WHERE `lang` = '%s'", lang);
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            String msg = resultSet.getString("msg");
            if (resultSet.next()) {
                throw new IllegalStateException();
            }
            return msg;
        }
        throw new IllegalStateException();
    }
}
