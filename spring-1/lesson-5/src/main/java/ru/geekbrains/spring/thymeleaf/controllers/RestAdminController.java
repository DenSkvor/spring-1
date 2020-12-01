package ru.geekbrains.spring.thymeleaf.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.thymeleaf.models.Client;
import ru.geekbrains.spring.thymeleaf.models.Role;
import ru.geekbrains.spring.thymeleaf.services.ClientRoleService;
import ru.geekbrains.spring.thymeleaf.services.ClientService;
import ru.geekbrains.spring.thymeleaf.services.RoleService;


@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
public class RestAdminController {

    private ClientService clientService;
    private ClientRoleService clientRoleService;
    private RoleService roleService;

    @PutMapping("/{id}")
    public String blockClient(@PathVariable Long id){
        Client client = clientService.findById(id);
        Role role = roleService.findByName("ROLE_BLOCKED_USER");

        if(clientRoleService.removeClientsRole(id, "ROLE_USER")){
            clientRoleService.add(client, role);
            return "Клиент заблокирован.";
        }
        return "Клиент не найден.";
    }
}
