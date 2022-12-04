package toproject.toy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toproject.toy.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
