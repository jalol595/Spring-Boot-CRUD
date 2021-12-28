package uz.pdp.appRegion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appRegion.entity.Region;
import uz.pdp.appRegion.repository.RegionRepository;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    RegionRepository regionRepository;

    @PostMapping
    public String save(@RequestBody Region region) {
        boolean exists = regionRepository.existsByName(region.getName());
        if (exists) return "already exist";

        regionRepository.save(region);
        return "saved";
    }

    @GetMapping
    public List<Region> get(){
        List<Region> regionList = regionRepository.findAll();
        return regionList;
    }

    @DeleteMapping("/{id}")
    public String deleted(@PathVariable Integer id){
        if(!regionRepository.existsById(id)){
            return "not found id";
        }

        regionRepository.deleteById(id);
        return "deleted";
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody Region region){
        if (!regionRepository.existsById(id)){
            return "not found id";
        }

        Region editRegion=new Region(null,region.getName());
        regionRepository.save(editRegion);
        return "editing";
    }


}
