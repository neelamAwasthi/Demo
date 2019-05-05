package com.flightRight.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.flightRight.domainobject.FileDO;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDTO implements Serializable {

	private Long id;

	private String firstName;

	private String lastName;

	private String dateOfBirth;

	private String postalCode;

	private FileDO image;
	
	private String imageUrl;

	private MemberDTO() {
	}

	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param dateOfBirth
	 * @param postalCode
	 * @param image
	 */
	public MemberDTO(Long id, String firstName, String lastName, String dateOfBirth, String postalCode, FileDO image, String imageUrl) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.postalCode = postalCode;
		this.image = image;
		this.imageUrl = imageUrl;
	}

	public static MemberDTOBuilder newBuilder() {
		return new MemberDTOBuilder();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @param firstName
	 *            the firstName to set
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
	 * @param lastName
	 *            the lastName to set
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
	 * @param dateOfBirth
	 *            the dateOfBirth to set
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
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the image
	 */
	public FileDO getImage() {
		return image;
	}

	/**
	 * @param bFile
	 *            the image to set
	 */
	public void setImage(FileDO bFile) {
		this.image = bFile;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public static class MemberDTOBuilder {
		private Long id;
		private String firstName;
		private String lastName;
		private String dateOfBirth;
		private String postalCode;
		private FileDO image;
		private String imageUrl;
		
		public MemberDTOBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public MemberDTOBuilder setImage(FileDO image, String imageUrl) {
			this.image = image;
			this.imageUrl = imageUrl;
			return this;
		}

		/**
		 * @param licencePlate
		 *            the firstName to set
		 */
		public MemberDTOBuilder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		/**
		 * @param lastName
		 *            the lastName to set
		 */
		public MemberDTOBuilder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		/**
		 * @param dateOfBirth
		 *            the dateOfBirth to set
		 */
		public MemberDTOBuilder setDateOfBirth(String dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
			return this;
		}

		/**
		 * @param postalCode
		 *            the postalCode to set
		 */
		public MemberDTOBuilder setPostalCode(String postalCode) {
			this.postalCode = postalCode;
			return this;
		}
		
		/**
		 * @param dateOfBirth
		 *            the dateOfBirth to set
		 */
		public MemberDTOBuilder setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
			return this;
		}


		public MemberDTO createMemberDTO() {
			return new MemberDTO(id, firstName, lastName, dateOfBirth, postalCode, image, imageUrl);
		}

	}
}
