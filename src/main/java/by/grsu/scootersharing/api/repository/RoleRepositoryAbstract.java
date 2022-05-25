package by.grsu.scootersharing.api.repository;

import by.grsu.scootersharing.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepositoryAbstract extends JpaRepository<Role,Long> {
    Role getByName(String name);
}
