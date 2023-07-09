package org.trollheim.game.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfiguration implements WebMvcConfigurer {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http

//                .cors().and().csrf().disable()
                //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

//                .formLogin(Customizer.withDefaults())
////              .loginPage("/login.html")
////              .loginProcessingUrl("/perform_login")
////              .defaultSuccessUrl("/", true)
////              .failureUrl("/login.html?error=true")
////              .failureHandler(authenticationFailureHandler())
////              .and()
//                .logout(Customizer.withDefaults())
                .authorizeRequests(authorize -> authorize.requestMatchers("/register/**", "/userAPI/register").permitAll()
                        .anyRequest().authenticated()
                )
//              .logoutUrl("/logout")
//                  .deleteCookies("JSESSIONID").and()
//                  .authorizeRequests()
//                    .antMatchers("/register/**")
//                    .permitAll().and().authorizeRequests()
//                .antMatchers("/userAPI/register")
//                .permitAll()
//                .anyRequest().authenticated()


        ;
        return http.build();
//              .logoutSuccessHandler(logoutSuccessHandler());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("register").setViewName("forward:register/index.html");
    }


}