package com.jbdl.wallet_service.config;

import com.jbdl.wallet_service.Commons;
import com.jbdl.wallet_service.worker.EmailNotificationWorker;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEmailNotificationListener {

    @Autowired
    EmailNotificationWorker emailNotificationWorker;

    @KafkaListener(topics = Commons.USER_DETAILS_TOPIC, groupId = "notify_email")
    public void ListenUserDetails(String data) {
        JSONObject userDetails = new JSONObject(data);
        String name = userDetails.optString(Commons.USER_NAME);
        String email = userDetails.optString(Commons.USER_EMAIL);
        String identifier = userDetails.optString(Commons.USER_IDENTIFIER);
        System.out.println("user information listened : " + data);
//        emailNotificationWorker.sendEmailNotificationToUser(name, email, identifier);

    }
}
