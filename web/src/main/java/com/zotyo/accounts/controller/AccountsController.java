package com.zotyo.accounts.controller;

import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zotyo.accounts.client.AccountsClient;
import com.zotyo.accounts.client.AccountsClientImpl;
import com.zotyo.accounts.common.Constants;
import com.zotyo.accounts.model.Account;

@Controller
public class AccountsController {
	public static final String EMPTY_ACCOUNT = "<account></account>";
	
    @RequestMapping(value="/accounts/home.html", method=RequestMethod.GET)
    public ModelAndView home() {
        AccountsClient client = new AccountsClientImpl(Constants.REST_URL_PROJECTS);
        String projects = client.getProjectNamesXML();
        final StringReader xmlReader = new StringReader(projects);
        return new ModelAndView("home", "xmlSource", xmlReader);
    }
    
    @RequestMapping(value="/accounts/projects/{project}.html", method=RequestMethod.GET)
    public ModelAndView project(@PathVariable String project) {
        AccountsClient client = new AccountsClientImpl(Constants.REST_URL);
        String projects = client.getAccountsByProjectXML(project);
        final StringReader xmlReader = new StringReader(projects);
        return new ModelAndView("project", "xmlSource", xmlReader);
    }
    
    @RequestMapping(value="/accounts/new.html", method=RequestMethod.GET)
    public ModelAndView newAccount() {
        AccountsClient client = new AccountsClientImpl(Constants.REST_URL_PROJECTS);
        String projects = client.getProjectNamesXML();
        final StringReader xmlReader = new StringReader(projects);
    	//final StringReader xmlReader = new StringReader(EMPTY_ACCOUNT);
        return new ModelAndView("new", "xmlSource", xmlReader);
    }

    @RequestMapping(value="/accounts/create.html", method=RequestMethod.POST)
    public void createAccount(Account account) {
    	System.out.println(account.getProject());
    }
}
