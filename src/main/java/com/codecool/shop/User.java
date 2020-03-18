package com.codecool.shop;

public class User {

    private int id;
    private String username;


    private String email;
    private String password;

    private int zip;
    private String address;
    private String city;

    public User(String username, String password) {
        this.username = username;
        this.password = hashPassword(password);
    }

    public String hashPassword(String password) {
        return "Hashed password";
    }
    public String decodePassword(){
        return "decoded password";
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
