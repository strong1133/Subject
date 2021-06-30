package com.daib.backend.config.security;


import com.daib.backend.domain.board.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user){this.user = user;}

    public User getUser(){return user;}

    @Override
    public String getPassword(){return  user.getPassword();}

    @Override
    public String getUsername(){return  user.getUsername();}

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){return Collections.emptyList();}


}
