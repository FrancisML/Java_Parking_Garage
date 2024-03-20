package com.techelevator;

import com.techelevator.dao.SalesDAO;
import com.techelevator.dao.SalesInterface;
import com.techelevator.dao.SpaceDAO;
import com.techelevator.dao.SpaceInterface;
import com.techelevator.model.Sales;
import com.techelevator.model.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Scanner;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Main implements SpaceInterface {

    private static BasicDataSource dataSource;
    private SalesDAO salesDAO = new SalesDAO(dataSource);

    public static void main(String[] args) {

        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/Parking_Garage");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");



        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMainMenu();

            System.out.println("Please Make a Selection:");
            String choice = scanner.nextLine();
            System.out.println(choice);

            switch (choice) {
                case "1":
                    Vehicle.handleArrivingCar(dataSource);
                    break;
                case "2":
                    Vehicle.handleLeavingCar(dataSource);
                    break;
                case "3":
                    Vehicle.getVehicles(dataSource);
                    break;
                case "4":
                    Sales.getSales(dataSource);
                    break;
                case "5":
                    // Exit
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please select again.");
            }

            // Wait for user to hit Enter to continue
            System.out.println("Press Enter to continue...");
            scanner.nextLine(); // This consumes the Enter key press
        }
    }

    private static void displayMainMenu() {
        int floorOne = new SpaceDAO(dataSource).countEmptySpots(1);
        int floorTwo = new SpaceDAO(dataSource).countEmptySpots(2);
        int floorThree = new SpaceDAO(dataSource).countEmptySpots(3);
        int floorFour = new SpaceDAO(dataSource).countEmptySpots(4);

        System.out.println();
        System.out.println("*******************************************");
        System.out.println("Welcome to the Tech ELevator Parking Garage");
        System.out.println("**************** Main Menu ****************");
        System.out.println();
        System.out.println("1. Arriving Vehicle" + "            Vacancy");
        System.out.println("2. Exiting Vehicle" + "             Floor 1: " + floorOne);
        System.out.println("3. List of Vehicles" + "            Floor 2: " + floorTwo);
        System.out.println("4. Sales" + "                       Floor 3: " + floorThree);
        System.out.println("5. Exit" + "                        Floor 4: " + floorFour);
        System.out.println();
        System.out.println("*******************************************");
    }



    @Override
    public int countEmptySpots(int floor_id) {
        return 0;
    }

    @Override
    public String getSpaceIdFromPlate(String licenseId) {
        return null;
    }
}