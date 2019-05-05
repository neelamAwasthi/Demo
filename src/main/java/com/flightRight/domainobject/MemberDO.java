package com.flightRight.domainobject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(
    name = "member",
    uniqueConstraints = @UniqueConstraint(name = "uc_firstName", columnNames = {"firstName"})
)
public class MemberDO
{

    @Id
    @GeneratedValue
    private Long id;
    
    @NotBlank(message = "FirstName is mandatory")
    private String firstName;
    
    private String lastName;    
    
    private String dateOfBirth;
    
  
    private String postalCode; 
    @OneToOne(targetEntity=FileDO.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private FileDO files;
    
    
    public MemberDO()
    {
    }

	/**
	 * @param firstName
	 * @param lastName
	 * @param dateOfBirth
	 * @param postalCode
	 * @param files
	 */
	public MemberDO(String firstName, String lastName, String dateOfBirth, String postalCode, FileDO files) {
		//this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.postalCode = postalCode;
		this.files = files;
	}
	
	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param dateOfBirth
	 * @param postalCode
	 * @param files
	 */
	public MemberDO(long id, String firstName, String lastName, String dateOfBirth, String postalCode, FileDO files) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.postalCode = postalCode;
		this.files = files;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the image
	 */
	public FileDO getImage() {
		return files;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(FileDO image) {
		this.files = image;
	}
    
}
