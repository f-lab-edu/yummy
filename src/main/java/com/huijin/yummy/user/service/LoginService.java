package com.huijin.yummy.user.service;

import com.huijin.yummy.user.entity.User;

public interface LoginService {
    public User findByUserId(String userId);
}

