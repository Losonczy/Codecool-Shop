package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.CheckoutController;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CheckoutDao {


    void setDataSource(DataSource dataSource);

    void getDataSource(DataSource dataSource);

    void add(ArrayList<String> data) throws SQLException;

    void addToHistory(ArrayList<String> items) throws SQLException;
}
