package com.zotyo.accounts.persistence;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zotyo.accounts.model.Account;

@Service
public class AccountDAOJPAImpl implements AccountDAO {

	public void createAccount(Account a) {
		// TODO Auto-generated method stub

	}

	public boolean deleteAccount(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

	public Account getAccountByProjectAndName(String project, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Account> getAccountsByProject(String project) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account updateAccount(Account a) {
		// TODO Auto-generated method stub
		return null;
	}

}
