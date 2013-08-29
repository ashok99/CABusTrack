package com.ashok.location.store;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.ashok.location.store.escort.EscortDataStore;

public class CacheManager {
	private static CacheManager inst = null;// new CacheManager();
	private static ConcurrentHashMap<String, DataStore> routeInformation;
	private static ConcurrentHashMap<String, DataStore> liveEscortDevices;
	private static List fixedRouteList = null;
	private static List<String> onDemandNetworkList = new ArrayList<String>();

	static {
		routeInformation = new ConcurrentHashMap<String, DataStore>();
		liveEscortDevices = new ConcurrentHashMap<String, DataStore>();
	}

	private CacheManager() {
		//
	}

	public static CacheManager getCacheManager() {
		if (inst == null) {
			synchronized (CacheManager.class) {
				if (inst == null) {
					inst = new CacheManager();
				}
			}
		}
		return inst;
	}

	public DataStore getStoreForRoute(String pRoute) {
		if (!isValidRoute(pRoute))
			return null;
		return routeInformation.get(pRoute);
	}
	
	public DataStore getStoreForDevice(String pPMF) {
		return liveEscortDevices.get(pPMF);
	}

	public void addOnDemandNetwork(String network) {
		if (!onDemandNetworkList.contains(network)) {
			onDemandNetworkList.add(network);
		}
	}

	public void updateLocationInfo(DataStore pInfo) {
		if (routeInformation == null)
			routeInformation = new ConcurrentHashMap<String, DataStore>();

		// TODO before cstoring validate the key names against fixed number of
		// routes
		routeInformation.put(pInfo.getRoute(), pInfo);
	}

	public String getAllAvailableRoutes() {
		StringBuffer buffer = new StringBuffer();
		if (routeInformation != null) {
			for (Entry<String, DataStore> entry : routeInformation.entrySet()) {
				buffer.append(entry.getKey() + ",");
			}
		}

		return buffer.toString();
	}

	private boolean isValidRoute(String pRoute) {
		if (fixedRouteList == null) {
			String[] rs = FixedRouteList.ROUTES.split(",");

			fixedRouteList = new ArrayList();
			for (String name : rs) {
				fixedRouteList.add(name);
			}
		}
		if (fixedRouteList.contains(pRoute)
				|| getOnDemandNetworks().contains(pRoute))
			return true;
		return false;
	}

	public List<String> getOnDemandNetworks() {
		return onDemandNetworkList;
	}

	public static void addDevice(EscortDataStore pDevice) {
		liveEscortDevices.put(pDevice.getPmfkey(), pDevice);
	}

	public static void removeDevice(String pmfKey) {
		liveEscortDevices.remove(pmfKey);
	}

	public static ConcurrentHashMap<String, DataStore> getAllDevices() {
		return liveEscortDevices;
	}
	
	public  String getJSONDeviceData() {
		StringBuffer buffer = new StringBuffer();
		JSONObject responseJSON = new JSONObject();
		final Set<Entry<String, DataStore>> entrySet = liveEscortDevices.entrySet();
		buffer.append("[");
		for (Entry<String, DataStore> entry : entrySet) {
			final EscortDataStore device = (EscortDataStore) entry.getValue();
			try {
					responseJSON.put("pmf", device.getPmfkey());
					responseJSON.put("status", device.getStatus());
					
					DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
					df.setTimeZone(TimeZone.getTimeZone("UTC"));
					Date date = new Date(device.getLastUpdatedTime()); 
					System.out.println("Wrong date time value: " + date);
					System.out.println("Correct date time value: " + df.format(date));
					
					responseJSON.put("time", date);
					responseJSON.put("lat" , device.getLatitude());
					responseJSON.put("longi" , device.getLongitude());
					buffer.append(responseJSON.toString());
					buffer.append(",");
					
			} catch (JSONException e) {
				e.printStackTrace();
				buffer.append("ERROR");
			}
		}
		StringBuffer resString = new StringBuffer(); 
		if(buffer.toString().contains(",")) {
			resString = new StringBuffer(buffer.toString().substring(0, buffer.toString().length() -1));
		} else {
			resString = new StringBuffer(buffer.toString());
		}
		resString.append("]");
		
		return resString.toString();
	}

}
