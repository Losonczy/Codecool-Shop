package com.codecool.shop.dao.implementation;

import com.codecool.shop.User;
import com.codecool.shop.dao.RegisterDao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PasswordHashing {
    Map DB = new HashMap();
    RegisterDao registration = RegisterDaoMem.getINSTANCE();
    public static final String SALT = "my-salt-text";

    public PasswordHashing() {
    }

    public void signup(String username, String password) throws SQLException {
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);
        DB.put(username, hashedPassword);
        User user = new User(username, hashedPassword);
        registration.add(user);
    }

    public Boolean login(String username, String password) throws SQLException {
        Boolean isAuthenticated = false;

        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);

        try {
            String storedPasswordHash = registration.find(username).getPassword();
 
            if (hashedPassword.equals(storedPasswordHash)) {
                isAuthenticated = true;
            } else {
                isAuthenticated = false;
            }
            return isAuthenticated;
        } catch (NullPointerException ex) {
            return false;
        }


    }

    public static String generateHash(String input) {
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f'};
            for (int idx = 0; idx < hashedBytes.length; idx++) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm");
        }

        return hash.toString();
    }

}
