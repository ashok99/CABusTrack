package com.ashok.location.store.escort;

import com.ashok.location.store.DataStore;

/**
 * 
 * @author ashok
 * 
 */
public class EscortDataStore extends DataStore {

	String pmfkey;
	String status;

	public EscortDataStore(Double latitude, Double longitude, String pmfKey, String network, long lastUpdatedTime, String pStatus) {
		
		super(latitude, longitude, pmfKey, network, lastUpdatedTime);
		this.pmfkey = pmfKey;
		this.status = pStatus;
	}

	public String getPmfkey() {
		return pmfkey;
	}

	public void setPmfkey(String pmfkey) {
		this.pmfkey = pmfkey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
