package org.trollheim.game.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

                .formLogin()
//              .loginPage("/login.html")
//              .loginProcessingUrl("/perform_login")
              .defaultSuccessUrl("/", true)
              .failureUrl("/login.html?error=true")
//              .failureHandler(authenticationFailureHandler())
              .and()
              .logout()
              .logoutUrl("/logout")
              .deleteCookies("JSESSIONID").and().authorizeRequests().anyRequest().authenticated();
//              .logoutSuccessHandler(logoutSuccessHandler());
    }
}