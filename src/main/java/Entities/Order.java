package Entities;

import dataBaseManagement.model.ObjectFromDB;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

public class Order implements Serializable {
    private String name;
    private List<Photo> photos;
    private BigInteger salonId;
    private HashMap<Params, String> params;//тут будем хранить статутс, даты, ещё что понадобиться

    public ObjectFromDB prepareObjectFromDB() {
        ObjectFromDB objToPersist = new ObjectFromDB();
        objToPersist.setName(this.name);
        objToPersist.setType("order");
        byte[] parameters = SerializationUtils.serialize(this);
        objToPersist.setParameters(parameters);
        return objToPersist;
    }

    public static Order parseOrder(ObjectFromDB objectFromDB) {
        Order order = null;
        if (objectFromDB.getType().equals("order"))
            order = (Order) SerializationUtils.deserialize(objectFromDB.getParameters());
        return order;
    }

    public Order(String name, BigInteger salonId) {
        this.name = name;
        this.salonId = salonId;
        this.params = new HashMap<Params, String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public BigInteger getSalonId() {
        return salonId;
    }

    public void setSalonId(BigInteger salonId) {
        this.salonId = salonId;
    }

    public HashMap<Params, String> getParams() {
        return params;
    }

    public void setParams(HashMap<Params, String> params) {
        this.params = params;
    }
}