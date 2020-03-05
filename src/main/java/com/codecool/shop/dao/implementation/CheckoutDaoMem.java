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
    public void add(ArrayList<String>data ) throws SQLException {
        String get = "INSERT INTO personal_data(user_id,full_name,email,address,city,zip) VALUES(?,?,?,?,?,?);";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(get);

        stmt.setInt(1,1);
        stmt.setString(2,data.get(0));
        stmt.setString(3,data.get(1));
        stmt.setString(4,data.get(2));
        stmt.setString(5,data.get(3));
        stmt.setInt(6,Integer.parseInt(data.get(4)));

        stmt.executeUpdate();




    }

}
