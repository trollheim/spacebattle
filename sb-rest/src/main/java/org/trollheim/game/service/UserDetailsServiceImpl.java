package org.trollheim.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.trollheim.game.data.models.AppUser;
import org.trollheim.game.data.models.InviteCode;
import org.trollheim.game.data.repository.AppUserRepository;
import org.trollheim.game.data.repository.InviteCodeRepository;
import org.trollheim.game.dto.CreateUserDto;

import java.util.Collections;
import java.util.Map;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Map<String, String> RESULT_OK = Collections.singletonMap("RESULT", "OK");
    private static final Map<String, String> RESULT_FAILED = Collections.singletonMap("RESULT", "FAILED");


    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private InviteCodeRepository inviteCodeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser userDetails = appUserRepository.findByUsername(username);
        if (username == null) {
            throw new UsernameNotFoundException("User does not exist");
        }
        return userDetails;
    }


    public Map<String, String> createUser(CreateUserDto createUserDto) {
        InviteCode inviteCode = inviteCodeRepository.findByInviteCode(createUserDto.getInviteCode());
        if (inviteCode == null) {
            return RESULT_FAILED;
        }
        String password = passwordEncoder.encode(createUserDto.getPassword());
        AppUser appUser = new AppUser(createUserDto.getUsername(), password);
        appUserRepository.save(appUser);
        inviteCodeRepository.delete(inviteCode);
        return RESULT_OK;
    }
}
