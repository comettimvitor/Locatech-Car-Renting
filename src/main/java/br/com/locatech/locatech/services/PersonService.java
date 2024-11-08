package br.com.locatech.locatech.services;

import br.com.locatech.locatech.entities.Person;
import br.com.locatech.locatech.entities.Vehicle;
import br.com.locatech.locatech.repositories.PersonRepository;
import br.com.locatech.locatech.repositories.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAllPeople(int page, int size) {
        int offset = (page -1) * size;

        return this.personRepository.findAll(size, offset);
    }

    public Optional<Person> findPersonById(Long id) {
        return this.personRepository.findById(id);
    }

    public void savePerson(Person person) {
        var save = this.personRepository.save(person);

        Assert.state(save == 1, "Error saving person" + person.getName());
    }

    public void updatePerson(Person person, Long id) {
        var update = this.personRepository.update(person, id);

        if(update == 0) {
            throw new RuntimeException("Person not found...");
        }
    }

    public void deletePerson(Long id) {
        var delete = this.personRepository.delete(id);

        if(delete == 0){
            throw new RuntimeException("Person not found...");
        }
    }
}
