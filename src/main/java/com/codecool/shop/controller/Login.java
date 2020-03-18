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
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/login"})
public class Login extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        PasswordHashing demo = new PasswordHashing();

        HttpSession session = req.getSession();


        try {
            if(demo.login(username,password)){
                context.setVariable("isLogin", "true");
                session.setAttribute("username",username);
                session.setAttribute("pw",password);

                System.out.println("session name: "+session.getAttribute("username"));
                System.out.println("Successful login");
            }else{
                context.setVariable("isLogin", "false");
                System.out.println("Unsuccessful login");
                session.invalidate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        engine.process("product/index.html", context, resp.getWriter());
    }
}
