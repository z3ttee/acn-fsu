package com.pluralsight.conference;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class ConferenceConfig {

    // Bean type is SINGLETON by default -> so there is only one instance through all the app
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();

        // Configure the web contents. Before, it was configured in application.properties
        bean.setPrefix("/WEB-INF/jsp/");
        bean.setSuffix(".jsp");

        // Order of viewResolver
        // If we have more than one, this specifies the order in which they should be executed
        // First one to be executed successfully returns the result.
        bean.setOrder(0);

        return bean;
    }

}
