package com.saviynt.custom.model;

import java.util.Map;

/**
 * The Class Attributes.
 */
public class Attributes {
	
	/** The attributegroupname. */
	private String attributegroupname;
	
	/** The value. */
	private String value;
	
	/** The attributes. */
	private String attributes;
	
	/** The attributevalues. */
	private Map<String, Object> attributevalues;
	
	/**
	 * Gets the attributegroupname.
	 *
	 * @return the attributegroupname
	 */
	public String getAttributegroupname() {
		return attributegroupname;
	}
	
	/**
	 * Sets the attributegroupname.
	 *
	 * @param attributegroupname the new attributegroupname
	 */
	public void setAttributegroupname(String attributegroupname) {
		this.attributegroupname = attributegroupname;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	public String getAttributes() {
		return attributes;
	}
	
	/**
	 * Sets the attributes.
	 *
	 * @param attributes the new attributes
	 */
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	
	/**
	 * Gets the attributevalues.
	 *
	 * @return the attributevalues
	 */
	public Map<String, Object> getAttributevalues() {
		return attributevalues;
	}
	
	/**
	 * Sets the attributevalue.
	 *
	 * @param attributevalues the attributevalues
	 */
	public void setAttributevalue(Map<String, Object> attributevalues) {
		this.attributevalues = attributevalues;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Attributes [attributegroupname=" + attributegroupname + ", value=" + value + ", attributes="
				+ attributes + ", attributevalue=" + attributevalues + ", getAttributegroupname()="
				+ getAttributegroupname() + ", getValue()=" + getValue() + ", getAttributes()=" + getAttributes()
				+ ", getAttributevalue()=" + getAttributevalues() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	

}
