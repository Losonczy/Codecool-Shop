package com.codecool.shop;

import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cart {
    private List<Product> productsInCart= new ArrayList<>();
    private static final Cart INSTANCE = new Cart();

    public void addProduct(Product product){
        productsInCart.add(product);
    }

    public List<Product> getAllProductsInCart() {
        return productsInCart;
    }

    public void removeProduct(Product product){
        productsInCart.remove(product);
    }

    public HashMap<Product, Integer> getCountedProduct(){
        HashMap<Product,Integer> countedProducts=new HashMap<>();
        for(Product product : productsInCart){
            if(countedProducts.containsKey(product)){
                countedProducts.replace(product,countedProducts.get(product)+1);
            }
            else{
                countedProducts.put(product,1);
            }
        }
        return countedProducts;
    }

    public static Cart getInstance(){
        return INSTANCE;
    }

}