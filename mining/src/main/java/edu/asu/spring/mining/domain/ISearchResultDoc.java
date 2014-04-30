package edu.asu.spring.mining.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "searchResultDocument")
public interface ISearchResultDoc 
{
	public String getDocumentName();
	public void setDocumentName(String docName);
	
	public String getName();
	public void setName(String name);
	
	public String getDescription();
	public void setDescription(String description);
}
