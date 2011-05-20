package com.zotyo.accounts.util;

import com.zotyo.accounts.entity.AccountEntity;
import com.zotyo.accounts.model.Account;

public class EntityUtil {
    public static Account getAccount(AccountEntity a) {
        Account rv = new Account(a.getProject(), a.getEntryname(), a.getUrl(), 
                a.getUsername(), a.getPassword(), a.getPassword2(), a.getTag());
        rv.setLastModified(a.getLastModified());
        return rv;
    }
    
    public static AccountEntity getAccountEntity(Account a) {
        AccountEntity rv = new AccountEntity(a.getProject(), a.getEntryname(), a.getUrl(), 
                a.getUsername(), a.getPassword(), a.getPassword2(), a.getTag());
        rv.setLastModified(a.getLastModified());
        return rv;
    }
    public static void copyAccountProperties(AccountEntity target, Account src) {
        target.setProject(src.getProject());
        target.setEntryname(src.getEntryname());
        target.setUrl(src.getUrl());
        target.setUsername(src.getUsername());
        target.setPassword(src.getPassword());
        target.setPassword2(src.getPassword2());
        target.setTag(src.getTag());
        target.setLastModified(src.getLastModified());
    }
}
