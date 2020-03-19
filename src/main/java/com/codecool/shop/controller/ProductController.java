package com.codecool.shop.controller;

import com.codecool.shop.Cart;
import com.codecool.shop.SavedCart;
import com.codecool.shop.User;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.RegisterDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.RegisterDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        Cart cart = Cart.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());


//        try {
//            context.setVariable("category", productCategoryDataStore.find(1));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        try {
            context.setVariable("products", productDataStore.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        context.setVariable("itemsInCart", cart.getAllProductsInCart());
        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        RegisterDao registration = RegisterDaoMem.getINSTANCE();
        HttpSession session = req.getSession();
        String username = String.valueOf(session.getAttribute("username"));

        try {
            User actualUser = registration.getUserData(username);
            try {
                List<SavedCart> cartData = registration.getCartByUser(username);
                context.setVariable("cartData", cartData);
            } catch (NullPointerException ex) {
                context.setVariable("cartData", "No orders yet!");
            }
            context.setVariable("actualUser", actualUser);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        engine.process("product/index.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getReader().lines().collect(Collectors.joining());

    }


}
