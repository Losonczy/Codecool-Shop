package com.codecool.shop;

import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cart {
    private List<Product> productsInCart = new ArrayList<>();
    private static final Cart INSTANCE = new Cart();


    public void addProduct(Product product) {
        for(int i=0; i< productsInCart.size();i++){
            if(product.getId() != productsInCart.get(i).getId()){
                productsInCart.add(product);
            }else{
                float price = productsInCart.get(i).getDefaultPrice();
                int quantity = productsInCart.get(i).getQuantityInCart();
                productsInCart.get(i).setDefaultPrice(price + product.getDefaultPrice());
                productsInCart.get(i).setQuantityInCart(quantity + 1);
            }
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