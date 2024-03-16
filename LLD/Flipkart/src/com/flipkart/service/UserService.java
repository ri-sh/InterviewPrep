package com.flipkart.service;

import com.flipkart.exceptions.UserDoesNotExistException;
import com.flipkart.models.Item;
import com.flipkart.models.User;
import com.flipkart.models.Wallet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private static  int userTotalCount = 0;
    private final Map<String, User> users = new HashMap<>();

    public UserService(){
    }
    public User addUser(String name, String email, double walletAmount){
        User user = new User(++userTotalCount, name, email, new Wallet(walletAmount));
        users.put(name, user);
        return  user;
    }
    public User getUser(String userName){
        if (users.get(userName) == null) {
            throw new UserDoesNotExistException();
        }
        return users.get(userName);
    }

}
