package com.zotyo.accounts.client;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="projects")
public class ProjectNamesList {
	
	private List<String> projects;

	@XmlElement(name="project")
	public List<String> getProjects() {
		return projects;
	}

	public void setProjects(List<String> projects) {
	    this.projects = projects;
	}
}
