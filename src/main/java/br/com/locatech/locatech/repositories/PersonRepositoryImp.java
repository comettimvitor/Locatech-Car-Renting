package br.com.locatech.locatech.repositories;

import br.com.locatech.locatech.entities.Person;
import br.com.locatech.locatech.entities.Vehicle;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepositoryImp implements PersonRepository{

    private final JdbcClient jdbcClient;

    public PersonRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Person> findById(Long id) {
        String sqlSelect = "SELECT * FROM person WHERE id = :id";

        return this.jdbcClient.sql(sqlSelect)
                .param("id", id)
                .query(Person.class)
                .optional();
    }

    @Override
    public List<Person> findAll(int size, int offset) {
        String sqlSelectAll = "SELECT * FROM person LIMIT :size OFFSET :offset";

        return this.jdbcClient.sql(sqlSelectAll)
                .param("size", size)
                .param("offset", offset)
                .query(Person.class)
                .list();
    }

    @Override
    public Integer save(Person person) {
        String sqlInsert = "INSERT INTO person(name, cpf, telephone, email) VALUES(:name, :cpf, :telephone, :email)";

        return this.jdbcClient.sql(sqlInsert)
                .param("name", person.getName())
                .param("cpf", person.getCpf())
                .param("telephone", person.getTelephone())
                .param("email", person.getEmail())
                .update();
    }

    @Override
    public Integer update(Person person, Long id) {
        String sqlUpdate = "UPDATE person SET name = :name, cpf = :cpf, telephone = :telephone, email = :email WHERE id = :id";

        return this.jdbcClient.sql(sqlUpdate)
                .param("id", id)
                .param("name", person.getName())
                .param("cpf", person.getCpf())
                .param("telephone", person.getTelephone())
                .param("email", person.getEmail())
                .update();
    }

    @Override
    public Integer delete(Long id) {
        String sqlDelete = "DELETE FROM person WHERE id = :id";

        return this.jdbcClient.sql(sqlDelete)
                .param("id", id)
                .update();
    }
}
