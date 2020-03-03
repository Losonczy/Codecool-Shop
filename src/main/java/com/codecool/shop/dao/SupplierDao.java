package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public interface SupplierDao {

    void getDataSource(DataSource dataSource);

    void add(Supplier supplier) throws SQLException;
    Supplier find(int id) throws SQLException;
    void remove(int id) throws SQLException;

    List<Supplier> getAll() throws SQLException;
}
