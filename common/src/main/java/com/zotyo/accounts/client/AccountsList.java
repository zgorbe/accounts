package com.zotyo.accounts.client;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.zotyo.accounts.model.Account;

@XmlRootElement(name="accounts")
public class AccountsList {
	
	List<Account> accountsList;

	@XmlElement(name="account")
	public List<Account> getAccounts() {
		return accountsList;
	}

	public void setAccounts(List<Account> accounts) {
		accountsList = accounts;
	}
}
