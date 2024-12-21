package org.trollheim.game.data.models;

import org.springframework.security.core.GrantedAuthority;

public enum GrantedAuthorities implements GrantedAuthority {
    USER("ROLE_USER");
    private final String role;

    GrantedAuthorities(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
