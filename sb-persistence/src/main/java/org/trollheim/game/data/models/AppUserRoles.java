package org.trollheim.game.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class AppUserRoles implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
