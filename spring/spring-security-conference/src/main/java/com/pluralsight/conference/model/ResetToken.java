package com.pluralsight.conference.model;

import java.util.Calendar;
import java.util.Date;

public class ResetToken {

    public static final int EXPIRATION_MS = 1000 * 60 * 60 * 24;

    private String token;
    private String username;
    private String email;
    private Date expiryDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date calculateExpiryDate(int expiryTimeInMillis) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MILLISECOND, expiryTimeInMillis);

        return cal.getTime();
    }
}
