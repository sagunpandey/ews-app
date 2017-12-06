package com.sagunpandey.ews.entities;

import com.google.gson.annotations.SerializedName;

public class Station {

	@SerializedName("id")
	private Integer id;

	@SerializedName("riverId")
	private Integer riverId;

	@SerializedName("code")
	private String code;

	@SerializedName("name")
	private String name;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("longitude")
	private String longitude;

	@SerializedName("dangerLevel")
	private Double dangerLevel;

	@SerializedName("warningLevel")
	private Double warningLevel;

	private River river;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = this.name;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getDangerLevel() {
		return dangerLevel;
	}

	public void setDangerLevel(Double dangerLevel) {
		this.dangerLevel = dangerLevel;
	}

	public Double getWarningLevel() {
		return warningLevel;
	}

	public void setWarningLevel(Double warningLevel) {
		this.warningLevel = warningLevel;
	}

	public Integer getRiverId() {
		return riverId;
	}

	public void setRiverId(Integer riverId) {
		this.riverId = riverId;
	}

	public River getRiver() {
		return river;
	}

	public void setRiver(River river) {
		this.river = river;
	}
}
