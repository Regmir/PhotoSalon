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
public class ObjectsFromDBController {
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

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addUser(@RequestParam ("name") String name,
                          @RequestParam ("pass") String pass,
                          @RequestParam ("secret") String secret,
                          @RequestParam ("email") String email, Model model){
        if (secret.equals("admin"))
            secret="yes";
        else
            secret="no";
        User user = new User(name,pass,email,secret);
        ObjectFromDB obj = user.prepareObjectFromDB();
        this.objectService.addObject(obj);
        return "redirect:/";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login(@RequestParam ("name") String name,
                          @RequestParam ("pass") String pass, Model model){
        ObjectFromDB obj = this.objectService.getObject(name,"user");
        if (obj!=null){
            User user = User.parseUser(obj);
            if (user.authentificate(pass)) {
                if (user.isadmin()) return  "adminMainPage";
                else return "userMainPage";
            }
        }
        return "authentificationFailed";
    }

    @RequestMapping("/login")
    public String login( Model model){
        return "login";
    }

    @RequestMapping("/register")
    public String register( Model model){
        return "register";
    }

    @RequestMapping(value = "/perceptron/add", method = RequestMethod.POST)
    public String addPerceptron(@RequestParam ("neurons") Integer[] neurons,
                                @RequestParam ("func") String[] func,
                                @RequestParam ("name") String name, Model model){
        //someCode
        return "showPerceptron";
    }
}
