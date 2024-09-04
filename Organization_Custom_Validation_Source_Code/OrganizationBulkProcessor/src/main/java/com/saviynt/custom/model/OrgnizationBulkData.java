package com.saviynt.custom.model;

import java.util.List;

/**
 * The Class OrgnizationBulkData.
 */
public class OrgnizationBulkData {
	
	/** The customer. */
	private List<Customer> CUSTOMER;
	
	/** The owners. */
	private List<Customer_Owners> OWNERS;
	
	/** The endpoints. */
	private List<Customer_Endpoints> ENDPOINTS;
	
	/** The roles. */
	private List<Customer_Roles> ROLES;
	
	/** The entitlements. */
	private List<Customer_Entitlements> ENTITLEMENTS;
	
	/** The attributes. */
	private List<Customer_Attributes> ATTRIBUTES;
	
	/** The source. */
	private String source;
	
	/** The sourceaction. */
	private String sourceaction;
	
	/**
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	public List<Customer> getCUSTOMER() {
		return CUSTOMER;
	}
	
	/**
	 * Sets the customer.
	 *
	 * @param cUSTOMER the new customer
	 */
	public void setCUSTOMER(List<Customer> cUSTOMER) {
		CUSTOMER = cUSTOMER;
	}
	
	/**
	 * Gets the owners.
	 *
	 * @return the owners
	 */
	public List<Customer_Owners> getOWNERS() {
		return OWNERS;
	}
	
	/**
	 * Sets the owners.
	 *
	 * @param oWNERS the new owners
	 */
	public void setOWNERS(List<Customer_Owners> oWNERS) {
		OWNERS = oWNERS;
	}
	
	/**
	 * Gets the endpoints.
	 *
	 * @return the endpoints
	 */
	public List<Customer_Endpoints> getENDPOINTS() {
		return ENDPOINTS;
	}
	
	/**
	 * Sets the endpoints.
	 *
	 * @param eNDPOINTS the new endpoints
	 */
	public void setENDPOINTS(List<Customer_Endpoints> eNDPOINTS) {
		ENDPOINTS = eNDPOINTS;
	}
	
	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	public List<Customer_Roles> getROLES() {
		return ROLES;
	}
	
	/**
	 * Sets the roles.
	 *
	 * @param rOLES the new roles
	 */
	public void setROLES(List<Customer_Roles> rOLES) {
		ROLES = rOLES;
	}
	
	/**
	 * Gets the entitlements.
	 *
	 * @return the entitlements
	 */
	public List<Customer_Entitlements> getENTITLEMENTS() {
		return ENTITLEMENTS;
	}
	
	/**
	 * Sets the entitlements.
	 *
	 * @param eNTITLEMENTS the new entitlements
	 */
	public void setENTITLEMENTS(List<Customer_Entitlements> eNTITLEMENTS) {
		ENTITLEMENTS = eNTITLEMENTS;
	}
	
	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	public List<Customer_Attributes> getATTRIBUTES() {
		return ATTRIBUTES;
	}
	
	/**
	 * Sets the attributes.
	 *
	 * @param aTTRIBUTES the new attributes
	 */
	public void setATTRIBUTES(List<Customer_Attributes> aTTRIBUTES) {
		ATTRIBUTES = aTTRIBUTES;
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
	
	
	

}
