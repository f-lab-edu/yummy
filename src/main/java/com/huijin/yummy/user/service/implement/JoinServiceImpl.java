package com.huijin.yummy.user.service.implement;

import com.huijin.yummy.user.entity.User;
import com.huijin.yummy.user.repository.UserRepository;
import com.huijin.yummy.user.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinServiceImpl implements JoinService {

    private final UserRepository userRepository;

    @Autowired
    public JoinServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int join(User user) {
        try {
            userRepository.save(user);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
