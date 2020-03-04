package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoMem implements ProductCategoryDao {

    private List<ProductCategory> data = new ArrayList<>();
    private static ProductCategoryDaoMem instance = null;
    DataSource dataSource;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoMem() {
    }

    public static ProductCategoryDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoMem();
        }
        return instance;
    }

    @Override
    public void getDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) throws SQLException {
//        category.setId(data.size() + 1);
//        data.add(category);
        String qr = "INSERT INTO product_category(name,department,description) VALUES (?,?,?);";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);

        stmt.setString(1,category.getName());
        stmt.setString(2,category.getDepartment());
        stmt.setString(3,category.getDescription());
        stmt.executeUpdate();
    }

    @Override
    public ProductCategory find(int id) throws SQLException {
//        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);

        String get = "SELECT * FROM product_category WHERE id= ?;";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(get);

        stmt.setInt(1,id);
        ResultSet res =  stmt.executeQuery();

        if(res.next()){
            ProductCategory category = new ProductCategory(res.getInt("id"),res.getString("name"),res.getString("department"),res.getString("description"));
            return category;
        }
        return null;
    }

    @Override
    public void remove(int id) throws SQLException {

//        data.remove(find(id));

        String qr = "DELETE FROM product_category WHERE id = ?;";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);

        stmt.setInt(1,id);
        stmt.executeUpdate();
    }

    @Override
    public List<ProductCategory> getAll() throws SQLException {
        List<ProductCategory> allCategory = new ArrayList<>();
        String qr = "SELECT * FROM product_category;";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);
        ResultSet res =  stmt.executeQuery();
        while(res.next()){
            ProductCategory category = new ProductCategory(res.getInt("id"),res.getString("name"),res.getString("department"),res.getString("description"));
            allCategory.add(category);

        }
        return allCategory;
    }

}
