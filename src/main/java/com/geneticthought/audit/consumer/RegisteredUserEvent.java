package com.geneticthought.audit.consumer;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisteredUserEvent {

    private String login;
    private LocalDateTime creationTime;

    public RegisteredUserEvent() {
    }

    public RegisteredUserEvent(String login, LocalDateTime creationTime) {
        this.login = login;
        this.creationTime = creationTime;
    }

    public String getLogin() {
        return login;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

}
