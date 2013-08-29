package com.ashok.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.ashok.location.store.CacheManager;
import com.ashok.location.store.DataStore;

@Path("/updateLocation")
public class UpdateLocationService {

	@GET
	@Path("{latitude}/{longitude}/{route}/{uploaderName}/{timestamp}/")
	public Response updateLocation(@PathParam("latitude") String pLatitude,
			@PathParam("longitude") String pLongitude,
			@PathParam("route") String pRoute,
			@PathParam("uploaderName") String pUploaderName,
			@PathParam("timestamp") String pTimestamp) {

		CacheManager.getCacheManager().updateLocationInfo(
				parseDataStore(pLatitude, pLongitude, pRoute, pUploaderName,
						pTimestamp));
		return Response.status(200).entity("Success").build();

	}

	
	/**
	 * 
	 * @param pLatitude
	 * @param pLongitude
	 * @param pRoute
	 * @param pUploaderName
	 * @param pUploaderName2
	 * @param pTimestamp
	 */
	private DataStore parseDataStore(String pLatitude, String pLongitude,
			String pRoute, String pUploaderName, String pTimestamp) {
		long longTime;
		
		if(pTimestamp==null)
			longTime = System.currentTimeMillis();
		
		return new DataStore(new Double(Double.parseDouble(pLatitude)),
				new Double(Double.parseDouble(pLongitude)), pUploaderName,
				pRoute, Long.parseLong(pTimestamp));
		
		//Double latitude, 
		//Double longitude, //
		//String uploader,
		//String route,
		//String lastUpdatedTime
		
		

	}
}
