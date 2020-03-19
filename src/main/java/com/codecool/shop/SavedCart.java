package com.codecool.shop;

import java.util.Date;
import java.util.List;

public class SavedCart {
    private int id;
    private int user_id;
    private String productListData;
    private List<Integer> productList;
    private int totalCost;
    private Date dateOfPurchase;

    public SavedCart(int id, int user_id, String productListData, int totalCost, Date dateOfPurchase) {
        this.id = id;
        this.user_id = user_id;
        this.productListData = productListData;
        this.totalCost = totalCost;
        this.dateOfPurchase = dateOfPurchase;
    }
}