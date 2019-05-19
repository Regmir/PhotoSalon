package Entities;

import dataBaseManagement.model.ObjectFromDB;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.math.BigInteger;

public class Worker implements Serializable {
    BigInteger salonId;
    String name;
    BigInteger id;

    public ObjectFromDB prepareObjectFromDB() {
        ObjectFromDB objToPersist = new ObjectFromDB();
        objToPersist.setName(this.name);
        objToPersist.setType("worker");
        byte[] parameters = SerializationUtils.serialize(this);
        objToPersist.setParameters(parameters);
        return objToPersist;
    }

    public static Worker parseWorker(ObjectFromDB objectFromDB) {
        Worker worker = null;
        if (objectFromDB.getType().equals("worker"))
            worker = (Worker) SerializationUtils.deserialize(objectFromDB.getParameters());
        worker.setId(objectFromDB.getId());
        return worker;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getSalonId() {
        return salonId;
    }

    public void setSalonId(BigInteger salonId) {
        this.salonId = salonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
