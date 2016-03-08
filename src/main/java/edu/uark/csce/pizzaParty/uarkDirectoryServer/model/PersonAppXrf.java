package edu.uark.csce.pizzaParty.uarkDirectoryServer.model;

import org.apache.openjpa.persistence.ExternalValues;
import org.apache.openjpa.persistence.Type;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "PERSON_APP_XRF", schema = "UARK")
@NamedQueries({
		@NamedQuery(name = "getAllApps", query = "select pax.app from PersonAppXrf pax where pax.person.username = :username"),
		@NamedQuery(name = "getOwnerApps", query = "select pax.app from PersonAppXrf pax where pax.person.username = :username and pax.isOwner = true")})
public class PersonAppXrf extends BaseModel
{
	private Integer id;
	private Person person;
	private App app;
	private Boolean isOwner;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	public Integer getId()
	{
		return id;
	}

	public PersonAppXrf setId(Integer id)
	{
		this.id = id;
		return this;
	}

	@ManyToOne(targetEntity = Person.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON", referencedColumnName = "ID", nullable = false)
	public Person getPerson()
	{
		return person;
	}

	public void setPerson(Person person)
	{
		this.person = person;
	}

	@ManyToOne(targetEntity = App.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "APP", referencedColumnName = "ID", nullable = false)
	public App getApp()
	{
		return app;
	}

	public void setApp(App app)
	{
		this.app = app;
	}

	@Basic
	@ExternalValues({"true=1", "false=0"})
	@Type(Integer.class)
	@Column(name = "IS_OWNER", nullable = false, columnDefinition = "TINYINT", length = 1)
	public Boolean getIsOwner()
	{
		return isOwner;
	}

	public void setIsOwner(Boolean isOwner)
	{
		this.isOwner = isOwner;
	}

	@Override
	public String toString()
	{
		return "PersonAppXrf{" +
				"id=" + getId() +
				", person=" + getPerson() +
				", app=" + getApp() +
				", isOwner=" + getIsOwner() +
				'}';
	}
}
