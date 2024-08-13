package com.jjmontenegrop.springbootcrud.security;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

public class TokenJwtConfig {

    // * Create a secret key to sign the JWT token
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
    public static final Date EXPIRATION_TIME = new Date(System.currentTimeMillis() + 3600000);
}
