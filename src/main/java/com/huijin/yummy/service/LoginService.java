package com.huijin.yummy.service;

import com.huijin.yummy.entity.User;

public interface LoginService {
    public User findByUserId(String userId);
}

