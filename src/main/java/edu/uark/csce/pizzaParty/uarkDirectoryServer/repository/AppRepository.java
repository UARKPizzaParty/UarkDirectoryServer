package edu.uark.csce.pizzaParty.uarkDirectoryServer.repository;

import edu.uark.csce.pizzaParty.uarkDirectoryServer.model.App;
import edu.uark.csce.pizzaParty.uarkDirectoryServer.model.Person;
import edu.uark.csce.pizzaParty.uarkDirectoryServer.model.PersonAppXrf;

import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Path("/app")
public class AppRepository extends BaseRepository
{
	public AppRepository() throws NamingException
	{
		super();
	}

	@POST
	@Path("/post/{username}/{owner}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postApp(App app, @PathParam("username") String username, @PathParam("owner") Boolean owner) throws NamingException
	{
		startTransaction();
		app.setCreateDate(new Date());
		Person person = (Person) getOne("username", username, "selectPerson");

		PersonAppXrf personAppXrf = new PersonAppXrf();
		personAppXrf.setApp(app);
		personAppXrf.setPerson(person);
		personAppXrf.setIsOwner(owner);

		postOne(personAppXrf);
		Response response = postOne(app);
		commitTransaction();
		return response;
	}

	@GET
	@Path("/get/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public App getApp(@PathParam("title") String title) throws NamingException
	{
		return (App) getOne("title", title, "selectApp");
	}

	@GET
	@Path("/getUserApps/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public List getAllAppsForUser(@PathParam("username") String username) throws NamingException
	{
		return getList("username", username, "getAllApps");
	}

	@GET
	@Path("/getOwnerApps/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public List getAppsOwnedByUser(@PathParam("username") String username) throws NamingException
	{
		return getList("username", username, "getOwnerApps");
	}

	@GET
	@Path("/getAllApps")
	@Produces(MediaType.APPLICATION_JSON)
	public List<App> getAllApps() throws NamingException
	{
		return entityManager.createQuery("SELECT a FROM App a", App.class).getResultList();
	}

}
