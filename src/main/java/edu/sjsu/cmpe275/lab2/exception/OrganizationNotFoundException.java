package edu.sjsu.cmpe275.lab2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class OrganizationNotFoundException extends RuntimeException{
	public OrganizationNotFoundException(String userId) {
		super("could not find user '" + userId + "'.");
	}
}
