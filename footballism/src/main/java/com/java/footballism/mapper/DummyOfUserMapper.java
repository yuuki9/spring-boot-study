package com.java.footballism.mapper;

import org.springframework.stereotype.Service;

import com.java.footballism.config.UserInfo;

@Service
public class DummyOfUserMapper {
   
	public UserInfo getUserInfo(String insertedId) {
        if (insertedId.equals("dev")) {
            return UserInfo.builder()
                    .id("dev")
                    .pw("$2a$12$BwHwnBcOuytio7uaon/nruah0miPgojzELPUUcuB48cYTsjWt6KJ6")
                    .roles("USER")
                    .build();
        }
        return null;
    }
}