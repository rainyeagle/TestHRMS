package com.re.service;

import com.re.model.User;

public interface UserService {
    String checkUsername(User user);

    String checkLoginInfo(User user);
}
