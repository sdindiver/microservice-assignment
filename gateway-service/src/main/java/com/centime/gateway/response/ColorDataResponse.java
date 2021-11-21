package com.centime.gateway.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ColorDataResponse {

	@JsonProperty(value = "Name")
	private String name;

	@JsonIgnore
	private String color;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty(value = "Sub Classes")
	private List<ColorDataResponse> childColorData;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public List<ColorDataResponse> getChildColorData() {
		return childColorData;
	}

	public void setChildColorData(List<ColorDataResponse> childColorData) {
		this.childColorData = childColorData;
	}

	public void addChildColorData(ColorDataResponse childDTO) {
		if (childColorData == null) {
			childColorData = new ArrayList<ColorDataResponse>();
		}
		childColorData.add(childDTO);
	}

}