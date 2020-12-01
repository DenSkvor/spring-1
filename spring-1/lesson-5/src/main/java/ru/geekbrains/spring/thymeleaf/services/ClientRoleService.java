package ru.geekbrains.spring.thymeleaf.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.thymeleaf.models.Client;
import ru.geekbrains.spring.thymeleaf.models.ClientRole;
import ru.geekbrains.spring.thymeleaf.models.Role;
import ru.geekbrains.spring.thymeleaf.repositories.ClientRoleRepository;

import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
public class ClientRoleService {

    private ClientRoleRepository clientRoleRepository;

    public List<ClientRole> findAll(){
        return clientRoleRepository.findAll();
    }

    public boolean removeClientsRole(Long clientId, String roleName){
        List<ClientRole> clientRoles = findAll();
        Iterator<ClientRole> iter = clientRoles.iterator();
        ClientRole clientRole;
        while (iter.hasNext()){
            clientRole = iter.next();
            if(clientRole.getClient().getId().equals(clientId) &&
                    clientRole.getRole().getName().equals(roleName)) {
                clientRoleRepository.delete(clientRole);
                return true;
            }
        }
        return false;
    }

    public void add(Client client, Role role){
        clientRoleRepository.save(new ClientRole(client, role));
    }
}
