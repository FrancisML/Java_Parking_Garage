package com.techelevator.dao;

public interface SpaceInterface {
    int countEmptySpots(int floor_id);

    // In SpaceDAO


    String getSpaceIdFromPlate(String licenseId);
}
