package com.jbdl.user_service.service;

import com.jbdl.wallet_service.Commons;
import com.jbdl.user_service.model.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    public void sendNotification(User user){
        JSONObject kafkaUserObject = new JSONObject();
        kafkaUserObject.put(Commons.USER_NAME, user.getName());
        kafkaUserObject.put(Commons.USER_EMAIL, user.getEmail());
        kafkaUserObject.put(Commons.USER_MOBILE, user.getMobileNo());
        kafkaUserObject.put(Commons.USER_IDENTIFIER, user.getUserIdentifier());
        kafkaUserObject.put(Commons.USER_IDENTIFIER_VALUE, user.getIdentifierValue());
        System.out.println("User notified successfully");
        kafkaTemplate.send(Commons.USER_DETAILS_TOPIC, kafkaUserObject.toString());
    }
}
