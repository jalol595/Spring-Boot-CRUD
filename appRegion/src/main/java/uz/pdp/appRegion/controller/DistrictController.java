package uz.pdp.appRegion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appRegion.entity.District;
import uz.pdp.appRegion.entity.Region;
import uz.pdp.appRegion.repository.DistrictRepository;
import uz.pdp.appRegion.repository.RegionRepository;
import uz.pdp.appRegion.transfer.DistrictDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    RegionRepository regionRepository;

    @PostMapping
    public String Save(@RequestBody DistrictDto districtDto) {
        boolean byName = districtRepository.existsByName(districtDto.getName());
        boolean byId = regionRepository.existsById(districtDto.getRegionId());
        if (byName && byId) return "already exist";

        Optional<Region> optionalRegion = regionRepository.findById(districtDto.getRegionId());
        if (!optionalRegion.isPresent()) {
            return "region not found";
        }
        Region region = optionalRegion.get();

        District district = new District(null, districtDto.getName(), region);
        districtRepository.save(district);
        return "saved";
    }

    @GetMapping
    public List<District> get() {
        List<District> districtList = districtRepository.findAll();
        return districtList;
    }


    @DeleteMapping("/{id}")
    public String deleted(@PathVariable Integer id) {
        if (!districtRepository.existsById(id)) {
            return "not found id";
        }

        districtRepository.deleteById(id);
        return "deleted";
    }


    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody DistrictDto districtDto) {
        if (!districtRepository.existsById(id)) {
            return "not found id";
        }

        Optional<Region> optionalRegion = regionRepository.findById(districtDto.getRegionId());
        if (!optionalRegion.isPresent()) {
            return "region not found";
        }
        Region region = optionalRegion.get();

        District district = new District(null, districtDto.getName(), region);
        districtRepository.save(district);
        return "edited";

    }
}
