/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zotyo.accounts.web;

import com.zotyo.accounts.service.AccountService;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Zoli
 */
@Path("/projects")
@Component
public class ProjectResource {
    @Autowired
    private AccountService accountService;

    @GET
    @Produces("application/xml")
    public List<String> getProjectsXML() {
        List<String> projects = accountService.getProjectNames();
        return projects;
    }
}