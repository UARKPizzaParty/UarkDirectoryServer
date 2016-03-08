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
	@Column(name = "TITLE", nullable = false, length = 50)
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
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

	@Override
	public String toString()
	{
		return "App{" +
				"id=" + getId() +
				", title='" + getTitle() + '\'' +
				", createDate=" + getCreateDate() +
				'}';
	}
}
