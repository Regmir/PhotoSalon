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
        if (type.equals("worker")) {
            model.addAttribute("name","Работники");
        }
        if (type.equals("equipmenttype")) {
            model.addAttribute("name","Типы оборудования");
        }
        if (type.equals("offer")) {
            model.addAttribute("name","Услуги");
        }
        return "showObjectsByType";
    }

    @RequestMapping("/remove/{id}")
    public String removeObj(@PathVariable("id") BigInteger id, Model model){
        String type = this.objectService.getObjectById(id).getType();
        this.objectService.removeObject(id);
        return "redirect:/show/admin/"+type;
    }

    @RequestMapping("show/admin/objectsfromdbdata/{id}")
    public String objData(@PathVariable("id") BigInteger id, Model model){
        ObjectFromDB objectFromDB = this.objectService.getObjectById(id);
        if (objectFromDB.getType().equals("equipment")) {
            Equipment eq = Equipment.parseEquipment(objectFromDB);
            model.addAttribute("offer",eq.getEquipmentType().getAvailableOffers());
            model.addAttribute("type",eq.getEquipmentType().getName());
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
            List<Worker> w = new ArrayList<Worker>();
            for (ObjectFromDB o:
                    objectService.getByType("worker")) {
                w.add(Worker.parseWorker(o));
            }
            model.addAttribute("works",w);
            return "showAndChangeSalon";
        }
        if (objectFromDB.getType().equals("order")) {
            Order o = Order.parseOrder(objectFromDB);
            model.addAttribute("order",o);
            return "showAndChangeorder";
        }
        if (objectFromDB.getType().equals("worker")) {
            Worker o = Worker.parseWorker(objectFromDB);
            model.addAttribute("work",o);
            return "showAndChangeWorker";
        }
        if (objectFromDB.getType().equals("equipmenttype")) {
            EquipmentType o = EquipmentType.parseEquipmentType(objectFromDB);
            model.addAttribute("equipmenttype",o);
            List<Offer> e = new ArrayList<Offer>();
            for (ObjectFromDB of:
                    objectService.getByType("offer")) {
                e.add(Offer.parseOffer(of));
            }
            model.addAttribute("offer",e);
            return "showAndChangeEquipmentType";
        }
        if (objectFromDB.getType().equals("offer")) {
            Offer o = Offer.parseOffer(objectFromDB);
            model.addAttribute("of",o);
            return "showAndChangeOffer";
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
        List<Worker> w = new ArrayList<Worker>();
        for (ObjectFromDB o:
                objectService.getByType("worker")) {
            w.add(Worker.parseWorker(o));
        }
        model.addAttribute("works",w);
        return "createSalon";
    }

    @RequestMapping(value = "/salon/add", method = RequestMethod.POST)
    public String addSalon(@RequestParam ("address") String address,
                                @RequestParam ("time") String time,
                                @RequestParam (value = "equip",required = false) List<String> equip,
                                @RequestParam (value = "works", required = false) List<String> works,
                                @RequestParam ("name") String name, Model model){
        Salon salon = new Salon(name);
        ArrayList<Equipment> equiptrue = new ArrayList<Equipment>();
        ArrayList<Worker> workstrue = new ArrayList<Worker>();
        if (equip!=null)
        for (String s:equip
             ) {
            equiptrue.add(Equipment.parseEquipment(objectService.getObject(s,"equipment")));
        }
        if (works!=null)
        for (String s:works
        ) {
            workstrue.add(Worker.parseWorker(objectService.getObject(s,"worker")));
        }
        salon.setWorkers(workstrue);
        salon.setEquipments(equiptrue);
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
                                @RequestParam (value = "equip", required = false) List<String> equip,
                                @RequestParam (value = "works", required = false) List<String> works,
                                @RequestParam ("name") String name,
                                @RequestParam ("flag") String flag, Model model){
        Salon salon = new Salon(name);
        ArrayList<Equipment> equiptrue = new ArrayList<Equipment>();
        ArrayList<Worker> workstrue = new ArrayList<Worker>();
        if (equip!=null)
        for (String s:equip
        ) {
            equiptrue.add(Equipment.parseEquipment(objectService.getObject(s,"equipment")));
        }
        if (works!=null)
        for (String s:works
        ) {
            workstrue.add(Worker.parseWorker(objectService.getObject(s,"worker")));
        }
        salon.setWorkers(workstrue);
        salon.setEquipments(equiptrue);
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

    @RequestMapping("/createEquipmentType")
    public String ceq( Model model){
        List<Offer> e = new ArrayList<Offer>();
        for (ObjectFromDB o:
                objectService.getByType("offer")) {
            e.add(Offer.parseOffer(o));
        }
        model.addAttribute("offer",e);
        return "createEquipmentType";
    }

    @RequestMapping(value = "/equipmenttype/add", method = RequestMethod.POST)
    public String addEquipmentType(@RequestParam (value = "ablt", required = false) List<Offer> ablt,
                                   @RequestParam ("name") String name, Model model){
        EquipmentType equipmentType = new EquipmentType(name);
        equipmentType.setAvailableOffers(ablt);
        ObjectFromDB objectFromDB = equipmentType.prepareObjectFromDB();
        BigInteger id = this.objectService.addObject(objectFromDB);
        objectFromDB = this.objectService.getObjectById(id);
        equipmentType = EquipmentType.parseEquipmentType(objectFromDB);
        model.addAttribute("equipt",equipmentType);
        return "showEquipmentType";
    }

    @RequestMapping(value = "/equipmenttype/addOrEdit", method = RequestMethod.POST)
    public String addEquipmentType(@RequestParam ("id") String oldid,
                                @RequestParam (value = "ablt", required = false) List<Offer> ablt,
                                @RequestParam ("name") String name,
                                @RequestParam ("flag") String flag, Model model){
        EquipmentType equipmentType = new EquipmentType(name);
        equipmentType.setAvailableOffers(ablt);
        EquipmentType oldequipmentType = EquipmentType.parseEquipmentType(this.objectService.getObjectById(new BigInteger(oldid)));
        ObjectFromDB objectFromDB = equipmentType.prepareObjectFromDB();
        objectFromDB.setId(new BigInteger(oldid));
        if (flag.equals("new")) {
            BigInteger id = this.objectService.addObject(objectFromDB);
            objectFromDB = this.objectService.getObjectById(id);
            equipmentType = EquipmentType.parseEquipmentType(objectFromDB);
            model.addAttribute("equipt", equipmentType);
        }
        if (flag.equals("old")) {
            this.objectService.updateObject(objectFromDB,oldequipmentType.getName());
            objectFromDB = this.objectService.getObjectById(new BigInteger(oldid));
            equipmentType = EquipmentType.parseEquipmentType(objectFromDB);
            model.addAttribute("equipt", equipmentType);
        }
        return "showEquipmentType";
    }

    @RequestMapping("/createEquipment")
    public String ce( Model model){
        List<EquipmentType> e = new ArrayList<EquipmentType>();
        for (ObjectFromDB o:
                objectService.getByType("equipmenttype")) {
            e.add(EquipmentType.parseEquipmentType(o));
        }
        model.addAttribute("type",e);
        return "createEquipment";
    }

    @RequestMapping(value = "/createEquipment/proceed",method = RequestMethod.POST)
    public String cep( @RequestParam ("name") String name,
                       @RequestParam ("type") String type, Model model){
        model.addAttribute("type",type);
        model.addAttribute("name",name);
        List<Offer> of = EquipmentType.parseEquipmentType(objectService.getObject(type,"equipmenttype")).getAvailableOffers();
        model.addAttribute("offer",of);
        return "createEquipment2";
    }

    @RequestMapping(value = "/equipment/add", method = RequestMethod.POST)
    public String addEquipment(@RequestParam (value = "ablt",required = false) List<Offer> ablt,
                               @RequestParam ("type") String type,
                               @RequestParam ("name") String name, Model model){
        EquipmentType et = EquipmentType.parseEquipmentType(objectService.getObject(type,"equipmenttype"));
        Equipment equipment = new Equipment(name,et);
        equipment.setOffers(ablt);
        ObjectFromDB objectFromDB = equipment.prepareObjectFromDB();
        BigInteger id = this.objectService.addObject(objectFromDB);
        objectFromDB = this.objectService.getObjectById(id);
        equipment = Equipment.parseEquipment(objectFromDB);
        model.addAttribute("equip",equipment);
        return "showEquipment";
    }

    @RequestMapping(value = "/equipment/addOrEdit", method = RequestMethod.POST)
    public String addEquipment(@RequestParam ("id") String oldid,
                               @RequestParam (value = "ablt",required = false) List<Offer> ablt,
                               @RequestParam ("type") String type,
                               @RequestParam ("name") String name,
                               @RequestParam ("flag") String flag, Model model){
        EquipmentType et = EquipmentType.parseEquipmentType(objectService.getObject(type,"equipmenttype"));
        Equipment equipment = new Equipment(name,et);
        equipment.setOffers(ablt);
        Equipment oldequipment = Equipment.parseEquipment(this.objectService.getObjectById(new BigInteger(oldid)));
        ObjectFromDB objectFromDB = equipment.prepareObjectFromDB();
        objectFromDB.setId(new BigInteger(oldid));
        if (flag.equals("new")) {
            BigInteger id = this.objectService.addObject(objectFromDB);
            objectFromDB = this.objectService.getObjectById(id);
            equipment = Equipment.parseEquipment(objectFromDB);
            model.addAttribute("equip", equipment);
        }
        if (flag.equals("old")) {
            this.objectService.updateObject(objectFromDB,oldequipment.getName());
            objectFromDB = this.objectService.getObjectById(new BigInteger(oldid));
            equipment = Equipment.parseEquipment(objectFromDB);
            model.addAttribute("equip", equipment);
        }
        return "showEquipment";
    }

    @RequestMapping("/createOffer")
    public String cf( Model model){
        return "createOffer";
    }

    @RequestMapping(value = "/offer/add", method = RequestMethod.POST)
    public String addOffer(@RequestParam (value = "pname",required = false) List<String> pname,
                               @RequestParam (value = "pval",required = false) List<String> pval,
                               @RequestParam ("name") String name, Model model){
        Offer offer = new Offer(name);
        HashMap<Params,String> map = new HashMap<Params,String>();
        int i=0;
        for (String s:pname
             ) {
            if (s.equals("TIME_TO_OFFER")){map.put(Params.TIME_TO_OFFER,pval.get(i)); i++;}
            if (s.equals("OFFER_PRICE")){map.put(Params.OFFER_PRICE,pval.get(i)); i++;}
            if (s.equals("DESCRIPTION")){map.put(Params.DESCRIPTION,pval.get(i)); i++;}
        }
        offer.setParams(map);
        ObjectFromDB objectFromDB = offer.prepareObjectFromDB();
        BigInteger id = this.objectService.addObject(objectFromDB);
        objectFromDB = this.objectService.getObjectById(id);
        offer = Offer.parseOffer(objectFromDB);
        model.addAttribute("offer",offer);
        return "showOffer";
    }

    @RequestMapping(value = "/offer/addOrEdit", method = RequestMethod.POST)
    public String addOffer(@RequestParam ("id") String oldid,
                           @RequestParam (value = "pname",required = false) List<String> pname,
                           @RequestParam (value = "pval",required = false) List<String> pval,
                           @RequestParam ("name") String name,
                           @RequestParam ("flag") String flag, Model model){
        Offer offer = new Offer(name);
        HashMap<Params,String> map = new HashMap<Params,String>();
        int i=0;
        for (String s:pname
        ) {
            if (s.equals("TIME_TO_OFFER")){map.put(Params.TIME_TO_OFFER,pval.get(i)); i++;}
            if (s.equals("OFFER_PRICE")){map.put(Params.OFFER_PRICE,pval.get(i)); i++;}
            if (s.equals("DESCRIPTION")){map.put(Params.DESCRIPTION,pval.get(i)); i++;}
        }
        offer.setParams(map);
        Offer oldoffer = Offer.parseOffer(this.objectService.getObjectById(new BigInteger(oldid)));
        ObjectFromDB objectFromDB = offer.prepareObjectFromDB();
        objectFromDB.setId(new BigInteger(oldid));
        if (flag.equals("new")) {
            BigInteger id = this.objectService.addObject(objectFromDB);
            objectFromDB = this.objectService.getObjectById(id);
            offer = Offer.parseOffer(objectFromDB);
            model.addAttribute("offer", offer);
        }
        if (flag.equals("old")) {
            this.objectService.updateObject(objectFromDB,oldoffer.getName());
            objectFromDB = this.objectService.getObjectById(new BigInteger(oldid));
            offer = Offer.parseOffer(objectFromDB);
            model.addAttribute("offer", offer);
        }
        return "showOffer";
    }

    @RequestMapping("/createWorker")
    public String cw( Model model){
        return "createWorker";
    }

    @RequestMapping(value = "/worker/add", method = RequestMethod.POST)
    public String addWorker(@RequestParam ("name") String name, Model model){
        Worker worker = new Worker(name);
        ObjectFromDB objectFromDB = worker.prepareObjectFromDB();
        BigInteger id = this.objectService.addObject(objectFromDB);
        objectFromDB = this.objectService.getObjectById(id);
        worker = Worker.parseWorker(objectFromDB);
        model.addAttribute("work",worker);
        return "showWorker";
    }

    @RequestMapping(value = "/worker/addOrEdit", method = RequestMethod.POST)
    public String addOffer(@RequestParam ("name") String name,
                           @RequestParam ("id") String oldid,
                           @RequestParam ("flag") String flag, Model model){
        Worker worker = new Worker(name);
        Worker oldworker = Worker.parseWorker(this.objectService.getObjectById(new BigInteger(oldid)));
        ObjectFromDB objectFromDB = worker.prepareObjectFromDB();
        objectFromDB.setId(new BigInteger(oldid));
        if (flag.equals("new")) {
            BigInteger id = this.objectService.addObject(objectFromDB);
            objectFromDB = this.objectService.getObjectById(id);
            worker = Worker.parseWorker(objectFromDB);
            model.addAttribute("worker", worker);
        }
        if (flag.equals("old")) {
            this.objectService.updateObject(objectFromDB,oldworker.getName());
            objectFromDB = this.objectService.getObjectById(new BigInteger(oldid));
            worker = Worker.parseWorker(objectFromDB);
            model.addAttribute("work", worker);
        }
        return "showWorker";
    }

}
