package Entities;

import dataBaseManagement.model.ObjectFromDB;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;

public class Offer implements Serializable {
    private String name;
    private HashMap<Params, String> params; //время выполнения заказа в часах, цена, ограничения на фото
    private BigInteger id;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public ObjectFromDB prepareObjectFromDB() {
        ObjectFromDB objToPersist = new ObjectFromDB();
        objToPersist.setName(this.name);
        objToPersist.setType("offer");
        byte[] parameters = SerializationUtils.serialize(this);
        objToPersist.setParameters(parameters);
        return objToPersist;
    }

    public static Offer parseOffer(ObjectFromDB objectFromDB) {
        Offer offer = null;
        if (objectFromDB.getType().equals("offer"))
            offer = (Offer) SerializationUtils.deserialize(objectFromDB.getParameters());
        return offer;
    }

    public Offer(String name) {
        this.name = name;
        this.params = new HashMap<Params, String>();
    }

    public int getParamsCount() {
        if (params!=null)
            if (params.size()==0)
                return 1;
            else return params.size();
        return 1;
    }

    public String[] getParamsName(){
        Set<Params> arr = params.keySet();
        String[] sarr = new String[params.size()];
        int i=0;
        for (Params p: arr
        ) {
            if (p.equals(Params.TIME_TO_OFFER)){ sarr[i]="Время"; i++; }
            if (p.equals(Params.OFFER_PRICE)){ sarr[i]="Цена"; i++;}
            if (p.equals(Params.DESCRIPTION)){ sarr[i]="Ограничения"; i++;}
        }
        return sarr;
    }

    public String[] getParamsVal(){
        Collection<String> arr = params.values();
        String[] sarr = new String[params.size()];
        int i=0;
        for (String p: arr
        ) {
            sarr[i] = p;
            i++;
        }
        return sarr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Params, String> getParams() {
        return params;
    }

    public void setParams(HashMap<Params, String> params) {
        this.params = params;
    }
}
