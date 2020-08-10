package com.zy.converter;

import com.zy.converter.service.CalculationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConverterApplicationTests {
    @Autowired
    public CalculationService calculationService;

    @Test
    void contextLoads() {
    }

    @Test
    public void calculationTest() throws Exception{

    }

}
