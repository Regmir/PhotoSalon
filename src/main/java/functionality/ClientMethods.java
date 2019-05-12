package functionality;

import Entities.Offer;
import Entities.Order;
import Entities.Salon;
import Entities.User;
import dataBaseManagement.model.ObjectFromDB;

import java.util.ArrayList;
import java.util.List;

public class ClientMethods {
    public static List<Salon> getSalons(List<ObjectFromDB> listFromDB){
        ArrayList<Salon> salons = new ArrayList<Salon>(listFromDB.size());
        for (ObjectFromDB objectFromDB : listFromDB){
            Salon.parseSalon(objectFromDB);
        }
        return salons;
    }

    public static List<Offer> getOffers(List<ObjectFromDB> listFromDB){
        ArrayList<Offer> offers = new ArrayList<Offer>(listFromDB.size());
        for (ObjectFromDB objectFromDB : listFromDB){
            Offer.parseOffer(objectFromDB);
        }
        return offers;
    }

    public static List<Order> getOrders(ObjectFromDB userObject){
        return User.parseUser(userObject).getOrders();
    }

    public static List<Order> changeOrders(ObjectFromDB userObject, Order order){
        List<Order> orders = User.parseUser(userObject).getOrders();
        for (Order o:orders){
            if(o.getName().equals(order.getName())){
                orders.remove(o);
            }
        }
        orders.add(order);
        return orders;
    }
}
