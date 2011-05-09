/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zotyo.accounts.persistence;

import com.zotyo.accounts.model.Account;
import java.util.List;

/**
 *
 * @author Zoli
 */
public interface AccountDAO {
    List<Account> getAccounts();
    List<Account> getAccountsByProject(String project);
    Account getAccountByProjectAndName(String project, String name);
    void createAccount(Account a);
    boolean deleteAccount(Account a);
    Account updateAccount(Account a);
}
