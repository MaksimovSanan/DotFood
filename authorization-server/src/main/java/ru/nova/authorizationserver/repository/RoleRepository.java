package ru.nova.authorizationserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nova.authorizationserver.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
