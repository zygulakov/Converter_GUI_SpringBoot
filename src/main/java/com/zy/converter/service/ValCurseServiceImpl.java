package com.zy.converter.service;

import com.zy.converter.model.ValCurs;
import com.zy.converter.repository.ValuteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class ValCurseServiceImpl implements ValCurseService{
    private ValuteRepository repo;
    private final String DATE_FORMAT = "dd.MM.yyyy";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    @Autowired
    public ValCurseServiceImpl(ValuteRepository repo) {
        this.repo = repo;
    }
    /*
        cheking db if there is todays data
     */
    @Override
    public ValCurs getLatestDataObject() throws Exception {
        String toDayDate = toDayDate();
        Optional<ValCurs> opt = repo.findByDate(dateFormat.parse(toDayDate));
        System.out.println("Server date: " + toDayDate);
        if(opt.isEmpty())
        {
            opt = repo.findTopByOrderByIdDesc();
            if(opt.isEmpty()){
                throw new Exception("No Data in DB");
            }
            System.out.println("No updated data getting latest data date: "+opt.get().getDate());
        }
        return opt.get();
    }
    /*
    get todays date from date object and parsing to string
     */
    public String toDayDate(){
        Date date = Calendar.getInstance().getTime();
        String toDay = dateFormat.format(date);
        return toDay;
    }

}
