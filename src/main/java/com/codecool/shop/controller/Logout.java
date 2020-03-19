package com.codecool.shop.controller;

import com.codecool.shop.Cart;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.RegisterDao;
import com.codecool.shop.dao.implementation.PasswordHashing;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.RegisterDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/logout"})
public class Logout extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        Cart cart = Cart.getInstance();

        try {
            context.setVariable("products", productDataStore.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        context.setVariable("itemsInCart", cart.getAllProductsInCart());

        RegisterDao registration = RegisterDaoMem.getINSTANCE();

        HttpSession session = req.getSession();
        session.invalidate();
        System.out.println("Unsuccessful login");

    engine.process("product/index.html", context, resp.getWriter());
}
}
