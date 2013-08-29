package com.ashok.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gcm_users")
public class RegistryInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGcm_regid() {
		return gcm_regid;
	}

	public void setGcm_regid(String gcm_regid) {
		this.gcm_regid = gcm_regid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCreatedat() {
		return createdat;
	}

	public void setCreatedat(Timestamp createdat) {
		this.createdat = createdat;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="gcm_regid")
	private String gcm_regid;
	@Column(name="name")
    private String name;
	@Column(name="created_at")
    private Timestamp createdat;
	@Column(name="email")
    private String email;
 
    
}
