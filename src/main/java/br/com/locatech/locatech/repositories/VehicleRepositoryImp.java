package br.com.locatech.locatech.repositories;

import br.com.locatech.locatech.entities.Vehicle;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VehicleRepositoryImp implements VehicleRepository{

    private final JdbcClient jdbcClient;

    public VehicleRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        String sqlSelect = "SELECT * FROM vehicles WHERE id = :id";

        return this.jdbcClient.sql(sqlSelect)
                .param("id", id)
                .query(Vehicle.class)
                .optional();
    }

    @Override
    public List<Vehicle> findAll(int size, int offset) {
        String sqlSelectAll = "SELECT * FROM vehicles LIMIT :size OFFSET :offset";

        return this.jdbcClient.sql(sqlSelectAll)
                .param("size", size)
                .param("offset", offset)
                .query(Vehicle.class)
                .list();
    }

    @Override
    public Integer save(Vehicle vehicle) {
        String sqlInsert = "INSERT INTO vehicles(brand, model, plate, vehicle_year, color, daily_price) VALUES(:brand, :model, :plate, :vehicle_year, :color, :daily_price)";

        return this.jdbcClient.sql(sqlInsert)
                .param("brand", vehicle.getBrand())
                .param("model", vehicle.getModel())
                .param("plate", vehicle.getPlate())
                .param("vehicle_year", vehicle.getVehicleYear())
                .param("color", vehicle.getColor())
                .param("daily_price", vehicle.getDailyPrice())
                .update();
    }

    @Override
    public Integer update(Vehicle vehicle, Long id) {
        String sqlUpdate = "UPDATE vehicles SET brand = :brand, model = :model, plate = :plate, vehicle_year = :vehicle_year, color = :color, daily_price = :daily_price WHERE id = :id";

        return this.jdbcClient.sql(sqlUpdate)
                .param("id", id)
                .param("brand", vehicle.getBrand())
                .param("model", vehicle.getModel())
                .param("plate", vehicle.getPlate())
                .param("vehicle_year", vehicle.getVehicleYear())
                .param("color", vehicle.getColor())
                .param("daily_price", vehicle.getDailyPrice())
                .update();
    }

    @Override
    public Integer delete(Long id) {
        String sqlDelete = "DELETE FROM vehicles WHERE id = :id";

        return this.jdbcClient.sql(sqlDelete)
                .param("id", id)
                .update();
    }
}
