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

        stmt.setInt(1,product.getId());
        stmt.setString(2,product.getName());
        stmt.setInt(3, (int) product.getDefaultPrice());
        stmt.setString(4,String.valueOf(product.getDefaultCurrency()));
        stmt.setString(5,product.getDescription());
        stmt.setInt(6,product.getProductCategory().getId());
        stmt.setInt(7,product.getSupplier().getId());
        stmt.setInt(8,product.getAmount());

        stmt.executeUpdate();
    }


    @Override
    public Product find(int id) throws SQLException{ //TODO swap placeholders
        String get = "SELECT * FROM product WHERE id= ?;";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(get);

        stmt.setInt(1,id);
        ResultSet res =  stmt.executeQuery();
        if(res.next()){
            Product product= new Product(res.getInt("id"),res.getString("name"),Integer.parseInt(res.getString("default_price")),res.getString("currency_string"),res.getString("description"),new ProductCategory("ProductTest","ProducttestDepartment","ProducttestDescription"),new Supplier("SupplierTest","Supplierdescription"),Integer.parseInt(res.getString("amount")));
            return product;
        }
        return null;
    }

    @Override
    public void remove(int id) {
        //data.remove(find(id));
    }

    @Override
    public List<Product> getAll() throws SQLException { //TODO swap placeholders
        List<Product> allProducts = new ArrayList<>();
        String getAll = "SELECT * FROM product;";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(getAll);
        ResultSet res =  stmt.executeQuery();
        while(res.next()){
            //Product product= new Product(res.getString("name"),Integer.parseInt(res.getString("default_price")),res.getString("currency_string"),res.getString("description"),res.getString("product_category_id"),res.getString("supplier_id"),Integer.parseInt(res.getString("amount")));
            Product product= new Product(res.getInt("id"),res.getString("name"),Integer.parseInt(res.getString("default_price")),res.getString("currency_string"),res.getString("description"),new ProductCategory("ProductTest","ProducttestDepartment","ProducttestDescription"),new Supplier("SupplierTest","Supplierdescription"),Integer.parseInt(res.getString("amount")));
            allProducts.add(product);

        }

        return allProducts;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;//data.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;//data.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }
}
