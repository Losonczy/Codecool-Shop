package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoMem implements ProductDao {

    //private List<Product> data = new ArrayList<>();
    private DataSource dataSource;
    private static ProductDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoMem() {
    }

    public static ProductDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductDaoMem();

        }
        return instance;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product) throws SQLException {
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
    }


    @Override
    public Product find(int id) throws SQLException {
        String get = "SELECT product.id AS p_id, product.name AS p_name, product.default_price AS p_default_price, product.currency_string AS p_curency_string, product.description AS p_description, product.amount AS p_amount, product_category.id AS pc_id, product_category.name AS pc_name, product_category.department AS pc_department, product_category.description AS pc_description, s.id AS s_id, s.name AS s_name, s.description AS s_description " +
                "FROM product " +
                "LEFT JOIN product_category ON product.product_category_id = product_category.id " +
                "LEFT JOIN supplier s on product.supplier_id = s.id WHERE product.id= ?;";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(get);

        stmt.setInt(1, id);
        ResultSet res = stmt.executeQuery();
        if (res.next()) {
            Product product = new Product(res.getInt("p_id"), res.getString("p_name"), Integer.parseInt(res.getString("p_default_price")), res.getString("p_curency_string"), res.getString("p_description"), new ProductCategory(res.getInt("pc_id"), res.getString("pc_name"), res.getString("pc_department"), res.getString("pc_description")), new Supplier(res.getInt("s_id"), res.getString("s_name"), res.getString("s_description")), Integer.parseInt(res.getString("p_amount")));
            return product;
        }
        return null;
    }

    @Override
    public void remove(int id) throws SQLException {
        String qr = "DELETE * FROM product WHERE id= ?;";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);

        stmt.setInt(1, id);
        stmt.executeUpdate();

    }

    @Override
    public List<Product> getAll() throws SQLException {
        List<Product> allProducts = new ArrayList<>();

        String getAll = "SELECT product.id AS p_id, product.name AS p_name, product.default_price AS p_default_price, product.currency_string AS p_curency_string, product.description AS p_description, product.amount AS p_amount, product_category.id AS pc_id, product_category.name AS pc_name, product_category.department AS pc_department, product_category.description AS pc_description, s.id AS s_id, s.name AS s_name, s.description AS s_description " +
                "FROM product " +
                "LEFT JOIN product_category ON product.product_category_id = product_category.id " +
                "LEFT JOIN supplier s on product.supplier_id = s.id;";

        PreparedStatement stmt = dataSource.getConnection().prepareStatement(getAll);

        ResultSet res = stmt.executeQuery();

        while (res.next()) {
            Product product = new Product(res.getInt("p_id"), res.getString("p_name"), Integer.parseInt(res.getString("p_default_price")), res.getString("p_curency_string"), res.getString("p_description"), new ProductCategory(res.getInt("pc_id"), res.getString("pc_name"), res.getString("pc_department"), res.getString("pc_description")), new Supplier(res.getInt("s_id"), res.getString("s_name"), res.getString("s_description")), Integer.parseInt(res.getString("p_amount")));
            allProducts.add(product);
        }

        return allProducts;
    }

    @Override
    public List<Product> getBy(Supplier supplier) throws SQLException {
        List<Product> allProducts = new ArrayList<>();
        String get = "SELECT product.id AS p_id, product.name AS p_name, product.default_price AS p_default_price, product.currency_string AS p_curency_string, product.description AS p_description, product.amount AS p_amount, product_category.id AS pc_id, product_category.name AS pc_name, product_category.department AS pc_department, product_category.description AS pc_description, s.id AS s_id, s.name AS s_name, s.description AS s_description " +
                "FROM product " +
                "LEFT JOIN product_category ON product.product_category_id = product_category.id " +
                "LEFT JOIN supplier s on product.supplier_id = s.id " +
                "WHERE supplier.id= ?;";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(get);

        stmt.setInt(1, supplier.getId());
        ResultSet res = stmt.executeQuery();
        if (res.next()) {
            Product product = new Product(res.getInt("p_id"), res.getString("p_name"), Integer.parseInt(res.getString("p_default_price")), res.getString("p_curency_string"), res.getString("p_description"), new ProductCategory(res.getInt("pc_id"), res.getString("pc_name"), res.getString("pc_department"), res.getString("pc_description")), new Supplier(res.getInt("s_id"), res.getString("s_name"), res.getString("s_description")), Integer.parseInt(res.getString("p_amount")));
            allProducts.add(product);
        }
        return allProducts;
    }


    @Override
    public List<Product> getBy(ProductCategory productCategory) throws SQLException {
        List<Product> allProducts = new ArrayList<>();
        String get = "SELECT product.id AS p_id, product.name AS p_name, product.default_price AS p_default_price, product.currency_string AS p_curency_string, product.description AS p_description, product.amount AS p_amount, product_category.id AS pc_id, product_category.name AS pc_name, product_category.department AS pc_department, product_category.description AS pc_description, s.id AS s_id, s.name AS s_name, s.description AS s_description " +
                "FROM product " +
                "LEFT JOIN product_category ON product.product_category_id = product_category.id " +
                "LEFT JOIN supplier s on product.supplier_id = s.id " +
                "WHERE product_category.id= ?;";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(get);

        stmt.setInt(1, productCategory.getId());
        ResultSet res = stmt.executeQuery();
        if (res.next()) {
            Product product = new Product(res.getInt("p_id"), res.getString("p_name"), Integer.parseInt(res.getString("p_default_price")), res.getString("p_curency_string"), res.getString("p_description"), new ProductCategory(res.getInt("pc_id"), res.getString("pc_name"), res.getString("pc_department"), res.getString("pc_description")), new Supplier(res.getInt("s_id"),res.getString("s_name"), res.getString("s_description")), Integer.parseInt(res.getString("p_amount")));
            allProducts.add(product);
        }
        return allProducts;
    }
}