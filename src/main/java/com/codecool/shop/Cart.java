package com.codecool.shop;

import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> productsInCart= new ArrayList<>();


    public void addProduct(Product product){
        productsInCart.add(product);
    }

    public List<Product> getAllProductsInCart() {
        return productsInCart;
    }

    public void removeProduct(Product product){
        productsInCart.remove(product);
    }

}