package uz.pdp.appRegion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appRegion.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    boolean existsByKochaAndNumber(String kocha, Integer number);
}
