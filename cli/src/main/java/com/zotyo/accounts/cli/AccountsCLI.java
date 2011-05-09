package com.zotyo.accounts.cli;

import com.zotyo.accounts.client.AccountsClient;
import com.zotyo.accounts.client.AccountsClientImpl;
import com.zotyo.accounts.common.Constants;
import com.zotyo.accounts.model.Account;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * AccountsCLI
 *
 */
public class AccountsCLI {
    
    private static String REST_URL = "http://localhost:8080/accounts/rest/accounts";
    private static final String PROMPT = "Accs> ";


    private static void initDB() {
        AccountsClient client = new AccountsClientImpl(REST_URL);
        Account test = new Account(Constants.PROJECT_ONE, "test", "test.test.hu", "test", "test", "test", "test");
        client.createAccount(test);
        Account test2 = new Account(Constants.PROJECT_TWO, "test2", "test2.test.hu", "test2", "test2", "test2", "test2");
        client.createAccount(test2);
        Account testh = new Account(Constants.PROJECT_THREE, "testh", "testh.test.hu", "testh", "testh", "testh", "testh");
        client.createAccount(testh);
    	Account testl = new Account(Constants.PROJECT_TWO, "testl", "testl.test.hu", "testl", "testl", "testl", "testl");
        client.createAccount(testl);
    }    
    private static void usage() {
        System.out.println("********** USAGE *************");
        System.out.println("Projects:\n - one\n - two\n - three");
        System.out.println("Commands:\n - create %PROJECT% %ENTRYNAME%\n - list %PROJECT%\n - get %PROJECT% %ENTRYNAME%\n - update %PROJECT% %ENTRYNAME%\n - delete %PROJECT% %ENTRYNAME%\n - exit");
        System.out.println("******************************");
    }
    
    private static boolean authenticate() {
        return true;
    }

    private static void doCreate(String[] lineSplitted) {
    	if (lineSplitted.length != 3) {
            usage();
            return;
        }
        AccountsClient client = new AccountsClientImpl(REST_URL);
        Account account = client.getAccountByProjectAndName(lineSplitted[1].toUpperCase(), lineSplitted[2]);
        if (account == null) {
        	account = new Account();
        	account.setProject(lineSplitted[1].toUpperCase());
        	account.setEntryname(lineSplitted[2]);
        	boolean success = editAccount(account);
        	if (success) {
        		client.createAccount(account);
        	}
        } else {
        	System.out.println("Entry already exists");
        }
    }    
    
    private static void doList(String[] lineSplitted) {
        if (lineSplitted.length != 2) {
            usage();
            return;
        }
        AccountsClient client = new AccountsClientImpl(REST_URL);
        List<Account> accs = client.getAccountsByProject(lineSplitted[1].toUpperCase());
        if (accs == null) {
        	System.out.println("No entry found for the " + lineSplitted[1] + " project");
        } else {
	        for (Account a : accs) {
	            System.out.println(a);
	        }
        }
    }
    
    private static Account doGet(String[] lineSplitted) {
    	if (lineSplitted.length != 3) {
            usage();
            return null;
        }
        AccountsClient client = new AccountsClientImpl(REST_URL);
        Account account = client.getAccountByProjectAndName(lineSplitted[1].toUpperCase(), lineSplitted[2]);
        if (account == null) {
        	System.out.println("No entry found");
        } else {
            System.out.println(account.toStringDetailed());
        }
        return account;
    }
    
    private static void doUpdate(String[] lineSplitted) {
    	if (lineSplitted.length != 3) {
            usage();
            return;
        }
    	Account a = doGet(lineSplitted);
    	boolean updateNeeded = editAccount(a);
        if (updateNeeded) {
        	AccountsClient client = new AccountsClientImpl(REST_URL);
        	client.updateAccount(a);
        	System.out.println("Sucessfully updated");
        }
    }
    
    private static boolean editAccount(Account a) {
    	boolean updateNeeded = false;
    	try {
    		System.out.println("Editing:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("- URL: ");
            String line = reader.readLine();
            if (line.trim().length() > 0) {
            	a.setUrl(line.trim());
            	updateNeeded = true;
            }
            System.out.print("- Username: ");
            line = reader.readLine();
            if (line.trim().length() > 0) {
            	a.setUsername(line.trim());
            	updateNeeded = true;
            }
            System.out.print("- Password: ");
            line = reader.readLine();
            if (line.trim().length() > 0) {
            	a.setPassword(line.trim());
            	updateNeeded = true;
            }
            System.out.print("- Confirm password: ");
            line = reader.readLine();
            if (line.trim().length() > 0) {
            	a.setPassword2(line.trim());
            	updateNeeded = true;
            }
            System.out.print("- Tag: ");
            line = reader.readLine();
            if (line.trim().length() > 0) {
            	a.setTag(line.trim());
            	updateNeeded = true;
            }
        } catch(IOException ioex) {
        	return false;
        }	
        return updateNeeded;
    }
    private static void doDelete(String[] lineSplitted) {
    }

    public static void main(String[] args) {
    	if (args.length == 1) {
    		REST_URL = args[0];
    	}
    	System.out.println("Rest service will be called at: " + REST_URL);
    	
        boolean b = authenticate();
        if (!b) {
            System.out.println("Wrong password! Bye!");
            System.exit(0);
        }
        usage();
        System.out.print(PROMPT);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = "";
            String lineSplitted[] = null;
            while ((line=reader.readLine()) != null) {
                lineSplitted = line.split(" ");
                if (lineSplitted[0].equals("initdb")) {
                   initDB();
                }
                else if (lineSplitted[0].equals("create")) {
                    doCreate(lineSplitted);
                }
                else if (lineSplitted[0].equals("list")) {
                    doList(lineSplitted);
                }
                else if (lineSplitted[0].equals("get")) {
                    doGet(lineSplitted);
                }
                else if (lineSplitted[0].equals("update")) {
                    doUpdate(lineSplitted);
                }
                else if (lineSplitted[0].equals("delete")) {
                    doDelete(lineSplitted);
                }
                else if (lineSplitted[0].equals("exit")) {
                    System.out.println("Bye!");
                    System.exit(0);
                }
                else {
                    usage();
                }
                System.out.print(PROMPT);
            } 
        } catch(IOException ioex) {
        }
    }
}
