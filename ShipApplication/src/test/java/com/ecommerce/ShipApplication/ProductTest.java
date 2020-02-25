package com.ecommerce.ShipApplication;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;


import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void getShipDateShiponWeekenTrue() {
        Product p =  new Product("test", 2,true,4);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        cal.add(cal.DAY_OF_MONTH, p.maxBuisnessDaysToShip);

        String expectedDate = sdf.format(cal.getTime());
        String actualDate= p.getShipDate();

        assertEquals((expectedDate), actualDate);
    }
    @Test
    void getShipDateShiponWeekenFalse() {
        Product p =  new Product("test", 2,false,4);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        //Please adjust the Expected date before running the test
        cal.add(cal.DAY_OF_MONTH, p.maxBuisnessDaysToShip+1);

        String expectedDate = sdf.format(cal.getTime());
        String actualDate= p.getShipDate();

        assertEquals((expectedDate), actualDate);
    }

    @Test
    void getShipDateShiponWeekenFalseMaxBusinessDays6() {
        Product p =  new Product("test", 2,false,6);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        //Please adjust the Expected date before running the test
        //// Why maxBuisnessDaysToShip +1 ?? cal.day_of_month is inclusive current day
        // so need to add one more extra day to count weekend day
        cal.add(cal.DAY_OF_MONTH, p.maxBuisnessDaysToShip +1); // Why maxBuisnessDaysToShip +1 ?? cal.day_of_month is inclusive current day so need to add one more extra day to get ru

        String expectedDate = sdf.format(cal.getTime());
        String actualDate= p.getShipDate();

        assertEquals((expectedDate), actualDate);
    }

}