package com.ashok.location.strore;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.List;
import java.util.ArrayList;

import com.ashok.location.strore.DataStore;

public class CacheManager {
	private static CacheManager inst = null;//new CacheManager();
	private static Map<String, DataStore> routeInformation;
	private static List fixedRouteList = null;
	private static List<String> onDemandNetworkList = new ArrayList<String>();

	static {
		routeInformation = new HashMap<String, DataStore>();
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
	
	
	public void addOnDemandNetwork(String network) {
		if(!onDemandNetworkList.contains(network)) {
			onDemandNetworkList.add(network);
		}
	}

	public void updateLocationInfo(DataStore pInfo) {
		if (routeInformation == null)
			routeInformation = new HashMap<String, DataStore>();

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
		if (fixedRouteList.contains(pRoute) || getOnDemandNetworks().contains(pRoute))
			return true;
		return false;
	}

	public List<String> getOnDemandNetworks() {
		return onDemandNetworkList;
	}

}
