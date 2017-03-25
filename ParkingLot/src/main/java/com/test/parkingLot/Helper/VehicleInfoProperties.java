package com.test.parkingLot.Helper;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */

@Component
@ConfigurationProperties("details")
public class VehicleInfoProperties {

    private List<VehicleInfo> vehicleInfo =new ArrayList();

    public List<VehicleInfo> getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(List<VehicleInfo> vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public  static class VehicleInfo
    {
        private  String type;
        private String price;
        private String size;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }
    }

}
