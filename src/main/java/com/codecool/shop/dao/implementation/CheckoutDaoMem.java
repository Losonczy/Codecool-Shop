package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.CheckoutController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class CheckoutDaoMem implements CheckoutDao {
    private static CheckoutDaoMem instance = null;
    DataSource dataSource;

    CheckoutDaoMem() {
    }

    public static CheckoutDaoMem getInstance() {
        if (CheckoutDaoMem.instance == null) {
            CheckoutDaoMem.instance = new CheckoutDaoMem();
        }
        return instance;
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void getDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(CheckoutController checkoutController) throws SQLException {
        String get = "INSERT INTO personal_data(full_name,email,address,city,zip) VALUES(?,?,?,?,?,?,?);";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(get);



    }

}
