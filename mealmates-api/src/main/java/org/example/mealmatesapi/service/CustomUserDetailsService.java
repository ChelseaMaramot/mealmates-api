package org.example.mealmatesapi.service;

import org.example.mealmatesapi.model.User;
import org.example.mealmatesapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;
@Service
public class CustomUserDetailsService  implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("looking for email..." + email);
        User user = userRepository.findByEmail(email);

        System.out.print("found email "+ email);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );
    }

    public String getUsernameFromEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user.getUsername();
    }
}