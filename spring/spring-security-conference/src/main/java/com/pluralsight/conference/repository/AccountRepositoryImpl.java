package com.pluralsight.conference.repository;

import com.pluralsight.conference.model.Account;
import com.pluralsight.conference.model.ConferenceUserDetails;
import com.pluralsight.conference.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Iterator;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private DataSource dataSource;

    @Override
    public Account create(Account account) {
        JdbcTemplate template = new JdbcTemplate(dataSource);

        template.update("INSERT INTO accounts (username, password, email, firstname, lastname) VALUES (?, ?, ?, ?, ?)",
                account.getUsername(),
                account.getPassword(),
                account.getEmail(),
                account.getFirstName(),
                account.getLastName()
        );

        return account;
    }

    @Override
    public void saveToken(VerificationToken verificationToken) {
        JdbcTemplate template = new JdbcTemplate(dataSource);

        template.update("INSERT INTO verification_tokens (username, token, expiry_date) VALUES (?, ?, ?);",
                verificationToken.getUsername(),
                verificationToken.getToken(),
                verificationToken.calculateExpiryDate(VerificationToken.EXPIRATION_MS)
        );
    }

    @Override
    public VerificationToken findByToken(String token) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        VerificationToken verificationToken = template.queryForObject("select username, token, expiry_date from verification_tokens where token = ?", (resultSet, rowNum) -> {
            VerificationToken rsToken = new VerificationToken();
            rsToken.setUsername(resultSet.getString("username"));
            rsToken.setToken(resultSet.getString("token"));
            rsToken.setExpiryDate(resultSet.getTimestamp("expiry_date"));
            return rsToken;
        }, token);

        return verificationToken;
    }

    @Override
    public Account findByUsername(String username) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        Account account = template.queryForObject("select username, firstname, lastname, email, password from accounts where username = ?", (resultSet, rowNum) -> {
            Account acc = new Account();
            acc.setUsername(resultSet.getString("username"));
            acc.setFirstName(resultSet.getString("firstname"));
            acc.setLastName(resultSet.getString("lastname"));
            acc.setEmail(resultSet.getString("email"));
            acc.setPassword(resultSet.getString("password"));
            return acc;
        }, username);

        return account;
    }

    @Override
    public void createUserDetails(ConferenceUserDetails userDetails) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("INSERT INTO users (username, password, enabled) VALUES (?, ?, ?)", userDetails.getUsername(), userDetails.getPassword(), 1);

    }

    @Override
    public void createAuthorities(ConferenceUserDetails userDetails) {
        JdbcTemplate template = new JdbcTemplate(dataSource);

        for (GrantedAuthority grantedAuthority : userDetails.getAuthorities()) {
            template.update("INSERT INTO authorities(username, authority) VALUES (?, ?)", userDetails.getUsername(), grantedAuthority.getAuthority());
        }


    }

    @Override
    public void delete(Account account) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("DELETE FROM accounts WHERE username = ?", account.getUsername());
    }

    @Override
    public void deleteToken(String token) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("DELETE FROM verification_tokens WHERE token = ?", token);
    }
}
