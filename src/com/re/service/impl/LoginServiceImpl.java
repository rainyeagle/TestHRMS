package com.re.service.impl;

import com.re.dao.impl.LoginDaoImpl;
import com.re.model.Person;
import com.re.service.LoginService;

public class LoginServiceImpl implements LoginService {
    LoginDaoImpl loginDao = new LoginDaoImpl();

    @Override
    public String checkLoginInfo(Person person) {
        return loginDao.checkUserInfo(person);
    }
}
