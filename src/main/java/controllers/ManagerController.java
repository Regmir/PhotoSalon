package controllers;

import Entities.User;
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
import java.util.List;

@Controller
public class ManagerController {
    private ObjectService objectService;

    @Autowired(required = true)
    @Qualifier(value = "objService")
    public void setObjcectService(ObjectService objectService) {
        this.objectService = objectService;
    }

    @RequestMapping(value = "objects", method = RequestMethod.GET)
    public String listObjects(Model model){
        model.addAttribute("obj", new ObjectFromDB());
        model.addAttribute("listObjects", this.objectService.getAll());
        return "objects";
    }

    @RequestMapping(value = "/objects/add", method = RequestMethod.POST)
    public String addObj(@ModelAttribute("obj") ObjectFromDB obj){
        this.objectService.addObject(obj);
        return "redirect:/objects";
    }

    @RequestMapping(value = "/manager/salons/show", method = RequestMethod.GET)
    public String showSalons(Model model){

        return "redirect:/showSalonsForManager";
    }

    @RequestMapping(value = "/perceptron/add", method = RequestMethod.POST)
    public String addPerceptron(@RequestParam ("neurons") Integer[] neurons,
                                @RequestParam ("func") String[] func,
                                @RequestParam ("name") String name, Model model){
        //someCode
        return "redirect:/showPerceptron";
    }
}
