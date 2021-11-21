package com.centime.gateway.request;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {
	@NotBlank(message = "Name is mandatory")
	@JsonProperty("Name")
	private String name;
	@NotBlank(message = "Surname is mandatory")
	@JsonProperty("Surname")
	private String surName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

}