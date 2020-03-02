package com.codecool.shop;

import javax.sql.DataSource;
import java.sql.SQLException;
import org.postgresql.ds.PGSimpleDataSource;


public class Connection {

    public void setup() throws SQLException {
        DataSource dataSource = connect();
//        DaoTest daoTest = new DaoTest(dataSource);

    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setDatabaseName("webshop");
        dataSource.setUser("evelin");
        dataSource.setPassword("95Marcius");

        dataSource.getConnection().close();

        return dataSource;
    }
}
