package controllers;

import Entities.*;
import dataBaseManagement.model.ObjectFromDB;
import dataBaseManagement.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class ManagerController {
    private ObjectService objectService;

    @Autowired(required = true)
    @Qualifier(value = "objService")
    public void setObjectService(ObjectService objectService) {
        this.objectService = objectService;
    }

    @RequestMapping(value = "/manager/salons/show", method = RequestMethod.GET)
    public String showSalons(Model model){

        return "redirect:/showSalonsForManager";
    }

    @RequestMapping("/show/admin/{type}")
    public String showByType(@PathVariable("type") String type, Model model){
        List<ObjectFromDB> object = objectService.getByType(type);
        model.addAttribute("objects",object);
        model.addAttribute("type",type);
        if (type.equals("equipment")){
            model.addAttribute("name","Оборудование");
        }
        if (type.equals("salon")) {
            model.addAttribute("name","Салоны");
        }
        if (type.equals("order")) {
            model.addAttribute("name","Заказы");
        }
        return "showObjectsByType";
    }

    @RequestMapping("show/admin/objectsfromdbdata/{id}")
    public String objData(@PathVariable("id") BigInteger id, Model model){
        ObjectFromDB objectFromDB = this.objectService.getObjectById(id);
        if (objectFromDB.getType().equals("equipment")) {
            Equipment eq = Equipment.parseEquipment(objectFromDB);
            model.addAttribute("equipment",eq);
            return "showAndChangeEquipment";
        }
        if (objectFromDB.getType().equals("salon")) {
            Salon s = Salon.parseSalon(objectFromDB);
            List<Equipment> eq = new ArrayList<Equipment>();
            model.addAttribute("salon",s);
            for (ObjectFromDB o:
                    objectService.getByType("equipment")) {
                eq.add(Equipment.parseEquipment(o));
            }
            model.addAttribute("equips",eq);
            return "showAndChangeSalon";
        }
        if (objectFromDB.getType().equals("order")) {
            Order o = Order.parseOrder(objectFromDB);
            model.addAttribute("order",o);
            return "showAndChangeorder";
        }
        return  "objectsfromdbdata";
    }

    @RequestMapping("/createSalon")
    public String cp( Model model){
        List<Equipment> eq = new ArrayList<Equipment>();
        for (ObjectFromDB o:
             objectService.getByType("equipment")) {
            eq.add(Equipment.parseEquipment(o));
        }
        model.addAttribute("equips",eq);
        return "createSalon";
    }

    @RequestMapping(value = "/salon/add", method = RequestMethod.POST)
    public String addPerceptron(@RequestParam ("address") String address,
                                @RequestParam ("time") String time,
                                @RequestParam (value = "equip",required = false) List<Equipment> equip,
                                @RequestParam ("name") String name, Model model){
        Salon salon = new Salon(name);
        salon.setEquipments(equip);
        HashMap<Params, String> map= new HashMap<Params, String>();
        map.put(Params.ADDRESS,address);
        map.put(Params.TIME_TO_OFFER,time);
        salon.setParams(map);
        ObjectFromDB objectFromDB = salon.prepareObjectFromDB();
        BigInteger id = this.objectService.addObject(objectFromDB);
        objectFromDB = this.objectService.getObjectById(id);
        salon = Salon.parseSalon(objectFromDB);
        model.addAttribute("salon",salon);
        return "showSalon";
    }

    @RequestMapping(value = "/salon/addOrEdit", method = RequestMethod.POST)
    public String addPerceptron(@RequestParam ("id") String oldid,
                                @RequestParam ("address") String address,
                                @RequestParam ("time") String time,
                                @RequestParam (value = "equip", required = false) List<Equipment> equip,
                                @RequestParam ("name") String name,
                                @RequestParam ("flag") String flag, Model model){
        Salon salon = new Salon(name);
        salon.setEquipments(equip);
        HashMap<Params, String> map= new HashMap<Params, String>();
        map.put(Params.ADDRESS,address);
        map.put(Params.TIME_TO_OFFER,time);
        salon.setParams(map);
        Salon oldSalon = Salon.parseSalon(this.objectService.getObjectById(new BigInteger(oldid)));
        ObjectFromDB objectFromDB = salon.prepareObjectFromDB();
        objectFromDB.setId(new BigInteger(oldid));
        if (flag.equals("new")) {
            BigInteger id = this.objectService.addObject(objectFromDB);
            objectFromDB = this.objectService.getObjectById(id);
            salon = Salon.parseSalon(objectFromDB);
            model.addAttribute("salon", salon);
        }
        if (flag.equals("old")) {
            this.objectService.updateObject(objectFromDB,oldSalon.getName());
            objectFromDB = this.objectService.getObjectById(new BigInteger(oldid));
            salon = Salon.parseSalon(objectFromDB);
            model.addAttribute("salon", salon);
        }
        return "showSalon";
    }

}
