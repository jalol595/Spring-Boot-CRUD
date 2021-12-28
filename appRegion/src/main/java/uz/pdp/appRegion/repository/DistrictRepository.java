package uz.pdp.appRegion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appRegion.entity.District;

public interface DistrictRepository extends JpaRepository<District, Integer> {
    boolean existsByName(String name);
}
