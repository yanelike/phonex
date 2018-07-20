package com.administration.phones.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;


@Document(collection = "Phones")
public class PhoneBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private ObjectId id;
	
	@NotNull(message = "brand is required")
	@Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "brand: No special characters")
	public String brand;
	
	@NotNull(message = "model is required")
	@Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "model: No special characters")
	public String model;
	
	@NotNull(message = "shortName is required")
	@Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "shortName: No special characters")
	public String shortName;
	
	@NotNull(message = "dateCreated is required")
	@DateTimeFormat(pattern="dd/MM/YY")
	public Date dateCreated;
	
	@NotNull(message = "imei is required")
	@Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "imei: No special characters")
	public String imei;
	
	public String cellNumber;
	
	@Email(message = "emailSupport invalid")
	public String emailSupport;
	
	public boolean hasIos;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}

	public String getEmailSupport() {
		return emailSupport;
	}

	public void setEmailSupport(String emailSupport) {
		this.emailSupport = emailSupport;
	}

	public boolean isHasIos() {
		return hasIos;
	}

	public void setHasIos(boolean hasIos) {
		this.hasIos = hasIos;
	}

	@Override
	public String toString() {
		return "PhoneBean [id=" + id + ", brand=" + brand + ", model=" + model + ", shortName=" + shortName
				+ ", dateCreated=" + dateCreated + ", imei=" + imei + ", cellNumber=" + cellNumber + ", emailSupport="
				+ emailSupport + ", hasIos=" + hasIos + "]";
	}
	
	
}
