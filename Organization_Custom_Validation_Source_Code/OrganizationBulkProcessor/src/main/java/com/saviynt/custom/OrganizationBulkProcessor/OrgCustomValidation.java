package com.saviynt.custom.OrganizationBulkProcessor;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.saviynt.custom.model.Attributes;
import com.saviynt.custom.model.Customer;
import com.saviynt.custom.model.CustomerValidationRequest;
import com.saviynt.custom.model.Endpoints;
import com.saviynt.custom.model.Entitlements;
import com.saviynt.custom.model.Owners;
import com.saviynt.custom.model.Roles;

/**
 * The Class OrgCustomValidation.
 *
 * @author Raj
 */
public class OrgCustomValidation {

	/**
	 * Validate org create.
	 *
	 * @param customerDomainRequest the customer domain request
	 * @param tabArray              the tab array
	 */
	public void validateOrgCreate(CustomerValidationRequest customerDomainRequest, List<String> tabArray) {
		String tab = customerDomainRequest.getTab();
		// Start Validation as per tabs
		System.out.println("customerDomainRequest :" + customerDomainRequest.toString());
		String message = "";
		Customer customer = customerDomainRequest.getDetails();
		if (customer != null && tabArray.contains("details")) {
			if (StringUtils.isBlank(customer.getCustomername())) {
				message = message + "Customer Name can not be empty!;";
			}
			if (customer.getCustomertype() == null) {
				message = message + "Customer Type can not be empty!;";
			}
			if (customer.getStartdate() == null) {
				message = message + "Customer Start Date can not be null!;";
			}
			if (customer.getEnddate() == null) {
				message = message + "Customer End Date can not be null!;";
			}
			customer.setCustomproperty27("O=TPC,OU=ShellOrg,DC=IMS,DC=Shell,DC=com");
		}
		List<Endpoints> endpointList = customerDomainRequest.getEndpoints();
		if (endpointList != null && endpointList.size() > 0 && tabArray.contains("endpoints")) {
			for (Endpoints endpoints : endpointList) {
				if (endpoints != null && endpoints.getEndpointname() == null) {
					message = message + "Endpoint name can not be null!;";
				}
			}
		} else if (tabArray.contains("endpoints")) {
			message = message + "Please select Endpoint!;";
		}

		List<Owners> ownerList = customerDomainRequest.getOwners();
		if (ownerList != null && ownerList.size() > 0 && tabArray.contains("owners")) {
			for (Owners owner : ownerList) {
				if (owner != null && owner.getOwner() == null) {
					message = message + "Owner name can not be null!;";
				}
			}
		} else if (!tab.equalsIgnoreCase("api") && tabArray.contains("owners")) {
			message = message + "Please select Owners!;";
		}

		List<Roles> roleList = customerDomainRequest.getRoles();
		if (roleList != null && roleList.size() > 0 && tabArray.contains("roles")) {
			for (Roles role : roleList) {
				if (role != null && role.getRolename() == null) {
					message = message + "Role name can not be null!;";
				}
			}
		} else if (!tab.equalsIgnoreCase("api") && tabArray.contains("roles")) {
			message = message + "Please select Roles!;";
		}

		List<Entitlements> entitlementsList = customerDomainRequest.getEntitlements();
		if (entitlementsList != null && entitlementsList.size() > 0 && tabArray.contains("entitlements")) {
			for (Entitlements entitlements : entitlementsList) {
				if (entitlements != null && entitlements.getEntitlementvalue() == null) {
					message = message + "Entitlements name can not be null!;";
				}
			}
		} else if (!tab.equalsIgnoreCase("api") && tabArray.contains("entitlements")) {
			message = message + "Please select Entitlements!;";
		}

		List<Attributes> attributesList = customerDomainRequest.getAttributes();
		if (attributesList != null && attributesList.size() > 0 && tabArray.contains("attributes")) {
			for (Attributes attribute : attributesList) {
				if (attribute != null && attribute.getAttributegroupname() == null) {
					message = message + "Attribute name can not be null!;";
				}
			}
		} else if (!tab.equalsIgnoreCase("api") && tabArray.contains("attributes")) {
			message = message + "Please select Attributes!;";
		}

		customerDomainRequest.setErrorMessage(message);
	}

