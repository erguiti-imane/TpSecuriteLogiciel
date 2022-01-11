package com.example.demo.controller;

import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String clientsList(Model model){
        model.addAttribute("clients",clientService.getClients());
        return "admin";
    }
}
