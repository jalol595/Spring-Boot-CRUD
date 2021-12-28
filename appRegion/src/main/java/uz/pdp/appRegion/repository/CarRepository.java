package uz.pdp.appRegion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appRegion.entity.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {

    boolean existsByModelAndAndStateNumberAndMadeYearAndType(String model, Integer stateNumber, Integer madeYear, String  type);

}
