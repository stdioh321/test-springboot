package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.services.ParkingSpotService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.UUID;

@RestController()
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {
    @Autowired
    private ParkingSpotService parkingSpotService;

    @GetMapping
    public ResponseEntity<?> get(@PageableDefault(page = 0, size = 15, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(this.parkingSpotService.getPaginated(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") UUID id) {
        var result = this.parkingSpotService.getById(id);
        if (result == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(this.parkingSpotService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") UUID id) {
        var result = this.parkingSpotService.getById(id);
        if (result == null) return ResponseEntity.notFound().build();
        this.parkingSpotService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putById(@PathVariable("id") UUID id, @Valid @RequestBody ParkingSpotDto parkingSpotDto) {
        var result = this.parkingSpotService.getById(id);
        if (result == null) return ResponseEntity.notFound().build();

        var updatedParkingSpot = this.parkingSpotService.updateById(id, parkingSpotDto.toEntity());
        return ResponseEntity.ok(updatedParkingSpot);
    }

    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody ParkingSpotDto parkingSpotDto, BindingResult bindingResult) {
        if (bindingResult.getErrorCount() > 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data");

        if (this.parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar().toLowerCase()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Licence Plate Car is already in use!");
        if (this.parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot Number is already in use!");
        if (this.parkingSpotService.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot already registered!");


        ParkingSpotModel createdParkingSpot = this.parkingSpotService.save(parkingSpotDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdParkingSpot);
    }

}
