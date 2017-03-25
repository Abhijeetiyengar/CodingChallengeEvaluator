package com.test.poc.Helper;

import com.main.poc.ParkingLotInitiator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */

@Configuration
@ComponentScan(basePackages = "com.main.poc",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                        value = {
                                ParkingLotInitiator.class,


                        })
        })
public class ConfigurationClass {
}
