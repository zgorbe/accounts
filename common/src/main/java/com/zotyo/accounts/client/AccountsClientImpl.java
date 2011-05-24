package com.zotyo.accounts.client;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.zotyo.accounts.model.Account;

public class AccountsClientImpl implements AccountsClient {

	private String url;
    private UsernamePasswordCredentials credentials;
    
	public AccountsClientImpl(String url) {
		this.url = url;
		
		credentials = new UsernamePasswordCredentials("admin", "t0mc4tw1ns");
	}
	public String createAccount(Account a) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost postMethod = new HttpPost(url);
		postMethod.addHeader(BasicScheme.authenticate(credentials,"US-ASCII",false));
		try {
			StringWriter sw = new StringWriter();
			JAXBContext jaxbContext = JAXBContext.newInstance(Account.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.marshal(a, sw); 
			StringEntity stringEntity = new StringEntity(sw.toString());
			stringEntity.setContentType("application/xml");
			postMethod.setEntity(stringEntity);
			HttpResponse response = httpclient.execute(postMethod);
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity);
		} catch (Exception e) {
			Logger.getLogger(AccountsClientImpl.class.getName()).log(Level.SEVERE, null, e);
			return "Failed to create account";
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

	public String createAccountForm(Account a) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost postMethod = new HttpPost(url);
		postMethod.addHeader(BasicScheme.authenticate(credentials,"US-ASCII",false));
		try {
			//project, entryname, url, username, password, password2, tag
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			formparams.add(new BasicNameValuePair("project", a.getProject()));
			formparams.add(new BasicNameValuePair("entryname", a.getEntryname()));
			formparams.add(new BasicNameValuePair("url", a.getUrl()));
			formparams.add(new BasicNameValuePair("username", a.getUsername()));
			formparams.add(new BasicNameValuePair("password", a.getPassword()));
			formparams.add(new BasicNameValuePair("password2", a.getPassword2()));
			formparams.add(new BasicNameValuePair("tag", a.getTag()));
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
			postMethod.setEntity(entity);
			HttpResponse response = httpclient.execute(postMethod);
			HttpEntity respEntity = response.getEntity();
			return EntityUtils.toString(respEntity);
		} catch (Exception e) {
			Logger.getLogger(AccountsClientImpl.class.getName()).log(Level.SEVERE, null, e);
			return "Failed to create account";
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}
	
	public boolean deleteAccount(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

    public Account updateAccount(Account a) {
    	Account account = null;
    	HttpClient httpclient = new DefaultHttpClient();
    	HttpPut putMethod = new HttpPut(url);
    	putMethod.addHeader(BasicScheme.authenticate(credentials,"US-ASCII",false));
		try {
			StringWriter sw = new StringWriter();
			JAXBContext jaxbContext = JAXBContext.newInstance(Account.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.marshal(a, sw); 
			StringEntity stringEntity = new StringEntity(sw.toString());
			stringEntity.setContentType("application/xml");
			putMethod.setEntity(stringEntity);
			HttpResponse response = httpclient.execute(putMethod);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				StringReader reader = new StringReader(EntityUtils.toString(entity));
				account = (Account)unmarshaller.unmarshal(reader);
			}
		} catch (Exception e) {
			Logger.getLogger(AccountsClientImpl.class.getName()).log(Level.SEVERE, null, e);
			return a;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return (account != null) ? account : a;
	}

	public Account getAccountByProjectAndName(String project, String name) {
		Account account = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet getMethod = new HttpGet(url + "/" + project + "/" + name);
		getMethod.addHeader(BasicScheme.authenticate(credentials,"US-ASCII",false));
		try {
			HttpResponse response = httpclient.execute(getMethod);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				JAXBContext jaxbContext = JAXBContext.newInstance(Account.class);
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				StringReader reader = new StringReader(EntityUtils.toString(entity));
				account = (Account)unmarshaller.unmarshal(reader);
			}
		} catch (Exception e) {
			Logger.getLogger(AccountsClientImpl.class.getName()).log(Level.SEVERE, null, e); 
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return account;
	}

	public List<Account> getAccounts() {
		HttpGet getMethod = new HttpGet(url);
		getMethod.addHeader(BasicScheme.authenticate(credentials,"US-ASCII",false));
		return invokeAccountsGetMethod(getMethod);
	}

	public List<Account> getAccountsByProject(String project) {
		HttpGet getMethod = new HttpGet(url + "?project=" + project);
		getMethod.addHeader(BasicScheme.authenticate(credentials,"US-ASCII",false));
		return invokeAccountsGetMethod(getMethod);
	}

	private List<Account> invokeAccountsGetMethod(HttpGet getMethod) {
		List<Account> accounts = null;
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpResponse response = httpclient.execute(getMethod);
			HttpEntity entity = response.getEntity();
			JAXBContext jaxbContext = JAXBContext.newInstance(AccountsList.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(EntityUtils.toString(entity));
			AccountsList list = (AccountsList)unmarshaller.unmarshal(reader);
			accounts = list.getAccounts();
		} catch (Exception e) {
			Logger.getLogger(AccountsClientImpl.class.getName()).log(Level.SEVERE, null, e); 
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return accounts;
		
	}
    
    public String getAccountsXML() {
        HttpGet getMethod = new HttpGet(url);
        getMethod.addHeader(BasicScheme.authenticate(credentials,"US-ASCII",false));
        return invokeAccountsGetMethodXML(getMethod);
    }
    
    public String getAccountsByProjectXML(String project) {
        HttpGet getMethod = new HttpGet(url + "?project=" + project);
        getMethod.addHeader(BasicScheme.authenticate(credentials,"US-ASCII",false));
        return invokeAccountsGetMethodXML(getMethod);    
    }

    private String invokeAccountsGetMethodXML(HttpGet getMethod) {
        String accounts = null;
        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpResponse response = httpclient.execute(getMethod);
            HttpEntity entity = response.getEntity();
            accounts = EntityUtils.toString(entity);
        } catch (Exception e) {
            Logger.getLogger(AccountsClientImpl.class.getName()).log(Level.SEVERE, null, e); 
        } finally {
            httpclient.getConnectionManager().shutdown();
        }

        return accounts;
    }
    
    public String getProjectNamesXML() {
        HttpGet getMethod = new HttpGet(url);
        getMethod.addHeader(BasicScheme.authenticate(credentials,"US-ASCII",false));
        
        String projectNamesXML = null;
        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpResponse response = httpclient.execute(getMethod);
            HttpEntity entity = response.getEntity();
            projectNamesXML = EntityUtils.toString(entity);
        } catch (Exception e) {
            Logger.getLogger(AccountsClientImpl.class.getName()).log(Level.SEVERE, null, e); 
        } finally {
            httpclient.getConnectionManager().shutdown();
        }

        return projectNamesXML;
    }
}
