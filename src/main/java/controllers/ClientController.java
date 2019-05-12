package controllers;

import Entities.Offer;
import Entities.Order;
import Entities.Salon;
import dataBaseManagement.service.ObjectService;
import functionality.ClientMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ClientController {
    private ObjectService objectService;

    @Autowired(required = true)
    @Qualifier(value = "objService")
    public void setObjectService(ObjectService objectService) {
        this.objectService = objectService;
    }

    @RequestMapping(value = "/client/salons/get", method = RequestMethod.GET)
    public List<Salon> getSalons(){
        return ClientMethods.getSalons(objectService.getByType("salon"));
    }

    @RequestMapping(value = "/client/offers/get", method = RequestMethod.GET)
    public List<Offer> getOffers(){
        return ClientMethods.getOffers(objectService.getByType("offer"));
    }

    @RequestMapping(value = "/client/orders/get", method = RequestMethod.GET)
    public List<Order> getOrders(@RequestParam("clientName") String clientName){
        return ClientMethods.getOrders(objectService.getObject(clientName, "user"));
    }

    @RequestMapping(value = "/client/orders/change", method = RequestMethod.POST)
    public List<Order> getOrders(@RequestParam("clientName") String clientName, @RequestParam("orderName") Order order){
        return ClientMethods.changeOrders(objectService.getObject(clientName, "user"), order);
    }

}