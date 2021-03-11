package com.pluralsight.conference;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Locale;

@Configuration
public class ConferenceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // addResourceHandler specifies the path on the web.
        // addResourceLocation specifies the path to the resources in the packaged application
        registry.addResourceHandler("/files/**")
                .addResourceLocations("/WEB-INF/pdf/");
    }

    // Add I18N support
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    // Add I18N support
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    // Add I18N support
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

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
