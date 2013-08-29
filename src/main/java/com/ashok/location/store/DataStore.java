package com.ashok.location.store;



public class DataStore {
	private Double latitude;
	private Double longitude;
	private String uploader;
	private String route;
	private long lastUpdatedTime;

	public DataStore(Double latitude, Double longitude, String uploader,
			String route, long lastUpdatedTime) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.uploader = uploader;
		this.route = route;
		this.lastUpdatedTime = lastUpdatedTime;
	}
	
	
	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public long getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(long lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	@Override
	public String toString() {
		/*return "Lat: " + getLatitude() + "\nLong: " + getLongitude()
				+ "\nRoute: " + getRoute() + "\nUploader: " + getUploader()
				+ "\nTime: " + getLastUpdatedTime();
		*/
		
		return getLatitude() + ":" + getLongitude();
		
	}

	@Override
	public int hashCode() {
		return getRoute().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		DataStore other = (DataStore) obj;

		if (this.getRoute().equals(other.getRoute())) {
			return true;
		}

		return false;
	}
}
