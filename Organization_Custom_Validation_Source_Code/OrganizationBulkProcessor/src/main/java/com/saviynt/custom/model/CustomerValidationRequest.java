package com.saviynt.custom.model;

import java.util.List;

/**
 * The Class CustomerValidationRequest.
 */
public class CustomerValidationRequest {
	
	/** The tab. */
	private String tab;
	
	/** The source. */
	private String source;
	
	/** The sourceaction. */
	private String sourceaction;
	
	/** The details. */
	private Customer details;
	
	/** The endpoints. */
	private List<Endpoints> endpoints;
	
	/** The roles. */
	private List<Roles> roles;
	
	/** The owners. */
	private List<Owners> owners;
	
	/** The entitlements. */
	private List<Entitlements> entitlements;
	
	/** The attributes. */
	private List<Attributes> attributes;
	
	/** The error message. */
	private String errorMessage;
	
	/**
	 * Gets the tab.
	 *
	 * @return the tab
	 */
	public String getTab() {
		return tab;
	}
	
	/**
	 * Sets the tab.
	 *
	 * @param tab the new tab
	 */
	public void setTab(String tab) {
		this.tab = tab;
	}
	
	/**
	 * Gets the source.
	 *
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	
	/**
	 * Sets the source.
	 *
	 * @param source the new source
	 */
	public void setSource(String source) {
		this.source = source;
	}
	
	/**
	 * Gets the sourceaction.
	 *
	 * @return the sourceaction
	 */
	public String getSourceaction() {
		return sourceaction;
	}
	
	/**
	 * Sets the sourceaction.
	 *
	 * @param sourceaction the new sourceaction
	 */
	public void setSourceaction(String sourceaction) {
		this.sourceaction = sourceaction;
	}
	
	/**
	 * Gets the details.
	 *
	 * @return the details
	 */
	public Customer getDetails() {
		return details;
	}
	
	/**
	 * Sets the details.
	 *
	 * @param details the new details
	 */
	public void setDetails(Customer details) {
		this.details = details;
	}
	
	/**
	 * Gets the endpoints.
	 *
	 * @return the endpoints
	 */
	public List<Endpoints> getEndpoints() {
		return endpoints;
	}
	
	/**
	 * Sets the endpoints.
	 *
	 * @param endpoints the new endpoints
	 */
	public void setEndpoints(List<Endpoints> endpoints) {
		this.endpoints = endpoints;
	}
	
	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	public List<Roles> getRoles() {
		return roles;
	}
	
	/**
	 * Sets the roles.
	 *
	 * @param roles the new roles
	 */
	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}
	
	/**
	 * Gets the owners.
	 *
	 * @return the owners
	 */
	public List<Owners> getOwners() {
		return owners;
	}
	
	/**
	 * Sets the owners.
	 *
	 * @param owners the new owners
	 */
	public void setOwners(List<Owners> owners) {
		this.owners = owners;
	}
	
	/**
	 * Gets the entitlements.
	 *
	 * @return the entitlements
	 */
	public List<Entitlements> getEntitlements() {
		return entitlements;
	}
	
	/**
	 * Sets the entitlements.
	 *
	 * @param entitlements the new entitlements
	 */
	public void setEntitlements(List<Entitlements> entitlements) {
		this.entitlements = entitlements;
	}
	
	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	public List<Attributes> getAttributes() {
		return attributes;
	}
	
	/**
	 * Sets the attributes.
	 *
	 * @param attributes the new attributes
	 */
	public void setAttributes(List<Attributes> attributes) {
		this.attributes = attributes;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "CustomerValidationRequest [tab=" + tab + ", source=" + source + ", sourceaction=" + sourceaction
				+ ", details=" + details + ", endpoints=" + endpoints + ", roles=" + roles + ", owners=" + owners
				+ ", entitlements=" + entitlements + ", attributes=" + attributes + ", getTab()=" + getTab()
				+ ", getSource()=" + getSource() + ", getSourceaction()=" + getSourceaction() + ", getDetails()="
				+ getDetails() + ", getEndpoints()=" + getEndpoints() + ", getRoles()=" + getRoles() + ", getOwners()="
				+ getOwners() + ", getEntitlements()=" + getEntitlements() + ", getAttributes()=" + getAttributes()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	/**
	 * Sets the error message.
	 *
	 * @param errorMessage the new error message
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	

}
