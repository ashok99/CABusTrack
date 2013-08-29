package com.ashok.rest;

import java.sql.Timestamp;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.ashok.model.RegistryInfo;
import com.ashok.util.HibernateUtil;

@Path("/updateRegInfo")
public class RegistryDataManager {
	@GET
	@Path("{regId}/{name}/{email}")
	public Response saveRegistry(@PathParam("regId") String regId,
			@PathParam("name") String name, @PathParam("email") String email) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		RegistryInfo regInfo = new RegistryInfo();
		session.beginTransaction();
		regInfo.setGcm_regid(regId);
		regInfo.setName(name);
		regInfo.setEmail(email);
		regInfo.setCreatedat(new Timestamp(new java.util.Date().getTime()));
		session.save(regInfo);
		session.getTransaction().commit();
		session.close();
		return Response.status(200).entity("Success").build();

	}
	

	@GET
	@Path("/fetchRegInfo")
	public Response fetchRegistry() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		//session.get
		Criteria criteria = session.createCriteria(RegistryInfo.class);
		List<RegistryInfo> criteriaList = criteria.list();
		for(RegistryInfo regInfo: criteriaList){
			System.out.println(regInfo.getName());
			System.out.println(regInfo.getEmail());
			System.out.println(regInfo.getGcm_regid());
		}
		session.getTransaction().commit();
		session.close();
		return Response.status(200).entity("Success").build();

	}
	
	
	
	
}
