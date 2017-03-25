package com.main.poc.Manager;

import com.main.poc.Helper.GenericHelper;
import com.main.poc.Helper.Utils;
import com.main.poc.Helper.VehicleInfoProperties;
import com.main.poc.Manager.DataStore.DataStore;
import com.main.poc.Manager.DataStore.InMemoryDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */

@Component
public class ParkingLotManager {

    DataStore store;

    @Autowired
    GenericHelper helper;

    @Value("${storageType:inMemory}")
    private String name;

    @PostConstruct
    public void postConstruct()
    {
        if(name!=null && name.equalsIgnoreCase("inMemory"))
        {
            store=new InMemoryDataStore();
        }
        else
        {
            throw new RuntimeException("Invalid Storage Type Provided");
        }
    }

    public boolean preStart()
    {
        if(helper.getVechileTypeToInfoMap().size()==0)
        {
            System.out.println("No Specific types of Vehicles are provided , sorry can't park");

            return  false;

        }

        if(store.isParkingSpaceToBeAsked())
        {
            int numberOfParking= Utils. <Integer>  getInput("Please provide number of parking lots available",
                    t->{
                        try {
                           return Utils.convertStringToInt(t);

                        } catch (Exception e) {
                            return null;
                        }
                    });

            store.setParkingSpace(numberOfParking);
        }

        System.out.println("Lets start parking");

        help();


        return true;

    }

    public void help()
    {

        helper.getVechileTypeToInfoMap().entrySet().stream().forEach(
            t->  System.out.println( "\t "+t.getValue())
        );
        System.out.println(String.format("Prices are per %d hour",helper.getHelper().getPrice_in_multiple_of_hours()));
        System.out.println("To Park type ENTER <vehicle type> for e.g ENTER CAR");
        System.out.println("To Park type EXIT <vehicle type> <number of hours> for e.g EXIT CAR 2");
        System.out.println("To get report type REPORT");

    }

    public VehicleInfoProperties.VehicleInfo getVehicleInfo(String type)
    {
        VehicleInfoProperties.VehicleInfo info= helper.getVechileTypeToInfoMap().get(type);

        if(info==null)
        {
            System.out.print("Invalid Vehicle Type provided , type help to find information");
        }

        return  info;
    }








}
