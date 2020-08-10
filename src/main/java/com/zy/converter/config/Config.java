package com.zy.converter.config;

import com.zy.converter.model.Valute;
import com.zy.converter.service.ValCurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class Config {
    @Autowired
    private ValCurseService valCurseService;
    /*getting ready data object from dataBase as bean*/
    @Bean
    public List<Valute> getData() throws Exception{
        return valCurseService.getLatestDataObject().getValute();
    }
}
