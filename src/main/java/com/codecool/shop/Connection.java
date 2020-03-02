package com.codecool.shop;

import javax.sql.DataSource;
import java.sql.SQLException;

import com.codecool.shop.dao.DaoTest;
import org.postgresql.ds.PGSimpleDataSource;


public class Connection {

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        DaoTest daoTest = new DaoTest(dataSource);
        //daoTest.add("engem");
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setDatabaseName("webshop");
        dataSource.setUser("losonczy");
        dataSource.setPassword("1234");

        dataSource.getConnection().close();

        return dataSource;
    }
}
