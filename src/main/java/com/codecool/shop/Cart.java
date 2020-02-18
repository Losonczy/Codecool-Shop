package com.codecool.shop;

import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> productsInCart=new ArrayList<Product>();


    public void addProduct(Product product){
        productsInCart.add(product);
    }

    public void removeProduct(Product product){
        productsInCart.remove(product);
    }

}