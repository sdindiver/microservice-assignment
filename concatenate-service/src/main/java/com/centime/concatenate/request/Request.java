package com.centime.concatenate.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {
	@JsonProperty("Name")
	private String name;
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