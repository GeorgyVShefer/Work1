package org.example.spsec.config;

import org.example.spsec.entity.UserCredentional;
import org.example.spsec.repository.UserCredentionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private  UserCredentionalRepository userCredentionalRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserCredentional> credentional = userCredentionalRepository.findByUsername(username);

        return credentional.map(CustomUserDetail::new).orElseThrow(()-> new RuntimeException("user not found"));
    }
}
