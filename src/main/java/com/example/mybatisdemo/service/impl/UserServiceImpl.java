package com.example.mybatisdemo.service.impl;

import com.example.mybatisdemo.dao.UserDao;
import com.example.mybatisdemo.pojo.User;
import com.example.mybatisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public List<User> getAllUser() {
        return userDao.selectList(null);
    }
}
