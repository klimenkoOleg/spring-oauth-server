package com.oklimenko.authserver.config;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.HashSet;
import java.util.Set;

public class JwtTokenStoreWithRevoke extends JwtTokenStore {

    // TODO USE persistent storage (JDBC or Redis or Hazlecast) for pods space > 1 !!!
    public static final Set tokenStorage = new HashSet();

    public JwtTokenStoreWithRevoke(JwtAccessTokenConverter jwtTokenEnhancer) {
        super(jwtTokenEnhancer);
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken token) {
        super.removeAccessToken(token);
        tokenStorage.remove(token);
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken c, OAuth2Authentication authentication) {
        super.storeAccessToken(c, authentication);
        tokenStorage.add(c);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        OAuth2AccessToken accessToken = super.readAccessToken(tokenValue);
        if ( !tokenStorage.contains(accessToken) ) {
            throw new InvalidTokenException("Token was revoked");
        }
        return accessToken;
    }
}
