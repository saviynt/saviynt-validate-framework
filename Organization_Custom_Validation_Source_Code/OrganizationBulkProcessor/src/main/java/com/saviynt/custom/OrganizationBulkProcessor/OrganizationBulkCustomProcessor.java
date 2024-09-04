package com.saviynt.custom.OrganizationBulkProcessor;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.saviynt.custom.model.Customer;
import com.saviynt.custom.model.OrgnizationBulkData;
import com.saviynt.custom.model.ValidationResponse;

/**
 * The Class OrganizationBulkCustomProcessor.
 * Organization Bulk Upload custom validation for Organization Upload process.
 */
public class OrganizationBulkCustomProcessor {

	/** The class name. */
	private String className = this.getClass().getName();
	
	/** The log. */
	Logger log = Logger.getLogger(className);
	
	/** The format selected. */
	String formatSelected = "";
	
	/** The validator. */
	private OrgBulkValidator validator = new OrgBulkValidator();
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/blankdb2?serverTimezone=UTC","root","password");  
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
	
	/**
	 * Bulk validation process.
	 *
	 * @param orguploadList the orgupload list
	 * @return the string
	 */
	public String bulkValidationProcess(String orguploadList) {
		
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

			public Date deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				Date formattedDate = null;
				SimpleDateFormat formatter = null;
				String date = element.getAsString();
				String[] dateFormatAry = { "MMM dd, yyyy", "MM-dd-yyyy", "dd-MMM-yyyy", "dd/MM/yyyy", "MM/dd/yyyy", "dd/MMM/yyyy" };
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
				} catch (Exception ex) {
					System.err.print(
							"Format: '" + formatSelected + "' not supported for '" + formattedDate + "' Date String");
				}
				return new JsonPrimitive(formattedDate);
			}
		});
		
		Gson gson = builder.create();
		OrgnizationBulkData orgUploadData = gson.fromJson(orguploadList, OrgnizationBulkData.class);
		if (orgUploadData != null) {
			for (Customer customer : orgUploadData.getCUSTOMER()) {
				processCustomerData(customer, false);
				System.out.println(customer);
			}
			if (orgUploadData.getOWNERS() != null) {
				validator.validateOwners(orgUploadData.getOWNERS());
			}
			if (orgUploadData.getENDPOINTS() != null) {
				validator.validateEndpoints(orgUploadData.getENDPOINTS());
			}
			if (orgUploadData.getENTITLEMENTS()!= null) {
				validator.validateEntitlements(orgUploadData.getENTITLEMENTS());
			}
			if (orgUploadData.getROLES() != null) {
				validator.validateRoles(orgUploadData.getROLES());
			}
			if (orgUploadData.getATTRIBUTES() != null) {
				validator.validateAttributes(orgUploadData.getATTRIBUTES());
			}
			System.out.println("Source : "+orgUploadData.getSource());
			System.out.println("Source Action : "+orgUploadData.getSourceaction());
		}
		String validatedresponse = gson.toJson(orgUploadData);
		return validatedresponse;
	}
	
	
	/**
	 * Bulk process organization.
	 *
	 * @param customerDetailList the customer detail list
	 * @return the string
	 */
	public String bulkProcessOrganization(String customerDetailList) {

		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

			public Date deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				Date formattedDate = null;
				SimpleDateFormat formatter = null;
				String date = element.getAsString();
				String[] dateFormatAry = { "MMM dd, yyyy", "MM-dd-yyyy", "dd-MMM-yyyy", "dd/MM/yyyy", "MM/dd/yyyy", "dd/MMM/yyyy" };
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
				} catch (Exception ex) {
					System.err.print(
							"Format: '" + formatSelected + "' not supported for '" + formattedDate + "' Date String");
				}
				return new JsonPrimitive(formattedDate);
			}
		});
		Gson gson = builder.create();
		Customer[] customerList = gson.fromJson(customerDetailList, Customer[].class);
		for (Customer customer : customerList) {
			processCustomerData(customer, false);
			System.out.println(customer);
		}

		String validatedresponse = gson.toJson(customerList);
		return validatedresponse;
	}
	
	/**
	 * Custom action: For Org Update Rule Action
	 *
	 * @param customer the customer
	 */
	public void customAction(String customer) {
		log.info("#############CUSTOM ACTION START#############");
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

			public Date deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				Date formattedDate = null;
				SimpleDateFormat formatter = null;
				String date = element.getAsString();
				String[] dateFormatAry = { "MMM dd, yyyy", "MM-dd-yyyy", "dd-MMM-yyyy", "dd/MM/yyyy", "MM/dd/yyyy", "dd/MMM/yyyy" };
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
				} catch (Exception ex) {
					System.err.print(
							"Format: '" + formatSelected + "' not supported for '" + formattedDate + "' Date String");
				}
				return new JsonPrimitive(formattedDate);
			}
		});
		Gson gson = builder.create();
		Customer customerDomain = gson.fromJson(customer, Customer.class);
		System.out.println("######## Customer Name: "+customerDomain.getCustomername()+" ##########");
		//Connection connection = getConnection();
		//try {
			System.out.println("################# !!!Custom Jar Invoked !!! ###########");
			//Statement stmt = connection.createStatement();
			//stmt.executeUpdate("UPDATE CUSTOMER SET CUSTOMPROPERTY20='CUSTOM ACTION PERFORMED' WHERE CUSTOMERNAME='"+customerDomain.getCustomername()+"'");
			System.out.println("################ !!! Custom Action COMPLETED !!! #############");
		//} catch (SQLException e) {
			//try {
				//connection.close();
			//} catch (SQLException e1) {
				//System.out.println(e1);
			//}
			//e.printStackTrace();
		//}
		System.out.println("########### !!! Custom Action END !!! #############");
	}

	/**
	 * Process organization.
	 *
	 * @param customer the customer
	 * @return the string
	 */
	public String processOrganization(String customer) {

		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

			public Date deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				Date formattedDate = null;
				SimpleDateFormat formatter = null;
				String date = element.getAsString();
				String[] dateFormatAry = { "MMM dd, yyyy", "MM-dd-yyyy", "dd-MMM-yyyy", "dd/MM/yyyy", "MM/dd/yyyy", "dd/MMM/yyyy" };
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
				} catch (Exception ex) {
					System.err.print(
							"Format: '" + formatSelected + "' not supported for '" + formattedDate + "' Date String");
				}
				return new JsonPrimitive(formattedDate);
			}
		});
		Gson gson = builder.create();
		Customer customerDomain = gson.fromJson(customer, Customer.class);
		
		if(StringUtils.isNotBlank(customerDomain.getIsApi()) && "TRUE".equalsIgnoreCase(customerDomain.getIsApi())) {
			processCustomerData(customerDomain, false);
		} else {
			processCustomerData(customerDomain, true);
		}
		

		ValidationResponse response = new ValidationResponse();
		
		Boolean isValid = true;
		if(StringUtils.isNotBlank(customerDomain.getValidation())) {
			isValid = false;
		} 
		response.setIsValid(isValid);
		response.setErrorMessage(customerDomain.getValidation());
		customerDomain.setValidation(null);
		response.setCustomer(customerDomain);;
		System.out.println(response);

		String validatedresponse = gson.toJson(response);
		System.out.println(validatedresponse);
		return validatedresponse;
	}

	/**
	 * Process customer data.
	 *
	 * @param customer the customer
	 * @param isSingle the is single
	 */
	private void processCustomerData(Customer customer, boolean isSingle) {
		String methodName = " :: processCustomerData() :: ";
		String errorMsg = "";

		System.out.println("######### Source : " + customer.getSource());
		System.out.println("######### Source Action: " + customer.getSourceaction());
		
		log.info(className+methodName+ "Organization Name :: " +customer.getCustomername());
		if (StringUtils.isNotBlank(customer.getCustomername())) {
			String customername = customer.getCustomername().trim();
			if(customername.length()<2 || customername.length()>15) {
				errorMsg += "Organization Name length should be between 2 - 15 characters;";
			}
			
		} else {
			errorMsg += "Organization Name should not be empty;";
		}
		
		log.info(className + methodName + "Status :: " + customer.getStatus());
		 if (customer.getStatus() == null) { 
		     errorMsg = errorMsg +"Status field value should be Actve/InActive;"; 
		 }

		log.info(className+methodName+ "Customer Type :: " +customer.getCustomertype());
		if (customer.getCustomertype() == null) {
			errorMsg += "Organization Type should not be empty;";
		} else if(customer.getCustomertype()!=null){
			Long customertype = customer.getCustomertype();
			if(customertype < 1l || customertype > 4l) {
				errorMsg += "Organization Type Should be 'DATA SECURITY', 'GROUPING', 'REQUEST RULES', 'COMPANY';";
			}
		}
		
		//Organization Type
		log.info(className+methodName+ "Custom Property 1 :: " +customer.getCustomproperty1());
		ArrayList<String> adCompanyTypeList = new ArrayList<String>();
		adCompanyTypeList.add("Shell Company");
		adCompanyTypeList.add("NIC");
		adCompanyTypeList.add("Non-Shell JV");
		adCompanyTypeList.add("Third Party");
		if(StringUtils.isNotBlank(customer.getCustomproperty1())) {
			if(!adCompanyTypeList.contains(customer.getCustomproperty1().trim())) {
				errorMsg += "Custom Property 1 should be one of the "+adCompanyTypeList+" values;";
			}
		}
		
		
		//Identity Attestation Period validation
		log.info(className+methodName+ "Custom Property 3 :: " + customer.getCustomproperty3());
		if(StringUtils.isNotBlank(customer.getCustomproperty3()) && !StringUtils.isNumeric(customer.getCustomproperty3().trim())) {
			errorMsg += "Custom Property 3 should be Numeric;";
		}
		
		//Identity Source Rule
		log.info(className+methodName+ "Custom Property 4 :: " +customer.getCustomproperty4());
		ArrayList<String> sourceRuleList = new ArrayList<String>();
		sourceRuleList.add("S:WD;C:IP");
		sourceRuleList.add("S:WD;C:VM");
		sourceRuleList.add("S:VM;C:VM");
		if(StringUtils.isNotBlank(customer.getCustomproperty4())) {
			if(!sourceRuleList.contains(customer.getCustomproperty4().trim())) {
				errorMsg += "Custom Property 4 should be one of the [S:WD C:IP, S:WD C:VM, S:VM C:VM] values;";
			}
		}
		/*
		 * else { errorMsg +=
		 * "Identity Source Rule should be one of the "+sourceRuleList+" values;"; }
		 */
		

		
		
		//Relationship Owner - email case
		log.info(className + methodName + "Custom Property 8 :: " + customer.getCustomproperty8());
		if (StringUtils.isNotBlank(customer.getCustomproperty8())) {
			String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(customer.getCustomproperty8());
			if (!matcher.matches()) {
				errorMsg += " Provide valid Custom Property 8 of Relationship Owner;";
			}
		}
		
		//Start Date and End Date Validations
		log.info(className+methodName+ "Start Date :: " +customer.getStartdate());
		log.info(className+methodName+ "End Date :: " + customer.getEnddate());
		errorMsg += dateValidations(customer.getStartdate(), customer.getEnddate());
		

		
		//Information Sharing Type
		log.info(className+methodName+ "Custom Property 16  :: " +customer.getCustomproperty16());
		ArrayList<String> sharingTypeList = new ArrayList<String>();
		sharingTypeList.add("Shell");
		sharingTypeList.add("Treat as Shell");
		sharingTypeList.add("Treat as Non-Shell");
		sharingTypeList.add("Non-Shell");
		if(StringUtils.isNotBlank(customer.getCustomproperty16())) {
			if(!sharingTypeList.contains(customer.getCustomproperty16().trim())) {
				errorMsg += "Custom Property 16 should be one of the "+sharingTypeList+" values;";
			}
		}
		/*
		 * else { errorMsg +=
		 * "Custom Property 16 should be one of the "+sharingTypeList+" values;"; }
		 */
		
		

		//IAL Types
		log.info(className+methodName+ "Custom Property 18 :: " + customer.getCustomproperty18());
		ArrayList<String> IALType = new ArrayList<String>();
		IALType.add("IAL1");
		IALType.add("IAL2");
		IALType.add("IAL3");
		if(StringUtils.isNotBlank(customer.getCustomproperty18())) {
			if(!IALType.contains(customer.getCustomproperty18().trim())) {
				errorMsg += "Custom Property 18 should be one of the "+IALType+" values;";
			}
		}
		/*
		 * else { errorMsg +=
		 * "Custom Property 18 should be one of the "+IALType+" values;"; }
		 */

		//IAL Managed Types
		log.info(className+methodName+ "Custom Property 21 :: " + customer.getCustomproperty21());
		ArrayList<String> IALManagedType = new ArrayList<String>();
		IALManagedType.add("Shell Managed");
		IALManagedType.add("Self Managed");
		if(StringUtils.isNotBlank(customer.getCustomproperty21())) {
			if(!IALManagedType.contains(customer.getCustomproperty21())) {
				errorMsg += "Custom Property 21 should be one of the "+IALManagedType+" values;";
			}
		}
		/*
		 * else { errorMsg +=
		 * "Custom Property 21 should be one of the "+IALManagedType+" values;"; }
		 */
		
		//Company Indicator
		log.info(className+methodName+ "Custom Property 22 :: " + customer.getCustomproperty22());
		ArrayList<String> companyIndicator = new ArrayList<String>();
		companyIndicator.add("Shell");
		companyIndicator.add("JV");
		companyIndicator.add("Third Party- Suppliers");
		companyIndicator.add("Third Party-Shell Contracted ");
		companyIndicator.add("Third Party-Non-Shell Contracted");
		if(StringUtils.isNotBlank(customer.getCustomproperty22())) {
			if(!companyIndicator.contains(customer.getCustomproperty22())) {
				errorMsg += "Custom Property 22 should be one of the "+companyIndicator+" values;";
			}
		}
		
		log.info(className+methodName+ "Custom Property 23 :: " + customer.getCustomproperty23());
		ArrayList<String> cp23List = new ArrayList<String>();
		cp23List.add("DS");
		cp23List.add("GF");
		cp23List.add("UP");
		
		if(StringUtils.isNotBlank(customer.getCustomproperty23())) {
			String cp23=customer.getCustomproperty23().trim();
			Boolean isValid = getCPValidation(cp23, cp23List);
			if(!isValid) {
				errorMsg += "Custom Property 23 should be one of the "+cp23List+" values;";
			}
		}
		
		log.info(className+methodName+ "Custom Property 24 :: " + customer.getCustomproperty24());
		ArrayList<String> cp24List = new ArrayList<String>();
		cp24List.add("GI");
		cp24List.add("C4C");
		cp24List.add("Apps Only");
		cp24List.add("TPA-Apps Only");
		cp24List.add("TPA-Restricted");
		
		if(StringUtils.isNotBlank(customer.getCustomproperty24())) {
			String cp24=customer.getCustomproperty24().trim();
			Boolean isValid = getCPValidation(cp24, cp24List);
			if(!isValid) {
				errorMsg += "Custom Property 24 should be one of the "+cp24List+" values;";
			}
		}
		
		ArrayList<String> cp25List = new ArrayList<String>();
		cp25List.add("S");
		cp25List.add("C");
		cp25List.add("N");
		cp25List.add("NC");
		cp25List.add("NO");
		cp25List.add("SO");
		cp25List.add("X");
		cp25List.add("NX");
		if(StringUtils.isNotBlank(customer.getCustomproperty25())) {
			String cp25=customer.getCustomproperty25().trim();
			Boolean isValid = getCPValidation(cp25, cp25List);
			if(!isValid) {
				errorMsg = errorMsg + "Customer Property 25 should be one of the "+cp25List+" values;";
			}
			
		}
		
		customer.setCustomproperty26("jar updated field data");
		customer.setCustomproperty27("jar updated field data");
		customer.setCustomproperty28("jar updated field data");
		customer.setCustomproperty10("jar updated field data");
		
		/*
		 * else { errorMsg +=
		 * "Custom Property 22 should be one of the "+companyIndicator+" values;"; }
		 */
		
		/*
		 * if(StringUtils.isBlank(customer.getOrganizationowners()) && !isSingle) {
		 * errorMsg = errorMsg + "Organization Owners are mandatory"; } else {
		 * log.info(className + methodName +
		 * "Owners: "+customer.getOrganizationowners());
		 * 
		 * }
		 */
		
		if(StringUtils.isNotBlank(customer.getOperation())) {
			String operation  = customer.getOperation();
			if(!("ADD".equalsIgnoreCase(operation)
					 ||"UPDATE".equalsIgnoreCase(operation)
					 ||"DELETE".equalsIgnoreCase(operation))) {
				errorMsg = errorMsg + " Operation type should be ADD, UPDATE or DELETE;";
			} 
		}
		
		customer.setValidation(errorMsg);
		
		
	}
	
	/**
	 * Gets the CP validation.
	 *
	 * @param property the property
	 * @param cp25List the cp 25 list
	 * @return the CP validation
	 */
	private Boolean getCPValidation(String property, List<String> cp25List) {
		boolean flag = true;
		if(property.contains(",")) {
			String[] properties = property.split(",");
			for (String prop : properties) {
				if(!cp25List.contains(prop)) {
					flag =  false;
					break;
				}
			}
		} else {
			flag = cp25List.contains(property) ?  true :  false;
		}
		
		return flag;
	}

	/**
	 * Date validations.
	 *
	 * @param startdate the startdate
	 * @param enddate the enddate
	 * @return the string
	 */
	public String dateValidations(Date startdate, Date enddate) {
		String methodName = " :: dateValidations() :: ";
		boolean dateValidationSuccess = true;
		if (startdate != null && enddate != null) {
			log.info(className + methodName + " enddate.compareTo(startdate) :: " + enddate.compareTo(startdate));
			log.info(className + methodName + " startdate.compareTo(enddate) :: " + startdate.compareTo(enddate));
			if (enddate.compareTo(startdate) < 0) {
				dateValidationSuccess = false;
			}
		} else {
			dateValidationSuccess = false;
		}

		if (!dateValidationSuccess) {
			return " Start Date and End Date are mandatory and End Date should not be less than Start Date;";
		}
		return "";

	}
	

}
