package com.zy.converter.controller;

import com.zy.converter.model.Valute;
import com.zy.converter.service.CalculationService;
import com.zy.converter.service.ValCurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class API {
    @Autowired
    private ValCurseService service;
    @Autowired
    private CalculationService calcService;
    @Autowired
    private List<Valute> data;

    @ResponseBody
    @RequestMapping(value = "api/calculate", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public String latestData(@RequestParam("fromDen") String fromDen,
                             @RequestParam("toDen") String toDen,
                             @RequestParam("amount") long amount) {
        try {
            return calcService.calculate(fromDen, toDen, amount) + "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) throws Exception {
        model.addAttribute("data", data);
        return "converter";
    }
}
