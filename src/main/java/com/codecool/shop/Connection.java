package com.codecool.shop;

import javax.sql.DataSource;
import java.sql.SQLException;

import com.codecool.shop.dao.RegisterDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import com.codecool.shop.dao.SupplierDao;
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
        RegisterDaoMem register = RegisterDaoMem.getINSTANCE();
        register.setDataSource(dataSource);
        System.out.println(supplierDaoMem.getAll());
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setServerName("ec2-52-87-135-240.compute-1.amazonaws.com");
        dataSource.setDatabaseName("d5ij0761tv0sti");
        dataSource.setUser("lpbvggrhcdpjtk");
        dataSource.setPassword("e47973e06ea0f1f9eb915ff42d66ad93889f678b9a1e0eaeef07b205dc07fe5b");

        dataSource.getConnection().close();

        return dataSource;
    }
}
