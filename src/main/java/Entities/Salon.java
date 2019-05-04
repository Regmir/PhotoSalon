package Entities;

import dataBaseManagement.model.ObjectFromDB;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Salon implements Serializable {
    private String name;
    private List<Equipment> equipments;//or  List<BigInteger> equipmentIds; смотря что удобнее и быстрее, этот же коммент к другим спискам в полях классов
    private List<Worker> workers;
    private List<Order> orders;
    private List<Offer> offers;
    private HashMap<String,String> params;//адрес, рабочий режим - надо будет придумать формат чтобы парсить при отображении, пример Понедельник?00:00-12:00#Вторник ......

    public ObjectFromDB prepareObjectFromDB(){
        ObjectFromDB objToPersist = new ObjectFromDB();
        objToPersist.setName(this.name);
        objToPersist.setType("salon");
        byte[] parameters =  SerializationUtils.serialize(this);
        objToPersist.setParameters(parameters);
        return  objToPersist;
    }

    public static EquipmentType parseEquipmentType(ObjectFromDB objectFromDB){
        EquipmentType equipmenttype = null;
        if (objectFromDB.getType().equals("salon"))
            equipmenttype = (EquipmentType)SerializationUtils.deserialize(objectFromDB.getParameters());
        return equipmenttype;
    }

    public Salon(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

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
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }
}
