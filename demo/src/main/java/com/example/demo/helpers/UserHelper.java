package com.example.demo.helpers;

import com.example.demo.models.UserModel;
import com.example.demo.models.UserModel;
import com.example.demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserHelper {
    @Autowired
    UserRepo userRepo;

    public UserHelper(UserRepo userRepo) {
        this.userRepo = userRepo;
        helper = this;
    }
    private static UserHelper helper = null;

    public static void saveUser(UserModel u){
        helper.userRepo.save(u);
    }
    public static UserModel findUser (String tgId){
        UserModel userModel = new UserModel();
        userModel = helper.userRepo.findUserModelByTgId(tgId);
        return userModel;
    }
}
