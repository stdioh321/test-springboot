package com.api.parkingcontrol.services;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ParkingSpotService {
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;


    @Transactional
    public ParkingSpotModel save(ParkingSpotDto parkingSpotDto) {
        var parkingSpotModel = parkingSpotDto.toEntity();
        return this.parkingSpotRepository.save(parkingSpotModel);
    }

    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return this.parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return this.parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return this.parkingSpotRepository.existsByApartmentAndBlock(apartment, block);
    }

    public List<ParkingSpotModel> get() {
        return this.parkingSpotRepository.findAll();
    }

    public Page<ParkingSpotModel> getPaginated(Pageable pageable) {
        return this.parkingSpotRepository.findPaginatedData(pageable);
    }

    public ParkingSpotModel getById(UUID id) {
        return this.parkingSpotRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteById(UUID id) {
        var parkingSpotModel = new ParkingSpotModel();
        parkingSpotModel.setId(id);
        this.parkingSpotRepository.delete(parkingSpotModel);
    }

    @Transactional
    public ParkingSpotModel updateById(UUID id, ParkingSpotModel entity) {
        var currEntity = this.getById(id);

        currEntity.setParkingSpotNumber(entity.getParkingSpotNumber());
        currEntity.setBlock(entity.getBlock());
        currEntity.setApartment(entity.getApartment());
        currEntity.setBrandCar(entity.getBrandCar());
        currEntity.setColorCar(entity.getColorCar());
        currEntity.setModelCar(entity.getModelCar());
        currEntity.setLicensePlateCar(entity.getLicensePlateCar());
        currEntity.setResponsibleName(entity.getResponsibleName());

        return this.parkingSpotRepository.save(currEntity);
    }
}
