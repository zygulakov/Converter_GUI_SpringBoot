package com.zy.converter.model;


import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity()
public class ValCurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @XmlAttribute(name = "Date", required = true)
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private Date date;

    @XmlAttribute(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "val_curs_date")
    @XmlElement(name = "Valute")
    List<Valute> valute;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<Valute> getValute() {
        return valute;
    }


    public void setValute(List<Valute> valute) {
        this.valute = valute;
    }

    public static class DateTimeAdapter extends XmlAdapter<String, Date> {
        private final String DATE_FORMAT = "dd.MM.yyyy";

        private final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        @Override
        public Date unmarshal(String xml) throws Exception {
            return dateFormat.parse(xml);
        }

        @Override
        public String marshal(Date object) throws Exception {
            return dateFormat.format(object);
        }

    }
}
