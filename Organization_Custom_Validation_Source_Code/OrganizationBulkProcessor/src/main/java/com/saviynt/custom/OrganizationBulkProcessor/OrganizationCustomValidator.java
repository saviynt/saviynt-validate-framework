package com.saviynt.custom.OrganizationBulkProcessor;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.saviynt.custom.model.CustomerValidationRequest;

/**
 * The Class OrganizationCustomValidator.
 */
public class OrganizationCustomValidator {
	
	/** The class name. */
	private String className = this.getClass().getName();
	
	/** The log. */
	Logger log = Logger.getLogger(className);
	
	/** The format selected. */
	String formatSelected = "";
	
	/** The customer tab array. */
	String customerTabArray[] = {"details"};
	
	/** The owners tab array. */
	String ownersTabArray[] = {"details","owners"};
	
	/** The endpoints tab array. */
	String endpointsTabArray[] = {"details","owners","endpoints"};
	
	/** The roles tab array. */
	String rolesTabArray[] = {"details","owners","endpoints","roles"};
	
	/** The entitlements tab array. */
	String entitlementsTabArray[] = {"details","owners","endpoints","roles","entitlements"};
	
	/** The attributes tab array. */
	String attributesTabArray[] = {"details","owners","endpoints","roles","entitlements","attributes"};
	
	/** The sendforapproval tab array. */
	String sendforapprovalTabArray[] = {"details","owners","endpoints","roles","entitlements","attributes", "sendforapproval"};
	
	/** The customer tab list. */
	/* private List<String> customerTabList = Arrays.asList(customerTabArray); */
	
	
	/**
	 * Process organization.
	 *
	 * @param customerRequest the customer request
	 * @return the string
	 */
	public String processOrganization(String customerRequest) {
		OrgCustomValidation validation = new OrgCustomValidation();

		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			public Date deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				Date formattedDate = null;
				SimpleDateFormat formatter = null;
				String date = element.getAsString();
				String[] dateFormatAry = { "MMM dd, yyyy", "MM-dd-yyyy", "dd-MMM-yyyy", "dd/MM/yyyy", "MM/dd/yyyy", "dd/MMM/yyyy", "yyyy-MM-dd'T'HH:mm:ss'Z'" };
				for (String format : dateFormatAry) {
					try {
						formatter = new SimpleDateFormat(format);
						formattedDate = formatter.parse(date);
						formatSelected = format;
					} catch(Exception ex) {
						System.err.print("Format: '"+format+"' not supported for '"+date+"' Date String" );
					}
				}
				return formattedDate;
			}

		});
		
		builder.registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
			public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
				String formattedDate = null;
				SimpleDateFormat formatter = null;

				try {
					formatter = new SimpleDateFormat(formatSelected);
					formattedDate = formatter.format(src);
					// formatSelected = format;
				} catch (Exception ex) {
					System.err.print(
							"Format: '" + formatSelected + "' not supported for '" + formattedDate + "' Date String");
				}

				return new JsonPrimitive(formattedDate);
			}
		});
		Gson gson = builder.create();
		CustomerValidationRequest customerDomainRequest = gson.fromJson(customerRequest, CustomerValidationRequest.class);
		
		String tab = customerDomainRequest.getTab();
		String action = customerDomainRequest.getSourceaction();
		
		List<String> tabArray = null;
		//Get Validation Tab details:::
		if(tab.equalsIgnoreCase("details")) {
			tabArray = Arrays.asList(customerTabArray);
		} else if(tab.equalsIgnoreCase("owners")) {
			tabArray = Arrays.asList(ownersTabArray);
		} else if(tab.equalsIgnoreCase("endpoints")) {
			tabArray = Arrays.asList(endpointsTabArray);
		} else if(tab.equalsIgnoreCase("roles")) {
			tabArray = Arrays.asList(rolesTabArray);
		} else if(tab.equalsIgnoreCase("entitlements")) {
			tabArray = Arrays.asList(entitlementsTabArray);
		} else if(tab.equalsIgnoreCase("attributes")) {
			tabArray = Arrays.asList(attributesTabArray);
		} else if(tab.equalsIgnoreCase("sendforapproval")) {
			tabArray = Arrays.asList(sendforapprovalTabArray);
		}  else if(tab.equalsIgnoreCase("api")) {
			tabArray = Arrays.asList(sendforapprovalTabArray);
		}
		/**
		 * ***********************************
		 * * Validation Logic for Custom Jar *
		 * *								 *
		 * ***********************************
		 */
		if(tab.equalsIgnoreCase("api")) {
			validation.validateOrgAPI(customerDomainRequest, tabArray);
		} else if((tab.equalsIgnoreCase("sendforapproval") || tab.equalsIgnoreCase("details")) && "updateOrg".equalsIgnoreCase(action) ){
			validation.validateOrgUpdate(customerDomainRequest, tabArray);
		} else {
			validation.validateOrgCreate(customerDomainRequest, tabArray);
		}
		
		
		String responseString = gson.toJson(customerDomainRequest);
		
		return responseString;
		
	}

}
