package com.main.poc.Manager.DataStore;

import com.main.poc.Helper.VehicleInfoProperties;
import com.main.poc.Manager.vo.ParkingLotVO;

import java.util.HashMap;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */
public class InMemoryDataStore implements  DataStore {

    HashMap<Integer,String> numberofType=new HashMap<>();

    ParkingLotVO parkingLotVO=new ParkingLotVO();


    @Override
    public void entry(VehicleInfoProperties.VehicleInfo vehicleInfo) {



    }

    @Override
    public void exit(VehicleInfoProperties.VehicleInfo vehicleInfo) {

        if(!numberofType.containsKey(vehicleInfo.getType()))
            System.out.println(String.format("There are not enough %s parked currently ",vehicleInfo.getType()));



    }

    @Override
    public void report() {

    }

    @Override
    public boolean isParkingSpaceToBeAsked() {
        return  true;

    }

    @Override
    public void setParkingSpace(int numberOfParkingSpace) {
        parkingLotVO.setTotalNumberOfParkingLot(numberOfParkingSpace);
    }


}
