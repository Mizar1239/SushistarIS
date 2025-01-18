package com.Sushistar.SushistarIS.repo;

import com.Sushistar.SushistarIS.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepo extends JpaRepository<Shipment, Long> {
}