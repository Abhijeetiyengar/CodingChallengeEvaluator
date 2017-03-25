package com.test.poc.parkingVo;

import com.main.poc.Helper.GenericHelper;
import com.main.poc.Helper.PropertiesHelper;
import com.main.poc.Helper.VehicleInfoProperties;
import com.main.poc.Manager.vo.ParkingLotVO;
import com.test.poc.ParkingLotInitiatorTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ParkingLotInitiatorTest.class )
//@ActiveProfiles("test")
@SpringBootTest
@TestPropertySource(locations="classpath:test_properties_number.properties")
public class PakingVoTester {

    @Autowired
    GenericHelper helper;

    public ParkingLotVO parkingLotVO;


    @Before
    public void createVO()
    {
        parkingLotVO =new ParkingLotVO(helper.getHelper().getVehicleInfoProperties().getVehicleInfo());
        parkingLotVO.setTotalNumberOfParkingLot(5);

    }

    @Test
    public void addMoreThenAvaliable()
    {
        VehicleInfoProperties.VehicleInfo vo=helper.getVechileTypeToInfoMap().get("car");
        boolean val=parkingLotVO.entry(vo);

        Assert.assertEquals(val,true);
        Assert.assertEquals(parkingLotVO.getAvailableSlots(),4);


        vo=helper.getVechileTypeToInfoMap().get("truck");
        parkingLotVO.entry(vo);

        Assert.assertEquals(val,true);
        Assert.assertEquals(parkingLotVO.getAvailableSlots(),2);

        vo=helper.getVechileTypeToInfoMap().get("car");
        parkingLotVO.entry(vo);

        Assert.assertEquals(val,true);
        Assert.assertEquals(parkingLotVO.getAvailableSlots(),1);

        vo=helper.getVechileTypeToInfoMap().get("truck");
        parkingLotVO.entry(vo);

        Assert.assertEquals(val,false);
        Assert.assertEquals(parkingLotVO.getAvailableSlots(),1);





    }


}
