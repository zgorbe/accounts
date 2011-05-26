package com.zotyo.accounts.controller;

import java.io.StringReader;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zotyo.accounts.client.AccountsClient;
import com.zotyo.accounts.client.AccountsClientImpl;
import com.zotyo.accounts.common.Constants;

@Controller
public class AccountsController {
    @RequestMapping("/accounts/home.html")
    public ModelAndView home() {
        AccountsClient client = new AccountsClientImpl(Constants.REST_URL_PROJECTS);
        String projects = client.getProjectNamesXML();
        final StringReader xmlReader = new StringReader(projects);
        return new ModelAndView("home", "xmlSource", xmlReader);
    }
    
    @RequestMapping("/accounts/projects/{project}.html")
    public ModelAndView project(@PathVariable String project) {
        AccountsClient client = new AccountsClientImpl(Constants.REST_URL);
        String projects = client.getAccountsByProjectXML(project);
        final StringReader xmlReader = new StringReader(projects);
        return new ModelAndView("project", "xmlSource", xmlReader);
    }
}
