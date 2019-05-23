package Entities;

import dataBaseManagement.model.ObjectFromDB;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order implements Serializable {
    private String name = "Order #";
    private List<Photo> photos;
    private BigInteger salonId;
    private HashMap<Params, String> params;//тут будем хранить статутс, даты, ещё что понадобиться
    private BigInteger id;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
        name = "Order #" + id;
    }

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
        order.setId(objectFromDB.getId());
        return order;
    }

    public Order() {
        this.params = new HashMap<Params, String>();
        this.photos = new ArrayList<Photo>();
    }

    public Double getOrderPrice(){
        double price=0;
        for (Photo photo:photos){
            for (Offer offer:photo.appliedOffers){
                price+=Double.parseDouble(offer.getParams().get(Params.OFFER_PRICE));
            }
        }
        return price;
    }

    public Double getTimeToOrder(){
        double time=0;
        for (Photo photo:photos){
            for (Offer offer:photo.appliedOffers){
                time+=Double.parseDouble(offer.getParams().get(Params.TIME_TO_OFFER));
            }
        }
        return time;
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