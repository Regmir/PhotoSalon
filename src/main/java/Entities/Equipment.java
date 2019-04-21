package Entities;

import dataBaseManagement.model.ObjectFromDB;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.List;

public class Equipment implements Serializable {
    String name;
    String description;
    EquipmentType equipmentType;
    List<Offer> offers;//это класс конкретного оборудования уже с четким набором услуг в этом списке

    public ObjectFromDB prepareObjectFromDB(){
        ObjectFromDB objToPersist = new ObjectFromDB();
        objToPersist.setName(this.name);
        objToPersist.setType("equipment");
        byte[] parameters =  SerializationUtils.serialize(this);
        objToPersist.setParameters(parameters);
        return  objToPersist;
    }

    public static Equipment parseEquipment(ObjectFromDB objectFromDB){
        Equipment equipment = null;
        if (objectFromDB.getType().equals("equipment"))
            equipment = (Equipment)SerializationUtils.deserialize(objectFromDB.getParameters());
        return equipment;
    }
}
