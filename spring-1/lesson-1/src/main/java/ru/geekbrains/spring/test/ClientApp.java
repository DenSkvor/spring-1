package ru.geekbrains.spring.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClientApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ClientAppConfig.class);

        RemoteRegistration remoteRegistration = context.getBean("remoteRegistration", RemoteRegistration.class);
        remoteRegistration.register("Терапевт");
        remoteRegistration.register("Лор");
    }

}
