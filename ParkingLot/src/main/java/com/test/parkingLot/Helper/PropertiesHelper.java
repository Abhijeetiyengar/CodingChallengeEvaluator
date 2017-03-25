package com.test.parkingLot.Helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */

@Component
@ConfigurationProperties
public class PropertiesHelper {

    private String types_of_vehicles;

    private int price_in_multiple_of_hours;

    @Autowired
    private VehicleInfoProperties vehicleInfoProperties;


    public String getTypes_of_vehicles() {
        return types_of_vehicles;
    }

    public void setTypes_of_vehicles(String types_of_vehicles) {
        this.types_of_vehicles = types_of_vehicles;
    }

    public int getPrice_in_multiple_of_hours() {
        return price_in_multiple_of_hours;
    }

    public void setPrice_in_multiple_of_hours(String price_in_multiple_of_hours) {
        this.price_in_multiple_of_hours=Integer.parseInt(price_in_multiple_of_hours);
    }

    public VehicleInfoProperties getVehicleInfoProperties() {
        return vehicleInfoProperties;
    }

    public void setVehicleInfoProperties(VehicleInfoProperties vehicleInfoProperties) {
        this.vehicleInfoProperties = vehicleInfoProperties;
    }

    @PostConstruct
    public void test()
    {
        System.out.println("Hi");
    }
}
