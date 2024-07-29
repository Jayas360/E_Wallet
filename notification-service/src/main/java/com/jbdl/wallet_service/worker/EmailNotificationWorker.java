package com.jbdl.wallet_service.worker;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationWorker {

//    @Autowired
//    JavaMailSender javaMailSender;
    public void sendEmailNotificationToUser(String name, String email, String identifier ) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(email);
        smm.setSubject("Account creation");
        smm.setText("Hi" + name + ", \n"
         + "Your account has been successfully created on e_wallet app" + "\n"
        + "using " + identifier + " as your identification document.\n\n"
        + "Thanks");
//        javaMailSender.send(smm);
    }
}
