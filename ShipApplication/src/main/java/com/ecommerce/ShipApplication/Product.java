package com.ecommerce.ShipApplication;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long productId;
    String productName;
    int inventoryQuantity;
    boolean shipOnWeekends= false;
    int maxBuisnessDaysToShip;
    @Transient
    String shipDate;

    @PostLoad
    private void postLoad() {}

    public Product(){

    }

    public Product(String productName, int inventoryQuantity, boolean shipOnWeekends, int maxBusinessDaysToShip) {
        this.productName = productName;
        this.inventoryQuantity = inventoryQuantity;
        this.shipOnWeekends = shipOnWeekends;
        this.maxBuisnessDaysToShip = maxBusinessDaysToShip;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", inventoryQuantity=" + inventoryQuantity +
                ", shipOnWeekends=" + shipOnWeekends +
                ", maxBusinessDaysToShip=" + maxBuisnessDaysToShip +
                '}';
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getInventoryQuantity() {
        return inventoryQuantity;
    }

    public boolean isShipOnWeekends() {
        return shipOnWeekends;
    }

    public int getMaxBusinessDaysToShip() {
        return maxBuisnessDaysToShip;
    }

    public String getShipDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        if (this.isShipOnWeekends() == true){
            cal.add(Calendar.DAY_OF_MONTH, this.getMaxBusinessDaysToShip());
        }else {
            // Week has 5 business days
             if (maxBuisnessDaysToShip >5){
                 cal.add(Calendar.DAY_OF_MONTH,((this.getMaxBusinessDaysToShip()/5)* 7+ this.maxBuisnessDaysToShip % 5)-1);
             }
             else {
                 int numberOfDaysToIncrement = calculateShipDate(maxBuisnessDaysToShip);
                 cal.add(cal.DAY_OF_MONTH, numberOfDaysToIncrement);
             }
        }
        this.shipDate = sdf.format(cal.getTime());
        return this.shipDate;
    }

    private int calculateShipDate(int maxBuisnessDaysToShip) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        Calendar cal = Calendar.getInstance();
        int countNumberOfActualShipDays = 0;
        int counterMaxShipDays = maxBuisnessDaysToShip;
        while(counterMaxShipDays!=0){
            Date day = cal.getTime();
            int value = cal.get(cal.DAY_OF_WEEK);
            // 1 - sunday in the calender.day_of_week and 7 = Saturday in calender.Day_of_week
            if (value > 1  && value < 7 ){
                // Decrement counter if it's valid business day to ship
                counterMaxShipDays--;
            }
            // increment number of days including weekends
            cal.add(cal.DAY_OF_MONTH, 1);
            cal.setTime(cal.getTime());
            countNumberOfActualShipDays++;
        }
        // Subtracting current day from actual total days;
        return countNumberOfActualShipDays-1 ;
    }
}
