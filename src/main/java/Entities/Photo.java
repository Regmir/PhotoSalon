package Entities;

import dataBaseManagement.model.ObjectFromDB;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Photo implements Serializable {
    //тут надо поле для хранения изображения
    List<Offer> appliedOffers;
    HashMap<String, String> params;//например ширина, высота, нужна ли печать
    private BigInteger id;

    public Photo() {
        this.appliedOffers = new ArrayList<Offer>();
        this.params = new HashMap<String, String>();
    }

    public ObjectFromDB prepareObjectFromDB() {
        ObjectFromDB objToPersist = new ObjectFromDB();
        objToPersist.setType("photo");
        byte[] parameters = SerializationUtils.serialize(this);
        objToPersist.setParameters(parameters);
        return objToPersist;
    }

    public static Photo parsePhoto(ObjectFromDB objectFromDB) {
        Photo photo = null;
        if (objectFromDB.getType().equals("photo"))
            photo = (Photo) SerializationUtils.deserialize(objectFromDB.getParameters());
        photo.setId(objectFromDB.getId());
        return photo;
    }

    public List<Offer> getAppliedOffers() {
        return appliedOffers;
    }

    public void setAppliedOffers(List<Offer> appliedOffers) {
        this.appliedOffers = appliedOffers;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}