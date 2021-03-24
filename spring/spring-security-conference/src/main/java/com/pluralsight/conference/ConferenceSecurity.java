package com.pluralsight.conference;

import com.pluralsight.conference.service.ConferenceUserDetailsContextMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class ConferenceSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ConferenceUserDetailsContextMapper ctxMapper;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // Everyone who wants to access these pages, can do so in anonymous mode
                .antMatchers("/anonymous*").anonymous()

                // Every request is permitted onto this endpoint
                .antMatchers("/login*").permitAll()
                .antMatchers("/account*").permitAll()
                .antMatchers("/password*").permitAll()

                // Permit all assets
                .antMatchers("/assets/css/**", "/assets/js/**", "/images/**").permitAll()

                .antMatchers("/index*").permitAll()

                // Every other request needs to be authenticated
                .anyRequest().authenticated()
                .and()

                // Login form specific settings, like url path and action path
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login") // Action path
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()

                // Configure rememberMe service
                .and()
                .rememberMe()
                .key("supersecretkey")
                .rememberMeParameter("remember-me")
                .tokenRepository(tokenRepository())

                // Setup Logout-Service
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                // Make the default POST request to a GET request
                // Because RequestMatcher checks for urls ending with /perform_logout, no need to specify .logoutUrl()
                .logoutRequestMatcher(new AntPathRequestMatcher("/perform_logout", "GET"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID") // Default cookie name
                .permitAll();
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);

        return tokenRepository;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder());

        /*auth.ldapAuthentication()
                // Setting up domain name pattern
                // uid={0} = first character to parse in for search
                // ou = Organisational Unit
                .userDnPatterns("uid={0},ou=people")

                .groupSearchBase("ou=groups")
                .contextSource()

                // dc = Directory Context
                .url("ldap://localhost:8389/dc=pluralsight,dc=com")
                .and()
                .passwordCompare()
                .passwordEncoder(passwordEncoder())
                .passwordAttribute("userPassword")

                // Authorize and pull user details from database
                .and()
                .userDetailsContextMapper(ctxMapper);*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
