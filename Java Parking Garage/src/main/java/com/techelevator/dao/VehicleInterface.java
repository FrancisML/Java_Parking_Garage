package com.techelevator.dao;

import com.techelevator.model.Vehicle;

import java.sql.Timestamp;
import java.util.List;

public interface VehicleInterface {


    Vehicle createVehicle(Vehicle vehicle);

    Timestamp getVehicleTS(String license);

    List<Vehicle> getVehicleList();


    int deleteVehicleById(String license_plate);
}
