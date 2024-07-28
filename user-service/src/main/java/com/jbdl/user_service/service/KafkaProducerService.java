package com.jbdl.user_service.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jbdl.user_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void sendNotification(User user){
    }
}
