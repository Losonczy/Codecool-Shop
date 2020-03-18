package com.codecool.shop;

import javax.sql.DataSource;
import java.sql.SQLException;

import com.codecool.shop.dao.implementation.CheckoutDao;
import com.codecool.shop.dao.implementation.CheckoutDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import org.postgresql.ds.PGSimpleDataSource;

public class Connection {

    public void setup() throws SQLException {
        DataSource dataSource = connect();

        ProductDaoMem productDaoMem= ProductDaoMem.getInstance();
        productDaoMem.setDataSource(dataSource);

        SupplierDaoMem supplierDaoMem = SupplierDaoMem.getInstance();
        supplierDaoMem.setDataSource(dataSource);
        CheckoutDaoMem checkoutDaoMem = CheckoutDaoMem.getInstance();
        checkoutDaoMem.setDataSource(dataSource);
        System.out.println(supplierDaoMem.getAll());
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
