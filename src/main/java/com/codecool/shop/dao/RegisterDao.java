package com.codecool.shop.dao;

import com.codecool.shop.User;
import com.codecool.shop.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface RegisterDao {

    void add(User user) throws SQLException;

    User find(int id) throws SQLException;
    boolean Validate(User user) throws SQLException;

    void remove(int id) throws SQLException;

    List<User> getAll() throws SQLException;
}
