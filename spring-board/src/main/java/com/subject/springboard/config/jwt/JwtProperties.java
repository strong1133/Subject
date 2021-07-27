package com.subject.springboard.config.jwt;

public interface JwtProperties {
    String SECRET = "daiblab";
    int EXPIRATION_TIME = 12340000;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
