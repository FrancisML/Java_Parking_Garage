package com.techelevator.model;


import com.techelevator.dao.*;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Vehicle implements VehicleInterface, SalesInterface {
    private static String licensePlate;
    private static String type;
    private static String color;
    private static String spaceId;
    private static Timestamp arrivalTime;
    private  DataSource dataSource;


    public Vehicle(String licensePlate, String type, String color, String spaceId, Timestamp arrivalTime) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.color = color;
        this.spaceId = spaceId;
        this.arrivalTime = arrivalTime;

    }

    public Vehicle() {

    }


    public String getLicensePlate() {
        return licensePlate;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public static void handleArrivingCar(DataSource dataSource) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter License Plate:");
        String licensePlate = scanner.nextLine();
        System.out.println("Enter Vehicle Color:");
        String vehicleColor = scanner.nextLine();
        System.out.println("Enter Vehicle Type (Truck, Car, Motorcycle):");
        String vehicleType = scanner.nextLine();
        System.out.println("Enter Parking Space Number:");
        String spaceId = scanner.nextLine();
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp arrivalTime = new Timestamp(currentTimeMillis);
        Vehicle vehicle = new Vehicle(licensePlate, vehicleType, vehicleColor, spaceId, arrivalTime);
        vehicle.setDataSource(dataSource); // Set the dataSource for the vehicle
        VehicleDAO vehicleDAO = new VehicleDAO(dataSource); // Pass dataSource to the DAO
        vehicleDAO.createVehicle(vehicle);
        System.out.println(licensePlate);
        System.out.println(vehicleColor);
        System.out.println(vehicleType);
        System.out.println(arrivalTime);
        scanner.close();

    }

    public static void handleLeavingCar(DataSource dataSource) {
        Scanner scanner = new Scanner(System.in);
        //get license plate
        System.out.println("Enter License Plate:");
        String license_plate = scanner.nextLine();
        // Create an instance of the class to call the non-static method
        Vehicle leavingVehicle = new Vehicle(licensePlate, type, color, spaceId, arrivalTime);
        leavingVehicle.setDataSource(dataSource);
        VehicleDAO vehicleDAO = new VehicleDAO(dataSource); // Pass dataSource to the DAO
        Timestamp timestamp1 = vehicleDAO.getVehicleTS(license_plate);
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        long diffMilliseconds = Math.abs(currentTimestamp.getTime() - timestamp1.getTime());
        // Convert milliseconds to minutes
        long diffMinutes = TimeUnit.MILLISECONDS.toMinutes(diffMilliseconds);
        // Round up the minutes to the nearest integer
        int roundedMinutes = (int) Math.ceil(diffMinutes);
        long diffHours = TimeUnit.MILLISECONDS.toHours(diffMilliseconds);
        // Round up the hours to the nearest integer
        int roundedHours = (int) Math.ceil(diffHours);
        int amount = 0;
        double time = 0;
        if (roundedMinutes <= 30 ) {
            amount = 0;
            time = 30;
        } else if (roundedHours <= 5) {
            amount = roundedHours * 5;
            time = (roundedHours);
        }else if (roundedHours % 24 == 0){
            amount = (roundedHours / 24) * 25;
            time = (roundedHours / 24);
        }else if (roundedHours % 24 != 0) {
            amount = (((roundedHours / 24) + 1) * 25);
            time = (roundedHours);
        }
        SpaceDAO spaceDAO = new SpaceDAO(dataSource);
        String spaceId = spaceDAO.getSpaceIdFromPlate(license_plate);
        Sales sale = new Sales(spaceId, amount, time, license_plate);
        sale.setDataSource(dataSource);
        SalesDAO salesDAO = new SalesDAO(dataSource);
        salesDAO.pushtoSales(sale);

    }

    public static void getVehicles(DataSource dataSource) {
        VehicleDAO vehicleDAO = new VehicleDAO(dataSource);
        System.out.println("*******************************************");
        System.out.println("Space   Plate   color   Type   Arrival");
        System.out.println("*******************************************");
        System.out.println();
        List<Vehicle> vehicleList = vehicleDAO.getVehicleList();

    }



    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        return null;
    }

    @Override
    public Timestamp getVehicleTS(String license) {
        return null;
    }

    @Override
    public List<Vehicle> getVehicleList() {
        return null;
    }


    @Override
    public int deleteVehicleById(String license_plate) {
        return 0;
    }


    @Override
    public Sales pushtoSales(Sales sales) {
        return null;
    }

    @Override
    public List<Sales> getSalesList() {
        return null;
    }
}