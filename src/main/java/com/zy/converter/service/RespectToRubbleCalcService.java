package com.zy.converter.service;

import com.zy.converter.model.Valute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

@Service
public class RespectToRubbleCalcService implements CalculationService {
    @Autowired
    private List<Valute> data;
    @Override
    public String calculate(String fromDenCharCode , String toDenRateCharCode, long amount)throws Exception {
        BigDecimal fromDenAmount = BigDecimal.valueOf(amount); // from amount to be converted
        BigDecimal fromDenNominal = getNominal(fromDenCharCode); // nominal of it
        BigDecimal fromDenRate = getRate(fromDenCharCode); // and rate of it
        BigDecimal toDenNominal = getNominal(toDenRateCharCode); // nominal of a desired den
        BigDecimal toDenRate = getRate(toDenRateCharCode); // rate of desired den

        BigDecimal toRub = fromDenAmount.divide(fromDenNominal,10,RoundingMode.HALF_UP).multiply(fromDenRate);
        BigDecimal result = toRub.multiply(toDenNominal.divide(toDenRate,10,RoundingMode.HALF_UP));
        return currencyFormat(result);
        //24/1*73,6 * 1/ 10
        //(amountX / nominalX * rateX) * nominalY / rateY;

    }
    private BigDecimal getNominal(String charCode)throws Exception{
        for(Valute valute : data){
            if(valute.getCharCode().equalsIgnoreCase(charCode))
                return new BigDecimal(valute.getNominal());
        }
        throw new Exception("no such Nominal for this input : "+charCode);
    }
    private BigDecimal  getRate(String charCode)throws Exception{
        for(Valute valute : data){
            if(valute.getCharCode().equalsIgnoreCase(charCode))
                return new BigDecimal(valute.getValue().replace(",","."));
        }
        throw new Exception("no such Nominal for this input : "+charCode);
    }
    private String currencyFormat(BigDecimal number){
        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format(number);
    }


}
