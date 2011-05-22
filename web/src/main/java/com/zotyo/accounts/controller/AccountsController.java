package com.zotyo.accounts.controller;

import java.io.StringReader;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountsController {
    @RequestMapping("/accounts/home.html")
    public ModelAndView home() {
        final StringReader xmlReader = new StringReader(
                "<?xml version='1.0' encoding='ISO-8859-1'?>" +
                "<message>This is added to the model in the XML</message>");
        return new ModelAndView("home", "xmlSource", xmlReader);
    }
}
