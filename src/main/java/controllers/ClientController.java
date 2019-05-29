package controllers;

import Entities.*;
import dataBaseManagement.model.ObjectFromDB;
import dataBaseManagement.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Controller
public class ClientController {
    private ObjectService objectService;

    @Autowired(required = true)
    @Qualifier(value = "objService")
    public void setObjectService(ObjectService objectService) {
        this.objectService = objectService;
    }

    @RequestMapping("/createOrder")
    public String createOrder(Model model) {
        HashSet<Offer> offers = new HashSet<Offer>();
        for (ObjectFromDB o : objectService.getByType("salon")) {
            for (Equipment e : Salon.parseSalon(o).getEquipments()) {
                offers.addAll(e.getOffers());
            }
        }
        model.addAttribute("offers", new ArrayList<Offer>(offers));//надо будет потом исключить из списка оффера, заказы для которых мы делаем отдельно - печать на майку и т.д.
        return "createOrder";
    }

    @RequestMapping(value = "/order/addPhotos", method = RequestMethod.POST)
    public String addPhotos(@RequestParam("photosData") String photosData, Model model) {//изменить тип данных
        ArrayList<Photo> photos = new ArrayList<Photo>();
        //парсим данные в Photo
        model.addAttribute("photos", photos);
        return "createOrder";
    }

    @RequestMapping(value = "/order/photo/addOffers", method = RequestMethod.POST)
//наверное не нужен так как это можно сделать прямо в jsp
    public String addOffers(@RequestParam("photo") Photo photo,
                            @RequestParam("offers") List<Offer> offers, Model model) {
        photo.setAppliedOffers(offers);
        return "createOrder";
    }

    @RequestMapping(value = "/{name}/createOrder/{typeo}", method = RequestMethod.GET)
    public String tempaddorder(@PathVariable("name") String name,
                               @PathVariable("typeo") String type, Model model) {
        int counter = objectService.getByType("order").size();
        Order order = new Order();
        order.setName("order number "+String.valueOf(counter+1));
        order.setSalonId(BigInteger.valueOf(0));
        order.setStatus("processing");
        order.setUsrname(name);
        if (type.equals("tshort")){
            order.setType("T-Short");
            order.setPrice("2000");
        }
        if (type.equals("book")){
            order.setType("Photobook");
            order.setPrice("1500");
        }
        if (type.equals("mouse")){
            order.setType("MousePrint");
            order.setPrice("500");
        }
        if (type.equals("photo")){
            order.setType("Photo");
            order.setPrice("150");
        }
        ObjectFromDB obj = order.prepareObjectFromDB();
        model.addAttribute("name",name);
        this.objectService.addObject(obj);
        return "userMainPage";
    }

    @RequestMapping(value = "/{name}/cabinet", method = RequestMethod.GET)
    public String tempcabinet(@PathVariable("name") String name,
                                Model model) {
        model.addAttribute("name",name);
        return "userCabinet";
    }

    @RequestMapping(value = "show/{name}/order", method = RequestMethod.GET)
    public String temposrorder(@PathVariable("name") String name,
                              Model model) {
        ArrayList<Order> orders = new ArrayList<Order>();
        List<ObjectFromDB> obj = objectService.getByType("order");
        for (ObjectFromDB o: obj
             ) {
            Order ord = Order.parseOrder(o);
            if (name.equals(ord.getUsrname())){ orders.add(ord);}
            if (name.equals("admin")){ orders.add(ord);}
        }
        model.addAttribute("orders",orders);
        if (name.equals("admin")) return "showAdminOrders";
        return "showUserOrders";
    }

    @RequestMapping(value = "/show/user/salon")
    public String tsalon(Model model) {
        ArrayList<Salon> salons = new ArrayList<Salon>();
        List<ObjectFromDB> obj = objectService.getByType("salon");
        for (ObjectFromDB o: obj
        ) {
            Salon ord = Salon.parseSalon(o);
             salons.add(ord);
        }
        model.addAttribute("salons",salons);
        return "showUserSalons";
    }

    @RequestMapping(value = "/order/add", method = RequestMethod.POST)
    public String addOrder(@RequestParam("photos") List<Photo> photos,//в jsp надо добавить проверку что списok не должны быть пустым
                           @RequestParam("address") String address,
                           @RequestParam("isDelivery") String isDelivery, Model model) {
        Order order = new Order();
        order.setPhotos(photos);
        Double price = order.getOrderPrice();
        Double time = order.getTimeToOrder();
        HashMap<Params, String> params = new HashMap<Params, String>();
        params.put(Params.ADDRESS, address);
        params.put(Params.IS_DELIVERY, isDelivery);
        if (isDelivery.equals("True")) {
            price += Constants.DELIVERY_PRICE;
            time += Constants.DELIVERY_TIME;
        }
        params.put(Params.ORDER_PRICE, price.toString());
        params.put(Params.ORDER_TIME, time.toString());
        order.setParams(params);
        ObjectFromDB objectFromDB = order.prepareObjectFromDB();
        BigInteger id = this.objectService.addObject(objectFromDB);
        objectFromDB = this.objectService.getObjectById(id);
        order = Order.parseOrder(objectFromDB);
        model.addAttribute("order", order);
        return "showOrder";
    }

    @RequestMapping("/show/client/{type}")
    public String showByType(@PathVariable("type") String type, Model model) {
        List<ObjectFromDB> object = objectService.getByType(type);
        model.addAttribute("objects", object);
        model.addAttribute("type", type);
        if (type.equals("salon")) {
            model.addAttribute("name", "Салоны");
        }
        if (type.equals("order")) {
            model.addAttribute("name", "Заказы");
        }
        if (type.equals("offer")) {
            model.addAttribute("name", "Услуги");
        }
        return "showObjectsByTypeForClient";
    }

    @RequestMapping("show/client/objectsfromdbdata/{id}")
    public String objData(@PathVariable("id") BigInteger id, Model model) {
        ObjectFromDB objectFromDB = this.objectService.getObjectById(id);
        if (objectFromDB.getType().equals("salon")) {
            Salon s = Salon.parseSalon(objectFromDB);
            List<Equipment> eq = new ArrayList<Equipment>();
            model.addAttribute("salon", s);
            return "showSalon";
        }
        if (objectFromDB.getType().equals("order")) {
            Order o = Order.parseOrder(objectFromDB);
            model.addAttribute("order", o);
            return "showOrder";
        }
        if (objectFromDB.getType().equals("offer")) {
            Offer o = Offer.parseOffer(objectFromDB);
            model.addAttribute("of", o);
            return "showOffer";
        }
        return "objectsfromdbdata";
    }

}
