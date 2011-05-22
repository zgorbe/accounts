package com.zotyo.accounts.controller;

import java.io.StringReader;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zotyo.accounts.client.AccountsClient;
import com.zotyo.accounts.client.AccountsClientImpl;
import com.zotyo.accounts.common.Constants;

@Controller
public class AccountsController {
    @RequestMapping("/accounts/home.html")
    public ModelAndView home() {
        AccountsClient client = new AccountsClientImpl(Constants.REST_URL);
        String accounts = client.getAccountsXML();
        final StringReader xmlReader = new StringReader(accounts);
        return new ModelAndView("home", "xmlSource", xmlReader);
    }
}
