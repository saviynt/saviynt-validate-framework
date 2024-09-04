package com.saviynt.custom.UserPreprocessValidator.validator;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.saviynt.custom.domain.User;

/**
 * Sample File for
 * 
 * The Class UsernameRuleValidator.
 */
public class UserBulkCustomValidator {

	/**
	 * Validate organization user rule.
	 *
	 * @param userListJson the user list json
	 * @return the string
	 */
	public String validateOrganizationUserRule(String userListJson) {
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
					} catch(Exception ex) {
						System.err.print("Format: '"+format+"' not supported for '"+date+"' Date String" );
					}
				}
				return formattedDate;
			}

		});
		Gson gson = builder.create();
		User[] userDataList = gson.fromJson(userListJson, User[].class);
		for (User userMap : userDataList) {

			checkUserData(userMap);

			System.out.println(userMap);
		}

		String validatedresponse = gson.toJson(userDataList);
		return validatedresponse;
	}

	/**
	 * Check user data.
	 *
	 * @param userMap the user map
	 */
	private void checkUserData(User userMap) {
		String msg = "";
		System.out.println("Manager : "+userMap.getManager());
		System.out.println("Secondary Email : "+userMap.getSecondaryEmail());
		System.out.println(" secondaryManager : "+userMap.getSecondaryManager());
		System.out.println("########### Source : "+userMap.getSource());
		System.out.println("########### Source Action : "+userMap.getSourceaction());

		if(StringUtils.isAlphanumeric(userMap.getCostcenter())) {
			if(StringUtils.isNotBlank(userMap.getCostcenter()) && userMap.getCostcenter().length()!=5) {
				msg = msg + "Cost Center Length should be equals to 5;";
			}
		} else {
			msg = msg + "Cost Center should Alpha-Numeric i.e. SH001;";
		}
		
		if(StringUtils.isBlank(userMap.getCustomer())) {
			msg = msg + "Organization should not be Empty;";
		} else {
			if(StringUtils.isNotBlank(userMap.getOwnedorganization()) && !userMap.getOwnedorganization().contains(userMap.getCustomer())) {
				msg = msg + "You are not allowed to create or update any user for the selected Organization;";
			}
		}
		
		System.out.println("Additional attribute SYStem ID: "+userMap.getSystem_id());
		if(StringUtils.isBlank(userMap.getCity()) || (userMap.getCity()!=null && StringUtils.isBlank(userMap.getCity().trim()))) {
			msg = msg + " City should not be Empty;";
		}
		
		if(StringUtils.isNotBlank(userMap.getCountry())) {
			
			
			if("United States of America".equalsIgnoreCase(userMap.getCountry().trim())) {
				msg= msg + userMap.getCountry()+" should be USA;";
			}
		}
		if(StringUtils.isNotBlank(userMap.getManager())) {
			System.out.println("Manager Already set as :"+userMap.getManager());
		} else {
			userMap.setManager("admin");
			System.out.println("Manager Set as :"+userMap.getManager());
		}
	

		if (StringUtils.isNotBlank(msg)) {
			userMap.setVALIDATION(msg);
		}
	}

}
