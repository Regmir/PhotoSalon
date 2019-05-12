package Entities;

import dataBaseManagement.model.ObjectFromDB;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

public class Offer implements Serializable {
    private String name;
    private HashMap<Params, String> params; //время выполнения заказа в часах, цена, ограничения на фото

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
