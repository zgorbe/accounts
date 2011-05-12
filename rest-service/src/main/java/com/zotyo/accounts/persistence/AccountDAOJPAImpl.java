package com.zotyo.accounts.persistence;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.zotyo.accounts.model.Account;

@Repository
public class AccountDAOJPAImpl implements AccountDAO {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
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
