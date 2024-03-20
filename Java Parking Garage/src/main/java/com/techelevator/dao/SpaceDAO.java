package com.techelevator.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class SpaceDAO implements SpaceInterface {

    private  final JdbcTemplate jdbcTemplate;
    private  DataSource dataSource;

    public SpaceDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public int countEmptySpots(int floor_id){
        String floor = "SELECT COUNT(*) AS number FROM space WHERE floor_id = ? AND occupied = false;";
        return jdbcTemplate.queryForObject(floor, Integer.class, floor_id);

    }


    @Override
    public  String getSpaceIdFromPlate(String licenseId) {
        String spaceId = "SELECT space_id FROM space_vehicle WHERE license_plate = ?";
        return jdbcTemplate.queryForObject(spaceId, String.class, licenseId);
    }


}