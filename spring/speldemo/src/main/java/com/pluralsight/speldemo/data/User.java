package com.pluralsight.speldemo.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


// This annotation creates a bean called user
@Component("user")
public class User {

    // Using the @Value annotation, default values can be set
    @Value("#{'John Doe'}")
    private String name;

    @Value("#{systemProperties['user.country']}")
    private String country;

    @Value("#{systemProperties['user.language']}")
    private String language;

    @Value("#{systemProperties['user.timezone']}")
    private String timeZone;

    // Setting a default value of 30
    @Value("#{30}")
    private int age;

    public User() {}

    /*public User(String country, String language) {
        System.out.println(country);
        this.country = country;
        this.language = language;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTimeZone() {
        return timeZone;
    }

    @Value("#{systemProperties['user.timezone']}")
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
