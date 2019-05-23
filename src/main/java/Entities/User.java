package Entities;

import dataBaseManagement.model.ObjectFromDB;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User implements Serializable {
    private List<Order> orders;
    private HashMap<Params, String> params;//хранение параметров личного кабинета и т д

    public User(String name, String password, String email, String admin){
        this.params = new HashMap<Params, String>();
        this.params.put(Params.NAME,name);
        this.params.put(Params.PASSWORD,password);
        this.params.put(Params.EMAIL,email);
        this.params.put(Params.IS_ADMIN,admin);
        this.orders = new ArrayList<Order>();
    }

    public ObjectFromDB prepareObjectFromDB(){
        ObjectFromDB objToPersist = new ObjectFromDB();
        objToPersist.setName(params.get(Params.NAME));
        objToPersist.setType("user");
        byte[] parameters =  SerializationUtils.serialize(this);
        objToPersist.setParameters(parameters);
        return objToPersist;
    }

    public boolean authentificate(String password){
        return params.get(Params.PASSWORD).equals(password);
    }

    public boolean isadmin(){
        return params.get(Params.IS_ADMIN).equals("yes");
    }

    public static User parseUser(ObjectFromDB objectFromDB){
        User user = null;
        if (objectFromDB.getType().equals("user"))
            user = (User)SerializationUtils.deserialize(objectFromDB.getParameters());
        return user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public HashMap<Params, String> getParams() {
        return params;
    }

    public void setParams(HashMap<Params, String> params) {
        this.params = params;
    }
}
