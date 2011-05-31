/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zotyo.accounts.web;

import com.zotyo.accounts.model.Account;
import com.zotyo.accounts.service.AccountService;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Zoli
 */
@Path("/accounts")
@Component
public class AccountResource {
    @Autowired
    private AccountService accountService;

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Account> getAccountsXML(@DefaultValue("ANY") @QueryParam("project") String project) {
        List<Account> accounts;
        if (project.equals("ANY")) {
            accounts = accountService.getAccounts();
        } else {
            accounts = accountService.getAccountsByProject(project);
        }
        return accounts;
    }

    @POST
    @Consumes("application/xml")
    @Produces("text/plain")
    public String createAccount(String representation) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Account.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(representation);
            Account account = (Account) unmarshaller.unmarshal(reader);
            if (Account.validateAccount(account)) {
                Account tmp = accountService.getAccountByProjectAndName(account.getProject(), account.getEntryname());
                if (tmp != null) {
                    return "Account entry already exists!";
                } else {
                    accountService.createAccount(account);
                    return "Successfully saved!";
                }
            } else {
                return account.getErrorMessage();
            }    
        } catch (JAXBException ex) {
            Logger.getLogger(AccountResource.class.getName()).log(Level.SEVERE, null, ex);
            return "Failed to save the account!";
        }    	
    }
    
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public String createAccountForm(@FormParam("project") String project,
            @FormParam("entryname") String entryname,
            @FormParam("url") String url,
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("password2") String password2,
            @FormParam("tag") String tag
            ) {
        Account account = new Account(project, entryname, url, username, password, password2, tag);
        if (Account.validateAccount(account)) {
            Account tmp = accountService.getAccountByProjectAndName(project, entryname);
            if (tmp != null) {
                return "Account entry already exists!";
            } else {
                accountService.createAccount(account);
                return "Successfully saved!";
            }
        } else {
            return account.getErrorMessage();
        }
    }
    
    @GET @Path("{project}/{name}")
    @Produces({"application/xml", "application/json"})
    public Account getAccount(@PathParam("project") String project, @PathParam("name") String entryname) {
        return accountService.getAccountByProjectAndName(project, entryname);
    }


    @PUT
    @Consumes("application/xml")
    @Produces({"application/xml", "application/json"})
    public Account updateAccount(String representation) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Account.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(representation);
            Account account = (Account) unmarshaller.unmarshal(reader);
            if (account.getProject() == null || account.getEntryname() == null) {
                return account;
            }
            return accountService.updateAccount(account);
        } catch (JAXBException ex) {
            Logger.getLogger(AccountResource.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @DELETE @Path("{project}/{name}")
    @Produces("text/plain")
    public String deleteAccount(@PathParam("project") String project, @PathParam("name") String entryname) {
        Account a = new Account();
        a.setProject(project);
        a.setEntryname(entryname);
        if (accountService.deleteAccount(a)) {
            return "Successfully deleted!";
        }
        return "Can't delete the Account!";
    }
}