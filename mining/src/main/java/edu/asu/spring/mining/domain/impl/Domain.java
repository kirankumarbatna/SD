package edu.asu.spring.mining.domain.impl;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import edu.asu.spring.mining.domain.IDomain;

@Document(collection = "domain")
public class Domain implements IDomain {

	@Id
	private String id;
	private String domains;

	public String getDomains() {
		return domains;
	}

	public void setDomains(String domains) {
		this.domains = domains;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {

		return (((Domain) obj).getDomains().equals(this.domains));

	}

	@Override
	public int hashCode() {
		return domains.hashCode();
	}

}