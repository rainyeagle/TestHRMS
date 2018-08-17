package com.re.dao;

import com.re.model.User;

public interface UserDao {
    String checkUsername(User user);

    String checkUserInfo(User user);
}
