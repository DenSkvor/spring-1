package ru.geekbrains.spring.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ru.geekbrains.spring.test")
public class ClientAppConfig {
    @Bean
    public RemoteRegistration remoteRegistration(){
        return new RemoteRegistration();
    }
}
