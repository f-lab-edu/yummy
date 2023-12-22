package com.huijin.yummy.user.service.implement;

import com.huijin.yummy.user.entity.User;
import com.huijin.yummy.user.repository.UserRepository;
import com.huijin.yummy.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    @Autowired
    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
}
