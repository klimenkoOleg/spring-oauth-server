package com.oklimenko.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

public class ManageableJwtTokenStore extends JwtTokenStore {

    @Autowired
    private JdbcTokenStore jdbcTokenStore;

    public ManageableJwtTokenStore(JwtAccessTokenConverter jwtTokenEnhancer) {
        super(jwtTokenEnhancer);
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
        OAuth2RefreshToken token = jdbcTokenStore.readRefreshToken(tokenValue);
        return token;
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {

        jdbcTokenStore.storeRefreshToken(refreshToken, authentication);
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken token) {
        jdbcTokenStore.removeRefreshToken(token);
//        ;
    }
}