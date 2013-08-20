package com.ashok.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.ashok.location.strore.CacheManager;
import com.ashok.location.strore.DataStore;

/**
 * 
 * @author ITHAS01
 * 
 */
@Path("/getRouteInfo")
public class LocationRetrievalService {

	@GET
	@Path("{routeKey}")
	public Response getRouteInfo(@PathParam("routeKey") String rKey) {
		StringBuffer result = null;
		if (rKey.equalsIgnoreCase("ALL")) {
			String[] routes;
			StringBuffer buf = new StringBuffer();
			String allRoutes = CacheManager.getCacheManager()
					.getAllAvailableRoutes();

			if (allRoutes != null && !allRoutes.isEmpty()) {
				allRoutes = allRoutes.trim();
			}

			routes = allRoutes.split(",");
			if (routes != null && routes.length > 0) {
				for (String routeSSID : routes) {
					DataStore details = CacheManager.getCacheManager()
							.getStoreForRoute(routeSSID);
					if (details != null) {
						result = new StringBuffer("LAT=");
						result.append(details.getLatitude().toString());
						result.append("LONG=");
						result.append(details.getLongitude().toString());
						buf.append(result.toString());
					}
				}

				result = buf;
			}

		} else {
			DataStore details = CacheManager.getCacheManager()
					.getStoreForRoute(rKey);
			if (details != null) {
				result = new StringBuffer("");
				// Return JSON here
				result.append(details.getLatitude().toString());
				result.append(":");
				result.append(details.getLongitude().toString());
				result.append(":");
				result.append(details.getLastUpdatedTime());
			} else {
				result = new StringBuffer("Route info not available");
			}
		}

		return Response.status(200).entity(result.toString()).build();

	}

	@GET
	public Response getRouteInfo() {
		return Response.status(200)
				.entity(CacheManager.getCacheManager().getAllAvailableRoutes())
				.build();

	}

	/*@GET
	public Response getAllOnDemandNetworks() {
		List<String> onDemandNetworks = CacheManager.getCacheManager()
				.getOnDemandNetworks();
		if (onDemandNetworks != null && onDemandNetworks.size() > 0) {
			StringBuffer buffer = new StringBuffer();
			for (String networkName : onDemandNetworks) {
				buffer.append(networkName + ",");
			}
			return Response.status(200).entity(buffer.toString()).build();
		}
		return Response.status(200).entity("No OnDemand Networks found")
				.build();
	}
*/
}
