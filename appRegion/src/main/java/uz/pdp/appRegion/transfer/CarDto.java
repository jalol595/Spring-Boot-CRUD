package uz.pdp.appRegion.transfer;

import lombok.Data;
import uz.pdp.appRegion.entity.Region;
import uz.pdp.appRegion.entity.Users;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
@Data
public class CarDto {

    private String model;

    private Integer stateNumber;


    private Integer madeYear;

    private String type;

    private Integer regionId;

    private Integer usersId;

}
