package com.codecool.shop;

import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cart {
    private List<Product> productsInCart = new ArrayList<>();
    private static final Cart INSTANCE = new Cart();


    public void addProduct(Product product) {
        boolean isContain = false;
        if(productsInCart.size() == 0){
            productsInCart.add(product);
        }

        for (Product value : productsInCart) {
            if (product.getName().equals(value.getName())) {
                isContain = true;
                float price = value.getDefaultPrice();
                int quantity = value.getQuantityInCart();
                value.setDefaultPrice(price + product.getDefaultPrice());
                value.setQuantityInCart(quantity + 1);
            }
        }
        if(!isContain){
            productsInCart.add(product);
        }



    }

    public Product getNewItem(){
       return productsInCart.get(productsInCart.size()-1);
    }

    public List<Product> getAllProductsInCart() {
        return productsInCart;
    }

    public void removeProduct(Product product) {
        productsInCart.remove(product);
    }

    public HashMap<Product, Integer> getCountedProduct() {
        HashMap<Product, Integer> countedProducts = new HashMap<>();
        for (Product product : productsInCart) {
            if (countedProducts.containsKey(product)) {
                countedProducts.replace(product, countedProducts.get(product) + 1);
            } else {
                countedProducts.put(product, 1);
            }
        }
        return countedProducts;
    }

    public int getCostOfCart() {
        HashMap<Product, Integer> countedProducts = getCountedProduct();
        int sum = 0;
        for (Product product : countedProducts.keySet()) {
            sum += (product.getDefaultPrice() * countedProducts.get(product));
        }
        return sum;
    }

    public int getCountOfCart() {
        HashMap<Product, Integer> countedProducts = getCountedProduct();
        int count = 0;
        for (Product product : countedProducts.keySet()) {
            count += (countedProducts.get(product));
        }
        return count;
    }


    public static Cart getInstance() {
        return INSTANCE;
    }

}