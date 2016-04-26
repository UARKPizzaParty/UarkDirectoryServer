package edu.uark.csce.pizzaParty.uarkDirectoryServer;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@XmlRootElement
@Table(name = "APP", schema = "UARK")
@NamedQuery(name = "selectApp", query = "select App from App app where app.name = :name")
public class App
{
	private Integer id;
	private String name;
	private String developer;
	private String version;
	private String description;
	private Date createDate;
	private Integer numImages;
	private String apkName;

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
	@Column(name = "NAME", nullable = false, length = 20)
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Basic
	@Column(name = "DEVELOPER", nullable = false, length = 20)
	public String getDeveloper()
	{
		return developer;
	}

	public void setDeveloper(String publisher)
	{
		this.developer = publisher;
	}

	@Basic
	@Column(name = "VERSION", nullable = false, length = 20)
	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
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

	@Basic
	@Column(name = "NUM_IMAGES", nullable = false)
	public Integer getNumImages()
	{
		return numImages;
	}

	public void setNumImages(Integer numImages)
	{
		this.numImages = numImages;
	}

	@Basic
	@Column(name = "APK_NAME", nullable = false, length = 200)
	public String getApkName()
	{
		return apkName;
	}

	public void setApkName(String apkName)
	{
		this.apkName = apkName;
	}
}