	/**
	 * Validate org API.
	 *
	 * @param customerDomainRequest the customer domain request
	 * @param tabArray              the tab array
	 */
	public void validateOrgAPI(CustomerValidationRequest customerDomainRequest, List<String> tabArray) {
		/* String tab = customerDomainRequest.getTab(); */
		// Start Validation as per tabs
		System.out.println("customerDomainRequest :" + customerDomainRequest.toString());
		String message = "";
		Customer customer = customerDomainRequest.getDetails();
		if (customer != null && tabArray.contains("details")) {
			if (StringUtils.isBlank(customer.getCustomername())) {
				message = message + "Customer Name can not be empty!;";
			}
			if (customer.getCustomertype() == null) {
				message = message + "Customer Type can not be empty!;";
			}
			if (customer.getStartdate() == null) {
				message = message + "Customer Start Date can not be null!;";
			}
			if (customer.getEnddate() == null) {
				message = message + "Customer End Date can not be null!;";
			}
			// Sample code to Set Organization Datas
			customer.setComments("updated value");
			customer.setCustomproperty1("cp1");
			customer.setCustomproperty5("cp5");
			customer.setCustomproperty6("cp6");
			customer.setCustomproperty3("cp3");
			customer.setCustomproperty4("cp4");
			customer.setCustomproperty10("");
		}
		List<Endpoints> endpointList = customerDomainRequest.getEndpoints();
		if (endpointList != null && endpointList.size() > 0 && tabArray.contains("endpoints")) {
			for (Endpoints endpoints : endpointList) {
				if (endpoints != null && endpoints.getEndpointname() == null) {
					message = message + "Endpoint name can not be null!;";
				}
			}
		}
		List<Owners> ownerList = customerDomainRequest.getOwners();
		if (ownerList != null && ownerList.size() > 0 && tabArray.contains("owners")) {
			for (Owners owner : ownerList) {
				if (owner != null && owner.getOwner() == null) {
					message = message + "Owner name can not be null!;";
				}
			}
		} else {
			message = message + "Please provide the owner with Rank!;";
		}

		List<Roles> roleList = customerDomainRequest.getRoles();
		if (roleList != null && roleList.size() > 0 && tabArray.contains("roles")) {
			for (Roles role : roleList) {
				if (role != null && role.getRolename() == null) {
					message = message + "Role name can not be null!;";
				}
			}
		}
		List<Entitlements> entitlementsList = customerDomainRequest.getEntitlements();
		if (entitlementsList != null && entitlementsList.size() > 0 && tabArray.contains("entitlements")) {
			for (Entitlements entitlements : entitlementsList) {
				if (entitlements != null && entitlements.getEntitlementvalue() == null) {
					message = message + "Entitlements name can not be null!;";
				}
			}
		}

		List<Attributes> attributesList = customerDomainRequest.getAttributes();
		if (attributesList != null && attributesList.size() > 0 && tabArray.contains("attributes")) {
			for (Attributes attribute : attributesList) {
				if (attribute != null && attribute.getAttributegroupname() == null) {
					message = message + "Attribute name can not be null!;";
				}
			}
		}

		customerDomainRequest.setErrorMessage(message);
	}

	/**
	 * Validate org update.
	 *
	 * @param customerDomainRequest the customer domain request
	 * @param tabArray              the tab array
	 */
	public void validateOrgUpdate(CustomerValidationRequest customerDomainRequest, List<String> tabArray) {
		String tab = customerDomainRequest.getTab();
		// Start Validation as per tabs
		System.out.println("customerDomainRequest :" + customerDomainRequest.toString());
		String message = "";
		Customer customer = customerDomainRequest.getDetails();
		if (customer != null && tabArray.contains("details")) {
			if (StringUtils.isBlank(customer.getCustomername())) {
				message = message + "Customer Name can not be empty!;";
			}
			if (customer.getCustomertype() == null) {
				message = message + "Customer Type can not be empty!;";
			}
			if (customer.getStartdate() == null) {
				message = message + "Customer Start Date can not be null!;";
			}
			if (customer.getEnddate() == null) {
				message = message + "Customer End Date can not be null!;";
			}
			// Sample code to set any data in Organization Properties
			customer.setComments("updated value");
			customer.setCustomproperty1("cp1");
			customer.setCustomproperty5("cp5");
			customer.setCustomproperty6("cp6");
			customer.setCustomproperty3("cp3");
			customer.setCustomproperty4("cp4");
		}
		List<Endpoints> endpointList = customerDomainRequest.getEndpoints();
		if (endpointList != null && endpointList.size() > 0 && tabArray.contains("endpoints")) {
			for (Endpoints endpoints : endpointList) {
				if (endpoints != null && endpoints.getEndpointname() == null) {
					message = message + "Endpoint name can not be null!;";
				}
			}
		}

		List<Owners> ownerList = customerDomainRequest.getOwners();
		if (ownerList != null && ownerList.size() > 0 && tabArray.contains("owners")) {
			for (Owners owner : ownerList) {
				if (owner != null && owner.getOwner() == null) {
					message = message + "Owner name can not be null!;";
				}
			}
		} else if (ownerList == null && tabArray.contains("owners")) {
			message = message + "Please select Owners!;";
		}

		List<Roles> roleList = customerDomainRequest.getRoles();
		if (roleList != null && roleList.size() > 0 && tabArray.contains("roles")) {
			for (Roles role : roleList) {
				if (role != null && role.getRolename() == null) {
					message = message + "Role name can not be null!;";
				}
			}
		}

		List<Entitlements> entitlementsList = customerDomainRequest.getEntitlements();
		if (entitlementsList != null && entitlementsList.size() > 0 && tabArray.contains("entitlements")) {
			for (Entitlements entitlements : entitlementsList) {
				if (entitlements != null && entitlements.getEntitlementvalue() == null) {
					message = message + "Entitlements name can not be null!;";
				}
			}
		}
		List<Attributes> attributesList = customerDomainRequest.getAttributes();
		if (attributesList != null && attributesList.size() > 0 && tabArray.contains("attributes")) {
			for (Attributes attribute : attributesList) {
				if (attribute != null && attribute.getAttributegroupname() == null) {
					message = message + "Attribute name can not be null!;";
				}
			}
		}

		customerDomainRequest.setErrorMessage(message);
	}
}
