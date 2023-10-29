package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.models.ParkingSpotModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {
    //    @Query("SELECT CASE WHEN COUNT(pSM) > 0 THEN true ELSE false END FROM ParkingSpotModel pSM WHERE pSM.licensePlateCar = :licensePlateCar")
    boolean existsByLicensePlateCar(String licensePlateCar);

    boolean existsByParkingSpotNumber(String parkingSpotNumber);

    boolean existsByApartmentAndBlock(String apartment, String block);

    @Query("SELECT pSM FROM ParkingSpotModel pSM")
    Page<ParkingSpotModel> findPaginatedData(Pageable pageable);
}
