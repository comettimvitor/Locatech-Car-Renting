package br.com.locatech.locatech.repositories;


import br.com.locatech.locatech.entities.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository {

    Optional<Vehicle> findById(Long id);
    List<Vehicle> findAll(int size, int offset);
    Integer save(Vehicle vehicle);
    Integer update(Vehicle vehicle, Long id);
    Integer delete(Long id);
}
