package com.codecool.shop;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SavedCart {
    ProductDaoMem productDao = ProductDaoMem.getInstance();
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getProductListData() {
        return productListData;
    }

    public void setProductListData(String productListData) {
        this.productListData = productListData;
    }

    public List<String> getProductList() {
        return productList;
    }

    public void setProductList(List<String> productList) throws SQLException {

        List<String> newProductList = new ArrayList<>();
        for(String product : productList){
            Product prod = productDao.find(Integer.parseInt(product));
            newProductList.add(prod.getName());
        }
        this.productList = newProductList;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    private int id;
    private int user_id;
    private String productListData;
    private List<String> productList;
    private int totalCost;
    private Date dateOfPurchase;

    public SavedCart(int id, int user_id, String productListData, int totalCost, Date dateOfPurchase) throws SQLException {
        this.id = id;
        this.user_id = user_id;
        this.productListData = productListData;
        this.totalCost = totalCost;
        this.dateOfPurchase = dateOfPurchase;
        productList = Arrays.asList(productListData.split(","));
        setProductList(productList);
    }
    
}