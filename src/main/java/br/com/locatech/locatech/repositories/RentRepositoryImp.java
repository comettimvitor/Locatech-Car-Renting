package br.com.locatech.locatech.repositories;

import br.com.locatech.locatech.entities.Person;
import br.com.locatech.locatech.entities.Rent;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RentRepositoryImp implements RentRepository{

    private final JdbcClient jdbcClient;

    public RentRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Rent> findById(Long id) {
        String sqlSelect = "SELECT r.id, r.person_id, r.vehicle_id, r.begin_date, r.end_date, r.total_price, " +
                "p.name as person_name, p.cpf as person_cpf, " +
                "v.model as vehicle_model, v.plate as vehicle_plate " +
                "FROM rent r " +
                "INNER JOIN person p on r.person_id = p.id " +
                "INNER JOIN vehicles v on r.vehicle_id = v.id " +
                "WHERE r.id = :id";

        return this.jdbcClient.sql(sqlSelect)
                .param("id", id)
                .query(Rent.class)
                .optional();
    }

    @Override
    public List<Rent> findAll(int size, int offset) {
        String sqlSelectAll = "SELECT r.id, r.person_id, r.vehicle_id, r.begin_date, r.end_date, r.total_price, " +
                "p.name as person_name, p.cpf as person_cpf, " +
                "v.model as vehicle_model, v.plate as vehicle_plate " +
                "FROM rent r " +
                "INNER JOIN person p on r.person_id = p.id " +
                "INNER JOIN vehicles v on r.vehicle_id = v.id " +
                "LIMIT :size OFFSET :offset";

        return this.jdbcClient.sql(sqlSelectAll)
                .param("size", size)
                .param("offset", offset)
                .query(Rent.class)
                .list();
    }

    @Override
    public Integer save(Rent rent) {
        String sqlInsert = "INSERT INTO rent(person_id, vehicle_id, begin_date, end_date, total_price) VALUES(:person_id, :vehicle_id, :begin_date, :end_date, :total_price)";

        return this.jdbcClient.sql(sqlInsert)
                .param("person_id", rent.getPersonId())
                .param("vehicle_id", rent.getVehicleId())
                .param("begin_date", rent.getBeginDate())
                .param("end_date", rent.getEndDate())
                .param("total_price", rent.getTotalPrice())
                .update();
    }

    @Override
    public Integer update(Rent rent, Long id) {
        String sqlUpdate = "UPDATE rent SET person_id = :person_id, vehicle_id = :vehicle_id, begin_date = :begin_date, end_date = :end_date, total_price = :total_price WHERE id = :id";

        return this.jdbcClient.sql(sqlUpdate)
                .param("id", id)
                .param("person_id", rent.getPersonId())
                .param("vehicle_id", rent.getVehicleId())
                .param("begin_date", rent.getBeginDate())
                .param("end_date", rent.getEndDate())
                .param("total_price", rent.getTotalPrice())
                .update();
    }

    @Override
    public Integer delete(Long id) {
        String sqlDelete = "DELETE FROM rent WHERE id = :id";

        return this.jdbcClient.sql(sqlDelete)
                .param("id", id)
                .update();
    }
}
