package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.CheckoutController;

import javax.sql.DataSource;
import java.sql.SQLException;

public interface CheckoutDao {


    void setDataSource(DataSource dataSource);

    void getDataSource(DataSource dataSource);

    void add(CheckoutController checkoutController) throws SQLException;
}
