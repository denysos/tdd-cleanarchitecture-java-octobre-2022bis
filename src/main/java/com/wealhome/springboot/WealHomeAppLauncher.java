package com.wealhome.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.wealhome")
@EntityScan("com.wealhome.models")
@EnableJpaRepositories("com.wealhome.repositories")
public class WealHomeAppLauncher {

    public static void main(String[] args) {
        SpringApplication.run(WealHomeAppLauncher.class, args);
    }

}
