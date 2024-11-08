package br.com.locatech.locatech.services;

import br.com.locatech.locatech.dto.RentRequestDTO;
import br.com.locatech.locatech.entities.Person;
import br.com.locatech.locatech.entities.Rent;
import br.com.locatech.locatech.repositories.PersonRepository;
import br.com.locatech.locatech.repositories.RentRepository;
import br.com.locatech.locatech.repositories.VehicleRepository;
import br.com.locatech.locatech.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class RentService {

    private final RentRepository rentRepository;
    private final VehicleRepository vehicleRepository;

    public RentService(RentRepository rentRepository, VehicleRepository vehicleRepository) {
        this.rentRepository = rentRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public List<Rent> findAllRents(int page, int size) {
        int offset = (page -1) * size;

        return this.rentRepository.findAll(size, offset);
    }

    public Optional<Rent> findRentById(Long id) {
        return Optional.ofNullable(this.rentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rent not found...")));
    }

    public void saveRent(RentRequestDTO rent) {
        var rentEntity = calculatesRent(rent);

        var save = this.rentRepository.save(rentEntity);

        Assert.state(save == 1, "Error saving rent...");
    }

    public void updateRent(Rent rent, Long id) {
        var update = this.rentRepository.update(rent, id);

        if(update == 0) {
            throw new RuntimeException("Rent not found...");
        }
    }

    public void deleteRent(Long id) {
        var delete = this.rentRepository.delete(id);

        if(delete == 0){
            throw new RuntimeException("Rent not found...");
        }
    }

    private Rent calculatesRent(RentRequestDTO rentRequestDTO) {
        var vehicle = this.vehicleRepository.findById(rentRequestDTO.vehicleId()).orElseThrow(() -> new RuntimeException("Vehicle not found..."));

        var quantityDays = BigDecimal.valueOf(rentRequestDTO.endDate().getDayOfYear() - rentRequestDTO.beginDate().getDayOfYear());

        var price = vehicle.getDailyPrice().multiply(quantityDays);

        return new Rent(rentRequestDTO, price);
    }
}
