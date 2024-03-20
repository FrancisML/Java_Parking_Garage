package com.techelevator.dao;


import com.techelevator.model.Sales;

import java.util.List;

public interface SalesInterface {

    public Sales pushtoSales(Sales sales);
    public List<Sales> getSalesList();


}


