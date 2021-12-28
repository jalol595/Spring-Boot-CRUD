package uz.pdp.appRegion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appRegion.entity.Address;
import uz.pdp.appRegion.entity.Users;
import uz.pdp.appRegion.repository.AddressRepository;
import uz.pdp.appRegion.repository.RegionRepository;
import uz.pdp.appRegion.repository.UsersRepository;
import uz.pdp.appRegion.transfer.UsersDto;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    AddressRepository addressRepository;

    @PostMapping
    public String save(@RequestBody UsersDto usersDto) {
        boolean byName = usersRepository.existsByName(usersDto.getName());
        boolean andNumber = addressRepository.existsByKochaAndNumber(usersDto.getKocha(), usersDto.getNumber());
        if (byName && andNumber) return "already exist";

        Address address = new Address(null, usersDto.getKocha(), usersDto.getNumber());
        Address save = addressRepository.save(address);

        Users users = new Users(null, usersDto.getName(), save);
        usersRepository.save(users);
        return "saved";
    }

    @GetMapping
    public List<Users> get() {
        List<Users> usersList = usersRepository.findAll();
        return usersList;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        if (!usersRepository.existsById(id)) {
            return "not found";
        }

        usersRepository.deleteById(id);
        return "deleted";
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody UsersDto usersDto) {
        if (!usersRepository.existsById(id)) {
            return "not found";
        }

        Address address = new Address(null, usersDto.getKocha(), usersDto.getNumber());

        Users users = new Users(null, usersDto.getName(), address);
        usersRepository.save(users);
        return "editing";

    }


}
