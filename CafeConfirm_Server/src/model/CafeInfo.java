package model;

public class CafeInfo implements Model {
	private String cafeName;
	private String password;
	private String maxNum;
	private String latitude;
	private String longitude;
	private String numOfPeople;
	
	public CafeInfo(String cafeName, String numOfPeople) {
		this.cafeName = cafeName;
		this.numOfPeople = numOfPeople;
	}
	
	public CafeInfo(String cafeName, String password, String maxNum, String latitude ,String longitude) {
		this.cafeName = cafeName;
		this.password = password;
		this.maxNum = maxNum;
		this.latitude = latitude;
		this.longitude = longitude;
		this.numOfPeople = "-1";
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
	
	public String getCafeName() {
		return cafeName;
	}
	
	public void setCafeName(String cafeName) {
		this.cafeName = cafeName;
	}
	
	public String getNumOfPeople() {
		return numOfPeople;
	}
	
	public void setNumOfPeople(String numOfPeople) {
		this.numOfPeople = numOfPeople;
	}
	
	public String getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(String maxNum) {
		this.maxNum = maxNum;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
