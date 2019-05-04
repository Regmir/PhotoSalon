package Entities;

import dataBaseManagement.model.ObjectFromDB;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class EquipmentType implements Serializable {
    private String name;
    private List<Offer> availableOffers;//услуги которые может продоставлять это оборудование
    private HashMap<Params, String> params;

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
        return equipmenttype;
    }

    public EquipmentType(String name) {
        this.name = name;
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
