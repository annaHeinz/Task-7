package ru.itmentor.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itmentor.spring.boot_security.demo.service.UserService;

@SpringBootApplication
//implements CommandLineRunner
public class SpringBootSecurityDemoApplication {
    @Autowired private UserService userService;
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
    }
//    @Override
//    public void run(String... args) throws Exception {
//        userService.createAdminUser();
//    }
}

