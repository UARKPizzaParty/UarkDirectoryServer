package edu.uark.csce.pizzaParty.uarkDirectoryServer.repository;

import edu.uark.csce.pizzaParty.uarkDirectoryServer.model.Person;
import edu.uark.csce.pizzaParty.uarkDirectoryServer.util.SshUtil;

import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("/person")
public class PersonRepository extends BaseRepository
{
	public PersonRepository() throws NamingException
	{
		super();
	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postPerson(Person person) throws NamingException
	{
		startTransaction();
		person.setCreateDate(new Date());

		Response response = super.postOne(person);
		commitTransaction();
		return response;
	}

	@GET
	@Path("/get/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getPerson(@PathParam("username") String username) throws NamingException
	{
		startTransaction();
		Person person = (Person) getOne("username", username, "selectPerson");
		commitTransaction();
		return person;
	}

	@GET
	@Path("/isValid/{username}/{password}")
	@Produces(MediaType.TEXT_PLAIN)
	public String isValidPerson(@PathParam("username") String username, @PathParam("password") String password)
	{
		return SshUtil.isValidLogin(username, password).toString();
	}

}
