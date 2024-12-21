package org.trollheim.game.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.trollheim.game.data.models.AppUser;
import org.trollheim.game.data.repository.AppUserRepository;

//@SpringBootApplication
public class BbLauncher {

    public static void main(String[] args) {
        SpringApplication.run(BbLauncher.class, args);
    }
//    @Bean
    public CommandLineRunner demo(AppUserRepository repository) {
        return (args) -> {
            // save a few customers


            repository.save(new AppUser("Jack", "Bauer"));
            repository.save(new AppUser("Chloe", "O'Brian"));
            repository.save(new AppUser("Kim", "Bauer"));
            repository.save(new AppUser("David", "Palmer"));
            repository.save(new AppUser("Michelle", "Dessler"));

            // fetch all customers
            System.out.println("Customers found with findAll():");
            System.out.println("-------------------------------");
            for (AppUser customer : repository.findAll()) {
                System.out.println(customer.toString());
            }


            // fetch an individual customer by ID
            AppUser customer = repository.findById(1L).get();
            System.out.println("Customer found with findById(1L):");
            System.out.println("--------------------------------");
            System.out.println(customer.toString());
            System.out.println("");

            // fetch customers by last name
            System.out.println("Customer found with findByLastName('Bauer'):");
            System.out.println("--------------------------------------------");


        };
    }

}