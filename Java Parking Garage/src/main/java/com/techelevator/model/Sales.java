package com.techelevator.model;

import com.techelevator.dao.SalesDAO;

import javax.sql.DataSource;
import java.util.List;

public class Sales {
    private  String spaceId;
    private int saleId;
    private  int amount;
    private  double time;

    private static String license;

    public DataSource getDataSource;

    public Sales() {

    }

    public String getSpaceId() {
        return spaceId;
    }



    public void setSpaceId(String spaceId) {
       this.spaceId = spaceId;
    }

    public  int getAmount() {
        return amount;
    }

    public  void setAmount(int amount) {
       this.amount = amount;
    }

    public  double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getLicense() {
        return license;
    }

    public  void setLicense(String license) {
        this.license = license;
    }

    public DataSource getGetDataSource() {
        return getDataSource;
    }

    public void setGetDataSource(DataSource getDataSource) {
        this.getDataSource = getDataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private DataSource dataSource;


    public Sales(String spaceId, int amount, double time, String license) {
        this.spaceId = spaceId;
        this.amount = amount;
        this.time = time;
        this.license = license;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public Sales(String spaceId, int amount, double time, String license, int saleId) {
        this.spaceId = spaceId;
        this.amount = amount;
        this.time = time;
        this.license = license;
        this.saleId = saleId;
    }
    public static void getSales(DataSource dataSource) {
        SalesDAO salesDAO = new SalesDAO(dataSource);
        System.out.println("*******************************************");
        System.out.println("Sale Id  Space   Amount   time   Plate");
        System.out.println("*******************************************");
        System.out.println();
        List<Sales> salesList = salesDAO.getSalesList();
        for (Sales sales : salesList) {
            System.out.println(sales.getSaleId() + "      " + sales.getSpaceId() + "        " + sales.getAmount() + "     " + sales.getTime() + "      " + sales.getLicense());
        }


    }

}