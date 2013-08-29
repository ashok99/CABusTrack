package com.ashok.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ashok.location.store.CacheManager;
import com.ashok.location.store.DataStore;
import com.ashok.location.store.escort.EscortDataStore;



@Path("/escort")
public class EscortModeService {
	
	
	@GET
	@Path("{latitude}/{longitude}/{device}/{pmf}/{timestamp}/{status}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addDevice(@PathParam("latitude") String pLatitude,
			@PathParam("longitude") String pLongitude,
			@PathParam("route") String pRoute,
			@PathParam("pmf") String pmfKey,
			@PathParam("timestamp") String pTimestamp,
			@PathParam("status") String pStatus) {
		     
		     CacheManager.addDevice(new EscortDataStore(new Double(pLatitude), new Double(pLongitude), pmfKey,"", new Long(pTimestamp), pStatus));
		     return Response.status(200).entity("Device Addedd Successfully").build();
	}
	
	
	/**
	 * Get all devices
	 * @return
	 */
	@GET
	public Response getAllDevices() {
		return Response.status(200)
				.entity(CacheManager.getCacheManager().getJSONDeviceData())
				.build();
	}
	
	
	@GET
	@Path("{pmf}/{action}")
	public Response removeDevice(@PathParam("pmf") String pmf, @PathParam("action") String action) {
		if (action.equalsIgnoreCase("REMOVE")) {
			CacheManager.removeDevice(pmf);
		}
		
		return Response.status(200).entity("removed device sucessfully").build();
	}

	
}
