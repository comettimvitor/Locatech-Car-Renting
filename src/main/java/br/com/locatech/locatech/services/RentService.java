package br.com.locatech.locatech.services;

import br.com.locatech.locatech.dto.RentRequestDTO;
import br.com.locatech.locatech.entities.Person;
import br.com.locatech.locatech.entities.Rent;
import br.com.locatech.locatech.entities.Vehicle;
import br.com.locatech.locatech.repositories.PersonRepository;
import br.com.locatech.locatech.repositories.RentRepository;
import br.com.locatech.locatech.repositories.VehicleRepository;
import br.com.locatech.locatech.services.exceptions.DateOutOfBoundsException;
import br.com.locatech.locatech.services.exceptions.ResourceNotFoundException;
import br.com.locatech.locatech.services.exceptions.VehicleNotAvailableException;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        int offset = (page - 1) * size;

        return this.rentRepository.findAll(size, offset);
    }

    public Optional<Rent> findRentById(Long id) {
        return Optional.ofNullable(this.rentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rent not found...")));
    }

    public void saveRent(RentRequestDTO rent) {
        var rentEntity = calculatesRent(rent);

        if (!rentRepository.isVehicleAvailable(rentEntity))
            throw new VehicleNotAvailableException("This Vehicle is not available for renting.");

        if (!endBiggerThanToday(rent.endDate()))
            throw new DateOutOfBoundsException("End Date cannot be Lower than today.");

        if (!endBiggerThanBegin(rent.beginDate(), rent.endDate()))
            throw new DateOutOfBoundsException("End Date cannot be lower than begin date.");

        if(!beginDateBiggerThanToday(rent.beginDate()))
            throw new DateOutOfBoundsException("Begin date must be bigger than today.");


        this.rentRepository.save(rentEntity);
    }

    public void updateRent(RentRequestDTO rent, Long id) {
        var rentEntity = calculatesRent(rent);

        var update = this.rentRepository.update(rentEntity, id);

        if (update == 0) {
            throw new ResourceNotFoundException("Rent not found...");
        }
    }

    public void deleteRent(Long id) {
        var delete = this.rentRepository.delete(id);

        if (delete == 0) {
            throw new ResourceNotFoundException("Rent not found...");
        }
    }

    private Rent calculatesRent(RentRequestDTO rentRequestDTO) {
        var vehicle = this.vehicleRepository.findById(rentRequestDTO.vehicleId()).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found..."));

        var quantityDays = BigDecimal.valueOf(rentRequestDTO.endDate().getDayOfYear() - rentRequestDTO.beginDate().getDayOfYear());

        var price = vehicle.getDailyPrice().multiply(quantityDays);

        return new Rent(rentRequestDTO, price);
    }

    private boolean endBiggerThanBegin(LocalDate beginDate, LocalDate endDate) {

        return endDate.isAfter(beginDate);
    }

    private boolean endBiggerThanToday(LocalDate endDate) {
        LocalDate today = LocalDate.now();

        return endDate.isAfter(today);
    }

    private boolean beginDateBiggerThanToday(LocalDate beginDate) {
        LocalDate today = LocalDate.now();

        return beginDate.isAfter(today);
    }
}
