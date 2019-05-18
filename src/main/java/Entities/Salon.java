package Entities;

import dataBaseManagement.model.ObjectFromDB;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

public class Salon implements Serializable {
    private String name;
    private List<Equipment> equipments;//or  List<BigInteger> equipmentIds; смотря что удобнее и быстрее, этот же коммент к другим спискам в полях классов
    private List<Worker> workers;
    private List<Order> orders;
    //private List<Offer> offers; зачем это если оно завит от оборудования?
    private HashMap<Params, String> params;//адрес, рабочий режим - надо будет придумать формат чтобы парсить при отображении, пример Понедельник?00:00-12:00#Вторник ......
    private BigInteger id;

    public ObjectFromDB prepareObjectFromDB(){
        ObjectFromDB objToPersist = new ObjectFromDB();
        objToPersist.setName(this.name);
        objToPersist.setType("salon");
        byte[] parameters =  SerializationUtils.serialize(this);
        objToPersist.setParameters(parameters);
        return  objToPersist;
    }

    public static Salon parseSalon(ObjectFromDB objectFromDB){
        Salon salon = null;
        if (objectFromDB.getType().equals("salon"))
            salon = (Salon)SerializationUtils.deserialize(objectFromDB.getParameters());
        return salon;
    }

    public Salon(String name) {
        this.name = name;
        this.params = new HashMap<Params, String>();

    }

    public String getAddress() {return params.get(Params.ADDRESS);}

    public void setAddress(String address) { params.put(Params.ADDRESS,address);}

    public String getTime() {return params.get(Params.TIME_TO_OFFER);}

    public void setTime(String time) { params.put(Params.TIME_TO_OFFER,time);}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public int getEquipmentSize(){return equipments.size();}

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Offer> getOffers() {
        return null;
    }

    /*
    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }*/

    public HashMap<Params, String> getParams() {
        return params;
    }

    public void setParams(HashMap<Params, String> params) {
        this.params = params;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}
