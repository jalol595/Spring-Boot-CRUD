package uz.pdp.appRegion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appRegion.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    boolean existsByName(String name);
}
