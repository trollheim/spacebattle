package org.trollheim.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.trollheim.game.data.models.AppUser;
import org.trollheim.game.data.repository.AppUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser userDetails = appUserRepository.findByUsername(username);
        return userDetails;
    }
}
