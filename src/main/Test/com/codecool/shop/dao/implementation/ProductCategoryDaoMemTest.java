package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.postgresql.ds.PGSimpleDataSource;
import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoMemTest {

    DataSource dataSource = connection();
//    ProductCategoryDaoMem category = ProductCategoryDaoMem.getInstance();
    ProductCategory category = new ProductCategory(0,"Unknown","Decoration","Some random category");
    ProductCategoryDaoMemTest() throws SQLException {
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

        String qr = "INSERT INTO product_category(name,department,description) VALUES (?,?,?);";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);

        stmt.setString(1,category.getName());
        stmt.setString(2,category.getDepartment());
        stmt.setString(3,category.getDescription());
        stmt.executeUpdate();


        String get = "SELECT * FROM product_category WHERE name = ?;";
        PreparedStatement check = dataSource.getConnection().prepareStatement(get);
        check.setString(1,category.getName());
        ResultSet res =  check.executeQuery();


        assertTrue(res.next());
        assertEquals(category.getName(), res.getString("name"));
        assertNotNull(category.getName());
        assertNotNull(category.getDepartment());
        assertNotNull(category.getDescription());

    }

    @Test
    public void testFind() throws SQLException {
        String get = "SELECT * FROM product_category WHERE id= ?;";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(get);

        stmt.setInt(1,1);
        ResultSet res =  stmt.executeQuery();
        while(res.next()) {
            assertEquals("Animal", res.getString("name"));
        }

    }

    @Test
    public void testRemove() throws SQLException {
        String qr = "DELETE FROM product_category WHERE name = ?;";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);

        stmt.setString(1,"Unknown");
        stmt.executeUpdate();


        String get = "SELECT * FROM product_category WHERE id = ?;";
        PreparedStatement check = dataSource.getConnection().prepareStatement(get);
        check.setInt(1,category.getId());
        ResultSet res =  check.executeQuery();

        assertFalse(res.next());
    }

    @Test
    public void getAll() throws SQLException {
        String qr = "SELECT * FROM product_category;";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);
        ResultSet res =  stmt.executeQuery();

        assertNotNull(res.next());

    }


}