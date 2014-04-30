package edu.asu.spring.mining.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "domain")
public interface IDomain {

	public String getDomains();

	public void setDomains(String domains);

	public String getId();

	public void setId(String id);

}