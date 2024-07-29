package com.jbdl.user_service.service;

import com.jbdl.user_service.enums.UserStatus;
import com.jbdl.user_service.enums.UserType;
import com.jbdl.user_service.model.User;
import com.jbdl.user_service.repository.UserRepo;
import com.jbdl.user_service.request.UserRequest;
import com.jbdl.user_service.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    KafkaProducerService kafkaProducerService;

    public UserResponse saveUser(UserRequest userRequest) {
        UserResponse userResponse = UserResponse.builder().name(userRequest.getName()).email(userRequest.getEmail()).build();

        try {
            User user = User.builder().name(userRequest.getName()).email(userRequest.getEmail()).mobileNo(userRequest.getMobileNo())
                    .password(userRequest.getPassword()).userIdentifier(userRequest.getUserIdentifier()).identifierValue(userRequest.getIdentifierValue()).build();
            user.setUserType(UserType.NORMAL);
            user.setUserStatus(UserStatus.ACTIVE);
            User savedUser = userRepo.save(user);
            if (savedUser == null) {
                userResponse.setMessage("User onboarding failed!");
            } else {
                kafkaProducerService.sendNotification(user);
                userResponse.setMessage("User created succesfully!");
            }
        } catch (Exception e) {
            userResponse.setMessage(e.getMessage());
        }

        return userResponse;
    }

}
