package Entities;

import dataBaseManagement.model.ObjectFromDB;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.List;

public class EquipmentType implements Serializable {
    String name;
    String description;
    List<Offer> availableOffers;//услуги которые может продоставлять это оборудование

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
}
