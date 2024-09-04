package com.saviynt.custom.UserPreprocessValidator.domain;

/**
 * The Class ValidationResponse.
 */
public class ValidationResponse {
	
	/** The error message. */
	private String errorMessage;
	
	/** The is valid. */
	private Boolean isValid;
	
	/** The users. */
	private User users;

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

	/**
	 * Gets the checks if is valid.
	 *
	 * @return the checks if is valid
	 */
	public Boolean getIsValid() {
		return isValid;
	}

	/**
	 * Sets the checks if is valid.
	 *
	 * @param isValid the new checks if is valid
	 */
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public User getUsers() {
		return users;
	}

	/**
	 * Sets the users.
	 *
	 * @param users the new users
	 */
	public void setUsers(User users) {
		this.users = users;
	}

}
