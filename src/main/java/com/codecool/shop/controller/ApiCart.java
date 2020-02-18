package com.codecool.shop.controller;

import com.codecool.shop.Cart;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;


@WebServlet(urlPatterns = {"/apiCart"})
public class ApiCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String response=req.getReader().lines().collect(Collectors.joining());
        Gson gson = new Gson();
        Product product = gson.fromJson(response, Product.class);
        System.out.println(product);

        //Object obj = new JSONParser().parse("JSONExample.json");

        /*ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        Cart cart=new Cart();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("category", productCategoryDataStore.find(1));
        context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        context.setVariable("itemsInCart",cart.getProductsInCart());

        engine.process("product/index.html", context, resp.getWriter());*/
    }

}
