package edu.uark.csce.pizzaParty.uarkDirectoryServer;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/app")
public class AppRepository
{
	protected EntityManager entityManager;

	public AppRepository() throws NamingException
	{
		this.entityManager = getEntityManager();
	}

	protected EntityManager getEntityManager() throws NamingException
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UARKPersistenceUnit");
		return emf.createEntityManager();
	}

	protected void startTransaction()
	{
		entityManager.getTransaction().begin();
	}

	protected void commitTransaction()
	{
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@POST
	@Path("/postApp")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postApp(App app) throws NamingException
	{
		startTransaction();
		entityManager.persist(app);
		Response response = Response.status(Response.Status.CREATED).entity(app).build();
		commitTransaction();
		return response;
	}

	@GET
	@Path("/getApp/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public App getApp(@PathParam("name") String name) throws NamingException
	{
		Query query = entityManager.createNamedQuery("selectApp");
		query.setParameter("name", name);
		List resultList = query.getResultList();
		if (resultList.size() > 0)
		{
			return (App) resultList.get(0);
		}
		else
		{
			return null;
		}
	}

	@GET
	@Path("/getAllApps")
	@Produces(MediaType.APPLICATION_JSON)
	public List<App> getAllApps() throws NamingException
	{
		return entityManager.createQuery("SELECT a FROM App a", App.class).getResultList();
	}
}
