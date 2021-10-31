package org.trollheim.game;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.trollheim.game.data.repository.AppUserRepository;

@SpringBootApplication
public class Launcher {
    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

    @Bean
    public CommandLineRunner demo(AppUserRepository repository, PasswordEncoder passwordEncoder) {
        return (args) -> {
            // save a few customers
//            repository.save(new AppUser("user1", passwordEncoder.encode("password1")));
            repository.findAll().stream().forEach(user -> System.out.println(user.getId() + " " + user.getUsername() + " " + user.getPassword()));
        };
    }
}
