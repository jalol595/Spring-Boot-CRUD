package uz.pdp.appRegion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appRegion.entity.Region;

public interface RegionRepository extends JpaRepository<Region, Integer> {
    boolean existsByName(String name);
}
