package uz.pdp.appRegion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appRegion.entity.Address;
import uz.pdp.appRegion.repository.AddressRepository;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressRepository addressRepository;

    @PostMapping
    public String save(@RequestBody Address address) {
        boolean exists = addressRepository.existsByKochaAndNumber(address.getKocha(), address.getNumber());
        if (exists) return "alredy exist";

        addressRepository.save(address);
        return "Saved";
    }


    @GetMapping
    public List<Address> get() {
        List<Address> addressList = addressRepository.findAll();
        return addressList;
    }

    @DeleteMapping("/{id}")
    public String deleted(@PathVariable Integer id) {
        if (!addressRepository.existsById(id)) {
            return "not found id";
        }

        addressRepository.deleteById(id);
        return "deleted";
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody Address address) {
        if (!addressRepository.existsById(id)) {
            return "not found id";
        }

        Address editAddress = new Address(null, address.getKocha(), address.getNumber());
        addressRepository.save(editAddress);
        return "editing";
    }
}
