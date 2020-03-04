package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoMemTest {
    DataSource dataSource = connection();
    Supplier mineralMine = new Supplier(1,"Mineral Mine Co.", "Quality rocks and minerals");
    ProductCategory animal = new ProductCategory(1,"Animal","Decoration","It makes u healthier day by day");
    //    ProductCategoryDaoMem category = ProductCategoryDaoMem.getInstance();
    Product product = new Product(0,"Welshite",49,"USD","Remove any disease.",animal,mineralMine,5,1);

    ProductDaoMemTest() throws SQLException {
    }

    public DataSource connection() throws SQLException {
        PGSimpleDataSource connect = new PGSimpleDataSource();

        connect.setDatabaseName("webshoptest");
        connect.setUser("evelin");
        connect.setPassword("95Marcius");
        connect.getConnection().close();
        return connect;

    }
    @Test
    public void testAdd() throws SQLException {

        String qr = "INSERT INTO product(id,name,default_price,currency_string,description,product_category_id,supplier_id,amount) VALUES (?,?,?,?,?,?,?,?);";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);

        stmt.setInt(1, product.getId());
        stmt.setString(2, product.getName());
        stmt.setInt(3, (int) product.getDefaultPrice());
        stmt.setString(4, String.valueOf(product.getDefaultCurrency()));
        stmt.setString(5, product.getDescription());
        stmt.setInt(6, product.getProductCategory().getId());
        stmt.setInt(7, product.getSupplier().getId());
        stmt.setInt(8, product.getAmount());
        stmt.executeUpdate();


        String get = "SELECT * FROM product WHERE name = ?;";
        PreparedStatement check = dataSource.getConnection().prepareStatement(get);
        check.setString(1,product.getName());
        ResultSet res =  check.executeQuery();


        assertTrue(res.next());
        assertEquals(product.getName(), res.getString("name"));
        assertNotNull(product);

    }

    @Test
    public void testFind() throws SQLException {
        String get = "SELECT * FROM product WHERE name= ?";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(get);

        stmt.setString(1, product.getName());
        ResultSet res = stmt.executeQuery();

        while(res.next()) {
            assertEquals(product.getName(), res.getString("name"));
        }

    }

    @Test
    public void testRemove() throws SQLException {
        String qr = "DELETE FROM product WHERE name= ?;";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);

        stmt.setString(1, product.getName());
        stmt.executeUpdate();


        String get = "SELECT * FROM product WHERE name = ?;";
        PreparedStatement check = dataSource.getConnection().prepareStatement(get);
        check.setString(1,product.getName());
        ResultSet res =  check.executeQuery();

        assertFalse(res.next());
    }

    @Test
    public void getAll() throws SQLException {
        String qr = "SELECT * FROM product;";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);
        ResultSet res =  stmt.executeQuery();

        assertTrue(res.next());

    }

}