/*
 * 
 */
package com.saviynt.custom.UserPreprocessValidator.validator;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.saviynt.custom.UserPreprocessValidator.domain.User;
import com.saviynt.custom.UserPreprocessValidator.domain.ValidationResponse;

/**
 * The Class UserCustomValidator.
 */
public class UserCustomValidator {
	
	/** The format selected. */
	String formatSelected = "";
	/**
	 * Do custom preprocess validation.
	 *
	 * @param userJson the user json
	 * @return the string
	 */
	public String doCustomPreprocessValidation(String userJson) {
		boolean isValid = true;
		String errorMessages = "";
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
		
		builder.registerTypeAdapter(Date.class,new JsonSerializer<Date>() {

			public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
				String formattedDate = null;
				SimpleDateFormat formatter = null;
				try {
					formatter = new SimpleDateFormat(formatSelected);
					formattedDate = formatter.format(src);
				} catch(Exception ex) {
					System.err.print("Format: '"+formatSelected+"' not supported for '"+formattedDate+"' Date String" );
				}
				return new JsonPrimitive(formattedDate) ;
			}
		});
		
		Gson gson = builder.create();
		User userData = gson.fromJson(userJson, User.class);
		System.out.println("Secondary Email : "+userData.getSecondaryEmail());
		System.out.println(" secondaryManager : "+userData.getSecondaryManager());
		System.out.println("########### Source : "+userData.getSource());
		System.out.println("########### Source Action : "+userData.getSourceaction());
		System.out.println("Request Params : "+userData.getRequestparams());
		// Username Check:
		if (StringUtils.isNotBlank(userData.getUsername())) {
			if (userData.getUsername().length() < 6) {
				errorMessages = "Username length should be greater than or equals to 6;";
				isValid = false;
			}

		} else {
			errorMessages = "Empty Username provided;";
			isValid = false;
		}
		// Firstname Check
		if (StringUtils.isNotBlank(userData.getFirstname())) {
			if (userData.getFirstname().length() < 6) {
				errorMessages = errorMessages + userData.getFirstname()
						+ " length should be greater than or equals to 6;";
				isValid = false;
			}

		} else {
			errorMessages = errorMessages + "Empty First Name provided;";
			isValid = false;
		}

		// LastName Check
		if (StringUtils.isNotBlank(userData.getLastname())) {
			if (userData.getLastname().length() < 6) {
				errorMessages = errorMessages + userData.getLastname()
						+ " length should be greater than or equals to 6;";
				isValid = false;
			}

		} else {
			errorMessages = errorMessages + "Empty Last Name provided;";
			isValid = false;
		}

		if (StringUtils.isAlphanumeric(userData.getCostcenter())) {
			if (StringUtils.isNotBlank(userData.getCostcenter()) && userData.getCostcenter().length() != 5) {
				errorMessages = errorMessages + "Cost Center Length should be equals to 5;";
				isValid = false;
			} 
		} else {
			errorMessages = errorMessages + "Cost Center should Alpha-Numeric i.e. SH001;";
			isValid = false;
		}

		if (StringUtils.isBlank(userData.getCustomer())) {
			errorMessages = errorMessages + "Organization should not be Empty;";
			isValid = false;
		} else {
			if (StringUtils.isNotBlank(userData.getOwnedorganization())
					&& !userData.getOwnedorganization().contains(userData.getCustomer())) {
				errorMessages = errorMessages
						+ "You are not allowed to create or update any user for the selected Organization;";
				isValid = false;
			}
		}

		if (StringUtils.isBlank(userData.getCity())) {
			errorMessages = errorMessages + " City should not be Empty;";
			isValid = false;
		}
		
		if (userData.getStatuskey() != null ) {
			if(userData.getStatuskey() == 0l) {
				String dateStr = "2021-01-31";
				Date termDate;
				try {
					termDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
					userData.setTermDate(termDate );
				} catch (ParseException e) {
					System.out.println("Invalid Date");
				}
			} 
		}

		if (StringUtils.isNotBlank(userData.getCountry())) {

			if ("United States of America".equalsIgnoreCase(userData.getCountry().trim())) {
				errorMessages = errorMessages + userData.getCountry() + " should be USA;";
				isValid = false;
			}
		}
		
		// Validation Response with Valid flag, Error Messages and User Data
		ValidationResponse response = new ValidationResponse();
		response.setIsValid(isValid);
		response.setErrorMessage(errorMessages);
		response.setUsers(userData);

		return gson.toJson(response);
	}

}
