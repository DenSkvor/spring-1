package ru.geekbrains.spring.thymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.thymeleaf.models.ClientRole;

@Repository
public interface ClientRoleRepository extends JpaRepository<ClientRole, Long> {
}
