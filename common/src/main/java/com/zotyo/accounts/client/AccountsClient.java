package com.zotyo.accounts.client;

import java.util.List;

import com.zotyo.accounts.model.Account;

public interface AccountsClient {
    List<Account> getAccounts();
    List<Account> getAccountsByProject(String project);
    Account getAccountByProjectAndName(String project, String name);
    String createAccount(Account a);
    boolean deleteAccount(Account a);
    Account updateAccount(Account a);
}
