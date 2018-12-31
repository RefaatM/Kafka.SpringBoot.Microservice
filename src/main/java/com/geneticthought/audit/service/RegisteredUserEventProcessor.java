package com.geneticthought.audit.service;

import com.geneticthought.audit.consumer.RegisteredUserEvent;
import com.geneticthought.audit.model.User;
import com.geneticthought.audit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisteredUserEventProcessor {

    @Autowired
    private UserRepository userRepository;

    public void processEvent(RegisteredUserEvent event) {
        User user = new User(event.getLogin(), event.getCreationTime());
        if (null == userRepository.findByLogin((user.getLogin()))) {
            userRepository.save(user);
        }
    }
}
