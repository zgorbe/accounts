package com.zotyo.accounts.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zotyo.accounts.entity.AccountEntity;
import com.zotyo.accounts.model.Account;
import com.zotyo.accounts.util.EntityUtil;

@Transactional
@Repository
public class AccountDAOJPAImpl implements AccountDAO {

    @PersistenceContext(unitName = "AccountsPU")
    private EntityManager em;
	
	public void createAccount(Account a) {
		AccountEntity ae = EntityUtil.getAccountEntity(a);
		em.persist(ae);
	}

	public boolean deleteAccount(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

	public Account getAccountByProjectAndName(String project, String name) {
        AccountEntity a = getAccountByP_N(project, name);
        if (a != null) {
            return EntityUtil.getAccount(a);
        } else {
            return null;
        }
	}

	public List<Account> getAccounts() {
	    List<AccountEntity> result = em.createQuery("select object(o) from AccountEntity as o").getResultList();
	    List<Account> rv = new ArrayList<Account>();
	    for (AccountEntity ae : result) {
	        rv.add(EntityUtil.getAccount(ae));
	    }
		return rv;
	}

	public List<Account> getAccountsByProject(String project) {
        Query query = em.createNamedQuery("AccountEntity.findByP");
        query.setParameter("project", project);
        List<AccountEntity> result = query.getResultList();
        List<Account> rv = new ArrayList<Account>();
        for (AccountEntity ae : result) {
            rv.add(EntityUtil.getAccount(ae));
        }
        return rv;
	}

	public Account updateAccount(Account a) {
	    AccountEntity ae = getAccountByP_N(a.getProject(), a.getEntryname());
	    EntityUtil.copyAccountProperties(ae, a);
        em.merge(ae);
		return a;
	}
    
	private AccountEntity getAccountByP_N(String project, String name) {
        Query query = em.createNamedQuery("AccountEntity.findByP_E");
	    query.setParameter("project", project);
        query.setParameter("entryname", name);

        List<AccountEntity> result = query.getResultList();
        if (result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

    public List<String> getProjectNames() {
        List<String> rv = new ArrayList<String>();
        rv.add("one");
        rv.add("two");
        rv.add("three");
        return rv;
    }
	
}
