package com.codecool.shop.config;

import com.codecool.shop.Connection;
import com.codecool.shop.dao.DaoTest;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        Connection connection=new Connection();
        try {
            connection.setup();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*
        //setting up a new supplier
        Supplier mineralMine = new Supplier("Mineral Mine Co.", "Quality rocks and minerals");
        supplierDataStore.add(mineralMine);
        Supplier rockFactory = new Supplier("Rock Factory", "Best looking minerals made by time");

        //setting up a new product category
        ProductCategory animal = new ProductCategory("Animal","Decoration","It makes u healthier day by day");
        productCategoryDataStore.add(animal);
        ProductCategory cartoon = new ProductCategory("Cartoon","Decoration","Makes your mood better");
        productCategoryDataStore.add(cartoon);
        ProductCategory holiday = new ProductCategory("Holiday","Decoration","Your girlfriend will love it!");
        productCategoryDataStore.add(holiday);
        ProductCategory other = new ProductCategory("Other","Decoration","Money stones");
        productCategoryDataStore.add(other);
        ProductCategory simple = new ProductCategory("Simple","Decoration","The true type of healing stones");
        productCategoryDataStore.add(simple);

        //setting up products and printing it
        productDataStore.add(new Product("Taconite",39,"USD","Good looking, makes your stronger and smarter.",animal,mineralMine,4));
        productDataStore.add(new Product(" Carnallite",49,"USD","You will be powered by the winds of magic.",animal,rockFactory,6));
        productDataStore.add(new Product("Moolooite",35,"USD","ALso known as 'salt rock', makes your dish tasty.",animal,mineralMine,10));
        productDataStore.add(new Product(" Arsenolite",69,"USD","Get's you out any rough situation.",other,rockFactory,9));
        productDataStore.add(new Product("Fornacite",59,"USD","It's useless, but beautiful!",holiday,mineralMine,8));
        productDataStore.add(new Product("Dickite",64,"USD","It helps you in bed and everywhere where you need it!",holiday,rockFactory,7));
        productDataStore.add(new Product("Fukalite",999,"USD","Makes rich people a little bit less richer",animal,mineralMine,5));
        productDataStore.add(new Product("Goosecreekite",39,"USD","A pink rock with green crystals, you need more?!",cartoon,rockFactory,2));
        productDataStore.add(new Product("Cummingtonite",29,"USD","No one knows what it is good for, but looks cool.",cartoon,mineralMine,2));
        /*productDataStore.add(new Product("Dragon shard",49,"USD","Rocks + dragon breath = Dragon shard.",blue,rockFactory));
        productDataStore.add(new Product("Analcime",53,"USD","Helps you get your loved one.",pink,mineralMine));
        productDataStore.add(new Product("Shattuckite",39,"USD","You can breath underwater with this rock...Or not.",blue,rockFactory));
        productDataStore.add(new Product("Welshite",49,"USD","Remove any disease.",white,mineralMine));*/


    }



}
