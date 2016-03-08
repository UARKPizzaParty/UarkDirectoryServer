package edu.uark.csce.pizzaParty.uarkDirectoryServer.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@XmlRootElement
@Table(name = "APP", schema = "UARK")
@NamedQuery(name = "selectApp", query = "select App from App app where app.title = :title")
public class App extends BaseModel
{
	private Integer id;
	private String title;
	private String publisher;
	private Float version;
	private String description;
	private Date createDate;
	private Set<PersonAppXrf> personAppXrfSet = new HashSet<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	@Basic
	@Column(name = "TITLE", nullable = false, length = 20)
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	@Basic
	@Column(name = "PUBLISHER", nullable = true, length = 20)
	public String getPublisher()
	{
		return publisher;
	}

	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}

	@Basic
	@Column(name = "VERSION", nullable = false)
	public Float getVersion()
	{
		return version;
	}

	public void setVersion(Float version)
	{
		this.version = version;
	}

	@Basic
	@Column(name = "DESCRIPTION", nullable = true, length = 200)
	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@Basic
	@Column(name = "CREATE_DATE", nullable = false)
	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	@OneToMany(mappedBy = "app", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<PersonAppXrf> getPersonAppXrfSet()
	{
		return personAppXrfSet;
	}

	public App setPersonAppXrfSet(Set<PersonAppXrf> personAppXrfSet)
	{
		this.personAppXrfSet = personAppXrfSet;
		return this;
	}
}
