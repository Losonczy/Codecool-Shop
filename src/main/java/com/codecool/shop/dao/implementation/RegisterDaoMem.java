package com.codecool.shop.dao.implementation;

import com.codecool.shop.SavedCart;
import com.codecool.shop.User;
import com.codecool.shop.dao.RegisterDao;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterDaoMem implements RegisterDao {
    private DataSource dataSource;
    private static RegisterDaoMem INSTANCE = null;
    ProductCategoryDaoMem product = ProductCategoryDaoMem.getInstance();

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
        String qr = "INSERT INTO users(id,username,password) VALUES (DEFAULT,?,?)";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.executeUpdate();

    }

    @Override
    public User find(String username) throws SQLException {
        String qr = "SELECT * FROM users WHERE username=?";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);
        stmt.setString(1, username);

        ResultSet res = stmt.executeQuery();

        while (res.next()) {
            User user = new User(res.getString("username"), res.getString("password"));
            user.setId(res.getInt("id"));

            return user;
        }
        return null;
    }

    public User getUserData(String username) throws SQLException {
        String qr = "SELECT users.id,users.username,pd.email,pd.full_name,pd.address,pd.city,pd.zip FROM users LEFT JOIN personal_data pd on users.id = pd.user_id WHERE username=?";

        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);
        stmt.setString(1, username);
        ResultSet res = stmt.executeQuery();

        while (res.next()) {
            User user = new User(res.getInt("id"), res.getString("username"), res.getString("email"), res.getString("full_name"), res.getInt("zip"), res.getString("address"), res.getString("city"));

            System.out.println(res.getInt("id") + res.getString("username") + res.getString("email") + res.getString("full_name") + res.getInt("zip") + res.getString("address") + res.getString("city"));
            return user;
        }
        return null;
    }

    public List<SavedCart> getCartByUser(String username) throws SQLException {

        String qr = "SELECT users.id as user_id, c.id as cart_id, product_list,total_cost,date_of_purchase FROM users\n" +
                "        LEFT JOIN cart c on users.id = c.user_id\n" +
                "        WHERE username=?";

        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);
        stmt.setString(1, username);
        ResultSet res = stmt.executeQuery();

        List<SavedCart> savedCartList = new ArrayList<>();

        while (res.next()) {
            SavedCart cart = new SavedCart(res.getInt("cart_id"), res.getInt("user_id"), res.getString("product_list"), res.getInt("total_cost"), res.getDate("date_of_purchase"));
            savedCartList.add(cart);

//            List<String> products = new ArrayList<>();
//            for(String i: cart.getProductList()){
//                products.add(product.find(Integer.parseInt(i)).getName());
//            }
//            for(String pro: products){
//                System.out.println(pro);
//            }
////            cart.setProductList(products);
        }
      
        return savedCartList;


    }

    @Override
    public boolean Validate(User user) throws SQLException {

        String qr = "SELECT * FROM users WHERE username=? AND password=?";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());

        ResultSet res = stmt.executeQuery();
        return res.next();

    }

    @Override
    public void remove(int id) throws SQLException {
        String qr = "DELETE FROM users WHERE id=?";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);
        stmt.setInt(1, id);
        stmt.executeUpdate();

    }

    @Override
    public List<User> getAll() throws SQLException {

        List<User> allUsers = new ArrayList<>();
        String qr = "SELECT * FROM users";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);
        ResultSet res = stmt.executeQuery();
        while (res.next()) {
            User user = new User(res.getString("username"), res.getString("password"));
            allUsers.add(user);
        }

        return allUsers;
    }
}
