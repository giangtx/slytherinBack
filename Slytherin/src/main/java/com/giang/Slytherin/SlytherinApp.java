package com.giang.Slytherin;

import com.giang.Slytherin.model.*;
import com.giang.Slytherin.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class SlytherinApp {
    public static void main(String[] args) {
        SpringApplication.run(SlytherinApp.class,args);
    }
}
