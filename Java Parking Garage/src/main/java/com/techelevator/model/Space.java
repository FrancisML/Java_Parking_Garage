package com.techelevator.model;

import com.techelevator.dao.SpaceInterface;

import javax.sql.DataSource;

public class Space implements SpaceInterface {
    private int spaceId;
    private String floorId;
    private boolean isOccupied;
    private Vehicle vehicle;
    private DataSource dataSource;

    public Space(int spaceId, String floorId, boolean isOccupied, Vehicle vehicle) {
        this.spaceId = spaceId;
        this.floorId = floorId;
        this.isOccupied = isOccupied;
        this.vehicle = vehicle;
    }

    public int getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static int countEmptySpots() {

        return 0;
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
