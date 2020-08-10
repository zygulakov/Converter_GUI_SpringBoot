package com.zy.converter;

import com.zy.converter.model.ValCurs;
import com.zy.converter.repository.ValuteRepository;
import com.zy.converter.service.XMLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Listener {
    @Autowired
    private ValuteRepository repo;
    @Autowired
    private XMLService service;
    //xml server api
    @Value("${xmlDataResource}")
    private  String xmlDataResource;
/*
when Application is ready this event gets data from server and syncs database
 */
    @EventListener
    public void initData(ApplicationReadyEvent event) throws  Exception{
            ValCurs valCurs = getObjectDataFromServer();
            syncDB(valCurs);
    }
    /*
    getting xml data from server api ,converting to object and returning
     */
    private ValCurs getObjectDataFromServer() throws Exception {
        ValCurs valCurse = (ValCurs) service.getXmlAsObject(xmlDataResource, ValCurs.class);
        return valCurse;
    }
/*
Checking if there is todays data if no then update the data;
*/
    private void syncDB(ValCurs valCurs) throws Exception {
        Date toDayDate = valCurs.getDate();
        System.out.println("Syncing data... ");
        if(!repo.existsByDate(toDayDate)){
            repo.save(valCurs);
            System.out.println("DataBase updated date :" + toDayDate);
        }else
            System.out.println("No data updated");

    }


}
