package edu.asu.spring.mining.domain.impl;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import edu.asu.spring.mining.domain.ISearchResultDoc;

@Document(collection = "searchResultDocument")
@Service
public class SearchResultDoc implements ISearchResultDoc {

	private String documentName;
	private String name;
	private String description;
	
	@Override
	public String getDocumentName() {
		return this.documentName;
	}

	@Override
	public void setDocumentName(String docName) {
		this.documentName = docName;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
