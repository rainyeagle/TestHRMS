package com.re.service.impl;

import com.re.dao.impl.UserDaoImpl;
import com.re.model.User;
import com.re.service.UserService;

public class UserServiceImpl implements UserService {
    UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public String checkUsername(User user){
        return userDao.checkUsername(user);
    }

    @Override
    public String checkLoginInfo(User user) {
        return userDao.checkUserInfo(user);
    }
}
