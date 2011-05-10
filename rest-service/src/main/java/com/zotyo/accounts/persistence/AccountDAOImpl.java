/**
 * DB4O has been removed from the project. 
 * The DB4O Maven Repo is permanently unavailable. Let's move to JPA 2
 */

/**
package com.zotyo.accounts.persistence;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.zotyo.accounts.common.Constants;
import com.zotyo.accounts.model.Account;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;


@Service
public class AccountDAOImpl implements AccountDAO, ServletContextAware {
    private ServletContext servletContext;

    public List<Account> getAccounts() {
        List<Account> rv = new ArrayList<Account>();
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
                servletContext.getRealPath(Constants.DB_FILE));
        ObjectSet<Account> result = db.queryByExample(new Account());
        while (result.hasNext()) {
            rv.add(result.next());
        }
        db.close();
        return rv;
    }

    public List<Account> getAccountsByProject(String project) {
        List<Account> rv = new ArrayList<Account>();
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
                servletContext.getRealPath(Constants.DB_FILE));
        Account example = new Account();
        example.setProject(project);
        ObjectSet<Account> result = db.queryByExample(example);
        while (result.hasNext()) {
            rv.add(result.next());
        }
        db.close();
        return rv;
    }

    public Account getAccountByProjectAndName(String project, String name) {
        ObjectContainer db = null;
        try {
            db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
                    servletContext.getRealPath(Constants.DB_FILE));
            Account example = new Account();
            example.setProject(project);
            example.setEntryname(name);
            ObjectSet<Account> result = db.queryByExample(example);
            if (result.hasNext()) {
                return result.next();
            } else {
                return null;
            }
        } finally {
            if (db != null)
                db.close();
        }
    }

    public void createAccount(Account a) {
        ObjectContainer db = null;
        try {
            db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
                    servletContext.getRealPath(Constants.DB_FILE));
            db.store(a);
        } finally {
            if (db != null)
                db.close();
        }
    }

    public boolean deleteAccount(Account a) {
        ObjectContainer db = null;
        try {
            db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
                    servletContext.getRealPath(Constants.DB_FILE));
            ObjectSet<Account> result = db.queryByExample(new Account(a.getProject(), a.getEntryname()));
            if (result.hasNext()) {
                Account account = result.next();
                db.delete(account);
                return true;
            } else {
                return false;
            }
        } finally {
            if (db != null)
                db.close();
        }
    }

    public Account updateAccount(Account a) {
        ObjectContainer db = null;
        try {
            db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
                    servletContext.getRealPath(Constants.DB_FILE));
            ObjectSet<Account> result = db.queryByExample(new Account(a.getProject(), a.getEntryname()));

            if (result.hasNext()) {
                Account account = result.next();
                if (a.getPassword() != null) {
                	account.setPassword(a.getPassword());
                }
                if (a.getUrl() != null) {
                	account.setUrl(a.getUrl());
                }
                if (a.getUsername() != null) {
                	account.setUsername(a.getUsername());
                }
                if (a.getTag() != null) {
                	account.setTag(a.getTag());
                }
                account.setLastModified(new Date());
                db.store(account);
                return account;
            } else {
                return a;
            }
        } finally {
            if (db != null)
                db.close();
        }
    }

    public void setServletContext(ServletContext sc) {
        servletContext = sc;
    }
}
*
**/