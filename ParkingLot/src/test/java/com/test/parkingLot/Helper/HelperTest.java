package com.test.parkingLot.Helper;

import com.test.parkingLot.ParkingLotInitiator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelperTest {


    @Autowired
    ApplicationContext ctx;

    @Autowired
    PropertiesHelper helper;

    @Test
    public void testProperties() throws  Exception
    {
        //ParkingLotInitiator.main(new String[]{});
        //helper.postInjection();
        System.out.println("Hi");
    }

}
