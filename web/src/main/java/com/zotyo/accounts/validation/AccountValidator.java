package com.zotyo.accounts.validation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.zotyo.accounts.model.Account;

@Scope("prototype")
@Component
public class AccountValidator {
    public boolean supports(Class clazz) {
        return Account.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {
        //TODO implement it properly...
        Account account = (Account) obj;
        
        if (account.getEntryname() == null || account.getEntryname().trim().length() == 0) {
            e.rejectValue("entryname", "validation.empty.entryname");
        }
        if (account.getUrl() == null || account.getUrl().trim().length() == 0) {
            e.rejectValue("url", "validation.empty.url");
        }
    }
}
