package Entities;

import dataBaseManagement.model.ObjectFromDB;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Equipment implements Serializable {
    private String name;
    private EquipmentType equipmentType;
    private List<Offer> offers;//это класс конкретного оборудования уже с четким набором услуг в этом списке
    private HashMap<Params, String> params;
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
        objToPersist.setType("equipment");
        byte[] parameters = SerializationUtils.serialize(this);
        objToPersist.setParameters(parameters);
        return objToPersist;
    }

    public static Equipment parseEquipment(ObjectFromDB objectFromDB) {
        Equipment equipment = null;
        if (objectFromDB.getType().equals("equipment"))
            equipment = (Equipment) SerializationUtils.deserialize(objectFromDB.getParameters());
        return equipment;
    }

    public Equipment(String name, EquipmentType equipmentType) {
        this.name = name;
        this.equipmentType = equipmentType;
        this.offers = new ArrayList<Offer>();
        this.params = new HashMap<Params, String>();
    }

    public int getOfferCount(){
        if (offers!=null)
            if (offers.size()==0)
                return 1;
            else
                return offers.size();
        return 1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public String getName() {
        return name;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public HashMap<Params, String> getParams() {
        return params;
    }

    public void setParams(HashMap<Params, String> params) {
        this.params = params;
    }
}