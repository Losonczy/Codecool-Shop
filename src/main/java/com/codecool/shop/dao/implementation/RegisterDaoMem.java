package com.codecool.shop.dao.implementation;

import com.codecool.shop.User;
import com.codecool.shop.dao.RegisterDao;
import com.codecool.shop.model.Product;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterDaoMem implements RegisterDao {
    private DataSource dataSource;
    private static RegisterDaoMem INSTANCE = null;

    private RegisterDaoMem() {

    }

    public static RegisterDaoMem getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new RegisterDaoMem();

        }
        return INSTANCE;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(User user) throws SQLException {
        String qr = "INSERT INTO users(id,username,password) VALUES (DEFAULT,?,?) RETURNING id";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        ResultSet rs = stmt.executeQuery();
        rs.next();
        user.setId(rs.getInt(1));

    }

    @Override
    public User find(int id) throws SQLException {
        String qr = "SELECT * FROM user WHERE id=?";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);
        stmt.setInt(1, id);

        ResultSet res = stmt.executeQuery();

        while (res.next()) {
            User user = new User(res.getString("username"), res.getString("password"));
            return user;
        }
        return null;
    }

    @Override
    public boolean Validate(User user) throws SQLException {
        String qr = "SELECT * FROM user WHERE username=? AND password=?";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());

        ResultSet res = stmt.executeQuery();
        if(!res.next()){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public void remove(int id) throws SQLException {
        String qr = "DELETE FROM user WHERE id=?";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);
        stmt.setInt(1, id);
        stmt.executeUpdate();

    }

    @Override
    public List<User> getAll() throws SQLException {

        List<User> allUsers = new ArrayList<>();
        String qr = "SELECT * FROM user";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);
        ResultSet res = stmt.executeQuery();
        while (res.next()) {
            User user = new User(res.getString("username"), res.getString("password"));
            allUsers.add(user);
        }

        return allUsers;
    }
}
