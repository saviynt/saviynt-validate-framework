package com.saviynt.custom.OrganizationBulkProcessor;

import java.util.List;

import com.saviynt.custom.model.Customer_Attributes;
import com.saviynt.custom.model.Customer_Endpoints;
import com.saviynt.custom.model.Customer_Entitlements;
import com.saviynt.custom.model.Customer_Owners;
import com.saviynt.custom.model.Customer_Roles;

/**
 * The Class OrgBulkValidator.
 */
public class OrgBulkValidator {

	/**
	 * Validate owners.
	 *
	 * @param ownerList the owner list
	 */
	public void validateOwners(List<Customer_Owners> ownerList) {
		if(ownerList!=null && ownerList.size()> 0) {
			for (Customer_Owners customer_Owners : ownerList) {
				System.out.println("Customer Owners : "+customer_Owners.getOwners());
				/** Set Policy as default 1 **/
				customer_Owners.setPolicy("SUPERADMIN"); 
			}
		}
	}

	/**
	 * Validate endpoints.
	 *
	 * @param endpointsList the endpoints list
	 */
	public void validateEndpoints(List<Customer_Endpoints> endpointsList) {
		if(endpointsList!=null && endpointsList.size()> 0) {
			for (Customer_Endpoints customerEndpoint : endpointsList) {
				try {
					System.out.println("Customer Endpoints : "+customerEndpoint.getEndpoint());
					customerEndpoint.setCustomproperty1(customerEndpoint.getCustomproperty1() +"_jar");
					customerEndpoint.setCustomproperty2(customerEndpoint.getCustomproperty2() +"_jar");
					customerEndpoint.setCustomproperty3(customerEndpoint.getCustomproperty3() +"_jar");
					customerEndpoint.setCustomproperty4(customerEndpoint.getCustomproperty4() +"_jar");
					customerEndpoint.setCustomproperty5(customerEndpoint.getCustomproperty5() +"_jar");
				} catch (Exception ex) {
					System.out.println("Error :" + ex.getMessage());
				}
			}
		}
	}

	/**
	 * Validate roles.
	 *
	 * @param rolesList the roles list
	 */
	public void validateRoles(List<Customer_Roles> rolesList) {
		if(rolesList!=null && rolesList.size()> 0) {
			for (Customer_Roles customerRoles : rolesList) {
				try {
					System.out.println("Customer Roles : "+customerRoles.getRoles());
					
				} catch (Exception ex) {
					System.out.println("Error :" + ex.getMessage());
				}
			}
		}
	}

	/**
	 * Validate entitlements.
	 *
	 * @param entList the ent list
	 */
	public void validateEntitlements(List<Customer_Entitlements> entList) {
		if(entList!=null && entList.size()> 0) {
			for (Customer_Entitlements custEnt : entList) {
				try {
					System.out.println("Customer Ent : "+custEnt.getEntitlement_value());
					
				} catch (Exception ex) {
					System.out.println("Error :" + ex.getMessage());
				}
			}
		}
	}

	/**
	 * Validate attributes.
	 *
	 * @param attributesList the attributes list
	 */
	public void validateAttributes(List<Customer_Attributes> attributesList) {
		if(attributesList!=null && attributesList.size()> 0) {
			for (Customer_Attributes customerAttributes : attributesList) {
				try {
					System.out.println("Customer Attribute Group name : "+customerAttributes.getAttributegroupname());
					System.out.println("Customer value : "+customerAttributes.getValue());
					System.out.println("Customer Attributes : "+customerAttributes.getAttributes());
					customerAttributes.setAttributes(customerAttributes.getAttributes() + ",VAM");
					System.out.println("Customer Attributes Updated : "+customerAttributes.getAttributes());
					System.out.println("Customer Attribute Name : "+customerAttributes.getAttrbutename());
					System.out.println("Customer Attribute Value : "+customerAttributes.getAttrbutevalue());
					customerAttributes.setAttrbutevalue(customerAttributes.getAttrbutevalue()+"_jar");
					System.out.println("Customer Attribute Value Updated : "+customerAttributes.getAttrbutevalue());
					
				} catch (Exception ex) {
					System.out.println("Error :" + ex.getMessage());
				}
			}
		}
	}

	

}
