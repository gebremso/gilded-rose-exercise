package com.solo.gilded.rose.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class NavigationController {

    @RequestMapping("/")
    public String home(){
        return "index";
    }

    @RequestMapping("/manage")
    public String manage(){return "manageItem";}

    @RequestMapping("/view")
    public String view(){return "viewItems";}

    @RequestMapping("/item-success/{id}")
    public String success(Model model, @PathVariable String id){
        model.addAttribute("itemId", id);
        return "success";}

}