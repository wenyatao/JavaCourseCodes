package com.wyt.spring01;

import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao{
    @Override
    public void run() {
        System.out.println("run.....");
    }

}
