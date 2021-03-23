package com.pluralsight.conference.service;

import com.pluralsight.conference.model.ConferenceUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.Collections;

@Service
public class ConferenceUserDetailsContextMapper implements UserDetailsContextMapper {

    @Autowired
    private DataSource dataSource;

    @Bean
    private JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    private static final String loadUserByUsernameQuery = "select username, password, enabled, nickname from users where username = ?;";

    @Override
    public UserDetails mapUserFromContext(DirContextOperations dirContextOperations, String s, Collection<? extends GrantedAuthority> collection) {
        final ConferenceUserDetails userDetails = new ConferenceUserDetails(
                dirContextOperations.getStringAttribute("uid"),
                "fake",
                Collections.emptyList()
        );

        jdbcTemplate().queryForObject(loadUserByUsernameQuery, (resultSet, i) -> {
            userDetails.setNickname(resultSet.getString("nickname"));
            return userDetails;
        }, dirContextOperations.getStringAttribute("uid"));

        return userDetails;
    }

    @Override
    public void mapUserToContext(UserDetails userDetails, DirContextAdapter dirContextAdapter) { }
}
