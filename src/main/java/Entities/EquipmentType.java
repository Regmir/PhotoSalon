package Entities;

import dataBaseManagement.model.ObjectFromDB;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EquipmentType implements Serializable {
    private String name;
    private List<Offer> availableOffers;//услуги которые может продоставлять это оборудование
    private HashMap<Params, String> params;
    private BigInteger id;

    public ObjectFromDB prepareObjectFromDB(){
        ObjectFromDB objToPersist = new ObjectFromDB();
        objToPersist.setName(this.name);
        objToPersist.setType("equipmenttype");
        byte[] parameters =  SerializationUtils.serialize(this);
        objToPersist.setParameters(parameters);
        return  objToPersist;
    }

    public static EquipmentType parseEquipmentType(ObjectFromDB objectFromDB){
        EquipmentType equipmenttype = null;
        if (objectFromDB.getType().equals("equipmenttype"))
            equipmenttype = (EquipmentType)SerializationUtils.deserialize(objectFromDB.getParameters());
        equipmenttype.setId(objectFromDB.getId());
        return equipmenttype;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public EquipmentType(String name) {
        this.name = name;
        this.params = new HashMap<Params, String>();
        this.availableOffers = new ArrayList<Offer>();
    }

    public int getAvaliableOfferCount(){
        if (availableOffers!=null)
            if (availableOffers.size()==0)
                return 1;
            else
                return availableOffers.size();
        return 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Offer> getAvailableOffers() {
        return availableOffers;
    }

    public void setAvailableOffers(List<Offer> availableOffers) {
        this.availableOffers = availableOffers;
    }

    public HashMap<Params, String> getParams() {
        return params;
    }

    public void setParams(HashMap<Params, String> params) {
        this.params = params;
    }
}
