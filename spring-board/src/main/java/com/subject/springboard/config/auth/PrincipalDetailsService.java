package com.subject.springboard.config.auth;


import com.subject.springboard.board.User;
import com.subject.springboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        System.out.println("PrincipalDetailsService : 진입");
        User user = userRepository.findByUsername(username);
        return new PrincipalDetails(user);
    }
}
