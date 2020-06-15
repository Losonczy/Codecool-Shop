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

        dataSource.setServerName("ec2-34-230-149-169.compute-1.amazonaws.com");
        dataSource.setDatabaseName("dcfjfrqkb9q88o");
        dataSource.setUser("vmlhkzbwzzdknw");
        dataSource.setPassword("ab9f09e8ea2ff66a196fdb81657efb84bb122bd94d98f3875b678c5c106370f9");

        dataSource.getConnection().close();

        return dataSource;
    }
}
