package br.com.locatech.locatech.services;

import br.com.locatech.locatech.dto.RentRequestDTO;
import br.com.locatech.locatech.dto.ResourceNotFoundDTO;
import br.com.locatech.locatech.dto.VehicleRequestDTO;
import br.com.locatech.locatech.entities.Rent;
import br.com.locatech.locatech.entities.Vehicle;
import br.com.locatech.locatech.repositories.RentRepository;
import br.com.locatech.locatech.repositories.RentRepositoryImp;
import br.com.locatech.locatech.repositories.VehicleRepository;
import br.com.locatech.locatech.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    private final RentRepository rentRepository;

    public VehicleService(VehicleRepository vehicleRepository, RentRepository rentRepository) {
        this.vehicleRepository = vehicleRepository;
        this.rentRepository = rentRepository;
    }

    public List<Vehicle> findAllVehicles(int page, int size) {
        int offset = (page -1) * size;

        return this.vehicleRepository.findAll(size, offset);
    }

    public Optional<Vehicle> findVehicleById(Long id) {
        return Optional.ofNullable(this.vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found...")));
    }

    public void saveVehicle(VehicleRequestDTO vehicleRequestDTO) {
        var vehicleEntity = returnsVehicleData(vehicleRequestDTO);

        var save = this.vehicleRepository.save(vehicleEntity);

        Assert.state(save == 1, "Error saving vehicles" + vehicleRequestDTO.brand());
    }

    public void updateVehicle(VehicleRequestDTO vehicle, Long id) {
        var vehicleEntity = returnsVehicleData(vehicle);

        var update = this.vehicleRepository.update(vehicleEntity, id);

        if(update == 0) {
            throw new ResourceNotFoundException("Vehicle not found...");
        }
    }

    public void deleteVehicle(Long id) {
        var delete = this.vehicleRepository.delete(id);

        if(delete == 0){
            throw new ResourceNotFoundException("Vehicle not found...");
        }
    }

    private Vehicle returnsVehicleData(VehicleRequestDTO vehicleRequestDTO) {
        return new Vehicle(vehicleRequestDTO);
    }
}
