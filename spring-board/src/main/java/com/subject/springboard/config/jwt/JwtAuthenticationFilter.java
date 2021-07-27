package com.subject.springboard.config.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.subject.springboard.config.auth.PrincipalDetails;
import com.subject.springboard.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter : 진입");
        ObjectMapper om = new ObjectMapper(); // 로그인 포스트 요청시 입력값을 JSON 으로 받기 위함.
        LoginRequestDto loginRequestDto = null; // 값을 포장해줄 loginRequestDto 초기화
        try {
            // loginRequestDto에 읽어온 값을 JSON 형태로 매칭 시킴
            loginRequestDto = om.readValue(request.getInputStream(), LoginRequestDto.class);
            System.out.println("loginRequestDto :" + loginRequestDto);
        }catch (Exception e) {
            System.out.println("로그인 실패");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequestDto.getUsername(),
                loginRequestDto.getPassword()); ///loginRequestDto에 담긴 값들로 임시 토큰을 생성

        System.out.println("JwtAuthenticationFilter : 토큰생성완료");
        System.out.println("UsernamePasswordAuthenticationToken : " + authenticationToken);

        Authentication authentication = null;

        authentication = authenticationManager.authenticate(authenticationToken);

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        System.out.println("Authentication : " + principalDetails.getUser().getUsername());
        System.out.println("UsernamePasswordAuthenticationToken : " + authentication);
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, SecurityException{

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME))
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("username",principalDetails.getUser().getUsername())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        System.out.println(jwtToken);

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
    }
}

