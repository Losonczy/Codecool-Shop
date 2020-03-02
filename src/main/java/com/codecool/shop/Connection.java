package com.codecool.shop;

import javax.sql.DataSource;
import java.sql.SQLException;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.postgresql.ds.PGSimpleDataSource;


public class Connection {

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        productCategoryDataStore.getDataSource(dataSource);

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
