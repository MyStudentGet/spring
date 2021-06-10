package com.itheima.service;

import com.itheima.domain.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<User> list() throws IOException;

    void save(User user, Long[] roleIds);

    void del(Long userId);

    User login(String username, String password);
}
