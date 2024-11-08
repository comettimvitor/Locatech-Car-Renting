package br.com.locatech.locatech.services;

import br.com.locatech.locatech.entities.Vehicle;
import br.com.locatech.locatech.repositories.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> findAllVehicles(int page, int size) {
        int offset = (page -1) * size;

        return this.vehicleRepository.findAll(size, offset);
    }

    public Optional<Vehicle> findVehicleById(Long id) {
        return this.vehicleRepository.findById(id);
    }

    public void saveVehicle(Vehicle vehicle) {
        var save = this.vehicleRepository.save(vehicle);

        Assert.state(save == 1, "Error saving vehicles" + vehicle.getBrand());
    }

    public void updateVehicle(Vehicle vehicle, Long id) {
        var update = this.vehicleRepository.update(vehicle, id);

        if(update == 0) {
            throw new RuntimeException("Vehicle not found...");
        }
    }

    public void deleteVehicle(Long id) {
        var delete = this.vehicleRepository.delete(id);

        if(delete == 0){
            throw new RuntimeException("Vehicle not found...");
        }
    }
}
