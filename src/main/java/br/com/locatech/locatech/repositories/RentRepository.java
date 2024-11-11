package br.com.locatech.locatech.repositories;

import br.com.locatech.locatech.entities.Person;
import br.com.locatech.locatech.entities.Rent;

import java.util.List;
import java.util.Optional;

public interface RentRepository {

    Optional<Rent> findById(Long id);
    List<Rent> findAll(int size, int offset);
    Integer save(Rent rent);
    Integer update(Rent rent, Long id);
    Integer delete(Long id);
    boolean isVehicleAvailable(Rent rent);

}
