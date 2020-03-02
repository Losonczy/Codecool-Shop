package com.codecool.shop.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoTest {
    private DataSource dataSource;

    public DaoTest(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(String test) {

        String query = "INSERT INTO table_name (column_name) VALUES ('"+test+"');";
        executeQuery(query);
    }
    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement()
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
