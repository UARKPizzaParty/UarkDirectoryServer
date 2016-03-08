package edu.uark.csce.pizzaParty.uarkDirectoryServer.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@XmlRootElement
@Table(name = "PERSON", schema = "UARK")
@NamedQuery(name = "selectPerson", query = "select Person from Person person where person.username = :username")
public class Person extends BaseModel
{
	private Integer id;
	private String username;
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
	@Column(name = "USERNAME", nullable = false, length = 20)
	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
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

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<PersonAppXrf> getPersonAppXrfSet()
	{
		return personAppXrfSet;
	}

	public void setPersonAppXrfSet(Set<PersonAppXrf> personAppXrfSet)
	{
		this.personAppXrfSet = personAppXrfSet;
	}
}
