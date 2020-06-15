
# Codecool Online Shop

Codecoool shop is a project for Codecool Programming School to practise use of Java Servlets. This is a basic webshop that sells diffeent types of stones. <br>

It's possible to purchase items without logging in, but registered users can access to previous purchases.
<br> Side navigation contains product filters, registration and login opportunities. 
<br> Purchase can not be completed until "Paypal" button was not clicked while shopping(since this is a school project, valid payment is not implemented)

## Tech stack:
 
 - Java servlets
 - JDBC
 - Postgres database
 - Thymeleaf

<br>
For separating data layer from business logic DAO pattern was used.
<br>
Front end:
 
 - HTML5
 - CSS3
 - Vanilla javascript
 - AJAX
 
 ### Live version
 <a href="https://stones-webshop.herokuapp.com/login">Click to checkout the project's live version</a>
 
# Install

Import this project to IntelliJ as a Maven project.
IntelliJ can auto-install the dependencies based on the pom.xml

Command run in config: jetty:run
