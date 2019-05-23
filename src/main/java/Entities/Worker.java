package Entities;

import dataBaseManagement.model.ObjectFromDB;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;

public class Worker implements Serializable {
    BigInteger salonId;
    String name;
    BigInteger id;
    private HashMap<Params, String> params;

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

    public Worker(String name){
        this.name=name;
        this.params = new HashMap<Params, String>();
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

    public HashMap<Params, String> getParams() {
        return params;
    }

    public void setParams(HashMap<Params, String> params) {
        this.params = params;
    }
}
