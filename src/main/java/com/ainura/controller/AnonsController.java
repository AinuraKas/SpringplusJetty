/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ainura.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/newanons")
/**
 *
 * @author Ainura_Andr
 */
public class AnonsController {
   


    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        
        return "anons.html";
}
}
