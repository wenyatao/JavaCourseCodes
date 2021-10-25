package com.wyt.spring01;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService{

   @Resource(name = "userDao")
    private UserDao userDao;

    @Override
    public void say() {
        System.out.println("say hello");
        userDao.run();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
