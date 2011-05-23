/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zotyo.accounts.service;

import com.zotyo.accounts.model.Account;
import com.zotyo.accounts.persistence.AccountDAO;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zoli
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDAO accountDAO;

    public List<Account> getAccounts() {
        return accountDAO.getAccounts();
    }

    public List<Account> getAccountsByProject(String project) {
        return accountDAO.getAccountsByProject(project);
    }

    public Account getAccountByProjectAndName(String project, String name) {
        return accountDAO.getAccountByProjectAndName(project, name);
    }

    public void createAccount(Account a) {
    	a.setLastModified(new Date());
        accountDAO.createAccount(a);
    }

    public boolean deleteAccount(Account a) {
        return accountDAO.deleteAccount(a);
    }

    public Account updateAccount(Account a) {
    	a.setLastModified(new Date());
        return accountDAO.updateAccount(a);
    }

    
    public List<String> getProjectNames() {
        return accountDAO.getProjectNames();
    }

}
