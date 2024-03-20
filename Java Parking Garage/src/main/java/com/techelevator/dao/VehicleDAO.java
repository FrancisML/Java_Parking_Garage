package com.techelevator.dao;

import javax.sql.DataSource;

import com.techelevator.model.Vehicle;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO implements VehicleInterface {

    private final JdbcTemplate jdbcTemplate;

    public VehicleDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {

        String sql = "INSERT INTO vehicle (license_Plate, type, color, arrival_time) VALUES (?,?,?,?);";
        String spaceVehicle = "INSERT INTO space_vehicle (space_id, license_plate) VALUES (?,?);";
        String space = "UPDATE space SET occupied = true , license_plate = ? WHERE space_id = ?;";

        jdbcTemplate.update(sql, vehicle.getLicensePlate(), vehicle.getType(), vehicle.getColor(), vehicle.getArrivalTime());
        jdbcTemplate.update(spaceVehicle, vehicle.getSpaceId(), vehicle.getLicensePlate());
        jdbcTemplate.update(space, vehicle.getLicensePlate(), vehicle.getSpaceId());


        return vehicle;
    }

    @Override
    public Timestamp getVehicleTS(String license) {
        String getVehicleData = "SELECT arrival_time FROM vehicle WHERE license_plate = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(getVehicleData, license);

        Timestamp arrival_time = null;
        if (results.next()) { // Move cursor to the first row
            arrival_time = results.getTimestamp("arrival_time");
            System.out.println(arrival_time);
        } else {
            // Handle the case where no data is found for the given license plate
            System.out.println("No data found for the license plate: " + license);
        }

        return arrival_time;
    }

    @Override
    public List<Vehicle> getVehicleList() {
        List<Vehicle> vehicleList = new ArrayList<>();
        String getVehicles = "SELECT * FROM vehicle v LEFT JOIN space_vehicle sv ON v.license_plate = sv.license_plate;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(getVehicles);
        while (results.next()) {
            Vehicle vehicle = mapRowToVehicle(results);
            vehicleList.add(vehicle);
            System.out.println(vehicle.getSpaceId() + "  " + vehicle.getLicensePlate() + "  " + vehicle.getColor() + "  " + vehicle.getType() + "    " + vehicle.getArrivalTime());
        }
        return vehicleList;
    }

    @Override
    public int deleteVehicleById(String license_plate) {
        return 0;
    }
    private Vehicle mapRowToVehicle(SqlRowSet rowSet) {
        Vehicle vehicle = new Vehicle();
        vehicle.setColor(rowSet.getString("color"));
        vehicle.setLicensePlate(rowSet.getString("license_plate"));
        vehicle.setArrivalTime(rowSet.getTimestamp("arrival_time"));
        vehicle.setType(rowSet.getString("type"));
        vehicle.setSpaceId(rowSet.getString("space_id"));
        return vehicle;

    }
}

