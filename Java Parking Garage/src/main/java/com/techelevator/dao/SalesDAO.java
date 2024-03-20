package com.techelevator.dao;

import com.techelevator.model.Sales;
import com.techelevator.model.Vehicle;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class SalesDAO implements SalesInterface {

    private static JdbcTemplate jdbcTemplate;

    public SalesDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public SalesDAO() {
    }


    @Override
    public  List<Sales> getSalesList() {
            List<Sales> salesList = new ArrayList<>();
            String sql = "SELECT * FROM sales;";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Sales sales = mapRowToSales(results);
                salesList.add(sales);
//                System.out.println(sales.getSpaceId() + "  " + sales.g() + "  " + vehicle.getColor() + "  " + vehicle.getType() + "    " + vehicle.getArrivalTime());
            }
            return salesList;
        }



    @Override
    public Sales pushtoSales(Sales sales) {
        String insertIntoSales = "INSERT INTO sales (space_id, amount, time_hours, license_plate) VALUES (?, ?, ?, ?);";
        String updateSpace = "UPDATE space SET occupied = false, license_plate = NULL WHERE space_id = ?;";
        String deleteFromSpaceVehicle = "DELETE FROM space_vehicle WHERE license_plate = ?;";
        String deleteFromVehicle = "DELETE FROM vehicle WHERE license_plate = ?;";
        jdbcTemplate.update(insertIntoSales, sales.getSpaceId(), sales.getAmount(),sales.getTime(), sales.getLicense());
        jdbcTemplate.update(updateSpace, sales.getSpaceId());
        jdbcTemplate.update(deleteFromSpaceVehicle, sales.getLicense());
        jdbcTemplate.update(deleteFromVehicle, sales.getLicense());
        System.out.println("Thank you, Drive Saftley!");
        return sales;
    }

    private Sales mapRowToSales(SqlRowSet rowSet) {
        Sales sales = new Sales();
        sales.setSaleId(rowSet.getInt("sale_id"));
        sales.setAmount(rowSet.getInt("amount"));
        sales.setLicense(rowSet.getString("license_plate"));
        sales.setTime(rowSet.getInt("time_hours"));
        sales.setSpaceId(rowSet.getString("space_id"));
        return sales;

    }
}
