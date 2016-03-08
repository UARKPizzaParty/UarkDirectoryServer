package edu.uark.csce.pizzaParty.uarkDirectoryServer.repository;

import edu.uark.csce.pizzaParty.uarkDirectoryServer.model.BaseModel;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.core.Response;
import java.util.List;

public abstract class BaseRepository
{
	protected EntityManager entityManager;

	public BaseRepository() throws NamingException
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

	protected Response postOne(BaseModel baseModel) throws NamingException
	{
		entityManager.persist(baseModel);
		return Response.status(Response.Status.CREATED).entity(baseModel).build();
	}

	protected BaseModel getOne(String paramName, String paramValue, String queryName) throws NamingException
	{
		Query query = entityManager.createNamedQuery(queryName);
		query.setParameter(paramName, paramValue);
		List resultList = query.getResultList();
		if (resultList.size() > 0)
		{
			return (BaseModel) resultList.get(0);
		}
		else
		{
			return null;
		}
	}

	protected List getList(String paramName, String paramValue, String queryName) throws NamingException
	{
		Query query = entityManager.createNamedQuery(queryName);
		query.setParameter(paramName, paramValue);
		return query.getResultList();
	}
}
