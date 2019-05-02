package Entities;

import dataBaseManagement.model.ObjectFromDB;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class User implements Serializable {

    List<Order> orders;
    HashMap<String, String> params;//хранение параметров личного кабинета и т д

    public User(String name, String password, String email, String admin){
        this.params.put("name",name);
        this.params.put("password",password);
        this.params.put("email",email);
        this.params.put("admin",admin);
    }

    public ObjectFromDB prepareObjectFromDB(){
        ObjectFromDB objToPersist = new ObjectFromDB();
        objToPersist.setName(params.get("name"));
        objToPersist.setType("user");
        byte[] parameters =  SerializationUtils.serialize(this);
        objToPersist.setParameters(parameters);
        return  objToPersist;
    }

    public boolean authentificate(String password){
        return params.get("password").equals(password);
    }

    public boolean isadmin(){
        return params.get("admin").equals("yes");
    }

    public static User parseUser(ObjectFromDB objectFromDB){
        User user = null;
        if (objectFromDB.getType().equals("user"))
            user = (User)SerializationUtils.deserialize(objectFromDB.getParameters());
        return user;
    }
}
