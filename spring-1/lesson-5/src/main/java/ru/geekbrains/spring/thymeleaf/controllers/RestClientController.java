package ru.geekbrains.spring.thymeleaf.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.spring.thymeleaf.models.Client;
import ru.geekbrains.spring.thymeleaf.services.ClientService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/client")
@AllArgsConstructor
public class RestClientController {

    private ClientService clientService;

    @GetMapping
    public String[] showClientInfo(Principal principal){
        Client client = clientService.findByName(principal.getName());
        return new String[]{client.getName(), client.getPhoneNumber(), client.getEmail()};
    }
}
