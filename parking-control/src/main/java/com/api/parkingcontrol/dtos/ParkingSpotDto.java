package com.api.parkingcontrol.dtos;

import com.api.parkingcontrol.models.ParkingSpotModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingSpotDto {
    @NotBlank
    private String parkingSpotNumber;
    @NotBlank
    @Size(min = 7)
    private String licensePlateCar;
    @NotBlank
    private String brandCar;
    @NotBlank
    private String modelCar;
    @NotBlank
    private String colorCar;
    @NotBlank
    private String responsibleName;
    @NotBlank
    private String apartment;
    @NotBlank
    private String block;

    public ParkingSpotModel toEntity() {
        return ParkingSpotModel.builder()
                .parkingSpotNumber(this.parkingSpotNumber)
                .licensePlateCar(this.licensePlateCar)
                .brandCar(this.brandCar)
                .modelCar(this.modelCar)
                .colorCar(this.colorCar)
                .responsibleName(this.responsibleName)
                .apartment(this.apartment)
                .block(this.block)
                .build();

    }
}
