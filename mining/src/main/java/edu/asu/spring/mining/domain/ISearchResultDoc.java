package edu.asu.spring.mining.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "searchResultDocument")
public interface ISearchResultDoc 
{
	public String getDocumentName();
	public void setDocumentName(String docName);
}
