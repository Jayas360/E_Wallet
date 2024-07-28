package com.jbdl.user_service.request;

import com.jbdl.user_service.enums.UserIdentifier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String name;
    private String email;
    private String mobileNo;
    private String password;
    private UserIdentifier userIdentifier;
}
