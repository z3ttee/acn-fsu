package com.pluralsight.conference.event;

import com.pluralsight.conference.model.Password;
import org.springframework.context.ApplicationEvent;

public class OnPasswordResetEvent extends ApplicationEvent {

    private Password password;
    private String appUrl;

    public OnPasswordResetEvent(Password password, String appUrl) {
        super(password);

        this.password = password;
        this.appUrl = appUrl;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }
}
