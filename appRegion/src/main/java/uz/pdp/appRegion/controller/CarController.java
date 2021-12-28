package uz.pdp.appRegion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appRegion.entity.Car;
import uz.pdp.appRegion.entity.Region;
import uz.pdp.appRegion.entity.Users;
import uz.pdp.appRegion.repository.CarRepository;
import uz.pdp.appRegion.repository.RegionRepository;
import uz.pdp.appRegion.repository.UsersRepository;
import uz.pdp.appRegion.transfer.CarDto;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    UsersRepository usersRepository;

    @PostMapping
    public String save(@RequestBody CarDto carDto) {
        boolean exists = carRepository.existsByModelAndAndStateNumberAndMadeYearAndType
                (carDto.getModel(), carDto.getStateNumber(), carDto.getMadeYear(), carDto.getType());
        boolean byId = regionRepository.existsById(carDto.getRegionId());
        boolean existsById = usersRepository.existsById(carDto.getUsersId());
        if (exists && byId && existsById) return "already exist";

        Optional<Region> id = regionRepository.findById(carDto.getRegionId());
        if (!id.isPresent()) {
            return "not found id region";
        }

        Region region = id.get();

        Optional<Users> users = usersRepository.findById(carDto.getUsersId());
        if (users.isPresent()) {
            return "not found id users";
        }

        Users users1 = users.get();

        Car car = new Car(null, carDto.getModel(), carDto.getStateNumber(), carDto.getMadeYear(), carDto.getType(), region, users1);
        carRepository.save(car);
        return "saved";
    }

    @GetMapping
    public List<Car> get() {
        List<Car> all = carRepository.findAll();
        return all;
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        if(!carRepository.existsById(id)){
            return "not found";
        }

        carRepository.deleteById(id);
        return "deleted";
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody CarDto carDto){
        if(!carRepository.existsById(id)){
            return "not found";
        }

       Optional<Region> byId = regionRepository.findById(carDto.getRegionId());
        if (!byId.isPresent()) {
            return "not found id";
        }

        Region region = byId.get();

        Optional<Users> users = usersRepository.findById(carDto.getUsersId());
        if (users.isPresent()) {
            return "not found id";
        }

        Users users1 = users.get();

        Car car = new Car(null, carDto.getModel(), carDto.getStateNumber(), carDto.getMadeYear(), carDto.getType(), region, users1);
        carRepository.save(car);
        return "edited";


    }

}
