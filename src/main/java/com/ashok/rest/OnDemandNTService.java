package com.ashok.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import com.ashok.location.strore.CacheManager;
import com.ashok.location.strore.DataStore;

@Path("/onDemand")
public class OnDemandNTService {
	
	@GET
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
	
	
	@GET
	@Path("{network}")
	public Response addOnDemandNT(@PathParam("network") String network) {
		CacheManager.getCacheManager().addOnDemandNetwork(network);
		return Response.status(200).entity("Success").build();
	}

}
