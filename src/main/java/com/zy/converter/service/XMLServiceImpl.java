package com.zy.converter.service;

import com.zy.converter.model.ValCurs;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.net.URL;
/*
fetching data from server as xml and converting to mapped object
 */
@Service
public class XMLServiceImpl implements XMLService {
    @Override
    public Object getXmlAsObject(String url, Class clazz) throws Exception {
        JAXBContext context = JAXBContext.newInstance(ValCurs.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ValCurs valCurs = (ValCurs) unmarshaller.unmarshal(new URL(url));
        return valCurs;
    }


}
