package com;

import model.Project;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.parser.*;

import com.google.gson.*;



@Path("/Projects")
public class ProjectService {

	Project projectObj = new Project();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProject() {
		return projectObj.readProject();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProject(@FormParam("projectID") String projectID, @FormParam("projectTitle") String projectTitle,
			@FormParam("projectDescription") String projectDescription, @FormParam("projectProposalLink") String projectProposalLink,
			@FormParam("projectVideoLink") String projectVideoLink) {

		String output = projectObj.insertProject(projectTitle, projectDescription, projectProposalLink, projectVideoLink);
		return output;
	}

	/*@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProject(String projectData) {

		// Convert the input string to a JSON object

		JsonObject projectObject = new JsonParser().parse(projectData).getAsJsonObject();

		// Read the values from the JSON object

		String pID = projectObject.get("projectID").getAsString();
		String pTitle = projectObject.get("projectTitle").getAsString();
		String pDescription = projectObject.get("projectDescription").getAsString();
		String pProposalLink = projectObject.get("projectProposalLink").getAsString();
		String pVideoLink = projectObject.get("projectVideoLink").getAsString();

		String output = projectObj.updateProject(pID, pTitle, pDescription, pProposalLink, pVideoLink);

		return output;
	}*/
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProject(String projectData) {
		// Convert the input string to a JSON object
		JsonObject projectObject = new JsonParser().parse(projectData).getAsJsonObject();
		// Read the values from the JSON object
		String projectID = projectObject.get("projectID").getAsString();
		String projectTitle = projectObject.get("projectTitle").getAsString();
		
		
		
		String projectDesc = projectObject.get("projectDescription").getAsString();
		
		
		
		String projectLink = projectObject.get("projectProposalLink").getAsString();
		String projectVideo = projectObject.get("projectVideoLink").getAsString();
		String output = projectObj.updateProject(projectID, projectTitle, projectDesc, projectLink, projectVideo);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProject(String projectData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(projectData, "", Parser.xmlParser());
		// Read the value from the element <projectID>
		String projectID = doc.select("projectID").text();
		String output = projectObj.deleteProject(projectID);
		return output;
	}

}
