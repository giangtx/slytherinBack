package com.giang.Slytherin;

import com.giang.Slytherin.model.*;
import com.giang.Slytherin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class SlytherinApp {
    public static void main(String[] args) {
        SpringApplication.run(SlytherinApp.class,args);
    }


}
