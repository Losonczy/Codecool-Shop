package com.codecool.shop.dao;

import com.codecool.shop.SavedCart;
import com.codecool.shop.User;
import com.codecool.shop.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface RegisterDao {

    void add(User user) throws SQLException;

    User find(String username) throws SQLException;
    boolean Validate(User user) throws SQLException;

    void remove(int id) throws SQLException;
    public List<SavedCart> getCartByUser(String username) throws SQLException;
    public User getUserData(String username) throws SQLException;

    List<User> getAll() throws SQLException;
}
