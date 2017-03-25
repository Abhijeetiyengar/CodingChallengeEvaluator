package com.main.poc.Manager.vo;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */
public class ParkingLotVO {

    int totalNumberOfParkingLot;

    int currentSlots;

    public void setTotalNumberOfParkingLot(int totalNumberOfParkingLot)
    {
        this.totalNumberOfParkingLot=totalNumberOfParkingLot;
        this.currentSlots=totalNumberOfParkingLot;
    }

    /*
        This method need to be synchronized or we need to use make currentSlots Atomic in case we are dealing with
         multi thread applications specially in case of UI . But be care full blocking algorithms will hurt the performance.

     */

    void entry(int numberOfSlots)
    {
        if(currentSlots<numberOfSlots) {
            System.out.println("Apologize , but we dont have enough space for your vehicle");
            return;
        }
        totalNumberOfParkingLot-=numberOfSlots;
    }

    void exit(int numberOfSlots)
    {
        if(currentSlots<numberOfSlots) {
            System.out.println("Apologize , but we dont have enough space for your vehicle");
            return;
        }
        totalNumberOfParkingLot-=numberOfSlots;
    }
}
