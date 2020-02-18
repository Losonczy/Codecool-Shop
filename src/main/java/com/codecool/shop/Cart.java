package com.codecool.shop;

import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> productsInCart=new ArrayList<CartItem>();


    public void addProduct(CartItem cartItem){
        productsInCart.add(cartItem);
    }

    public List<CartItem> getProductsInCart() {
        return productsInCart;
    }

    public void removeProduct(CartItem cartItem){
        productsInCart.remove(cartItem);
    }




}