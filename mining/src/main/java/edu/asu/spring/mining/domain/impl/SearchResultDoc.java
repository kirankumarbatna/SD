package edu.asu.spring.mining.domain.impl;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import edu.asu.spring.mining.domain.ISearchResultDoc;

@Document(collection = "searchResultDocument")
@Service
public class SearchResultDoc implements ISearchResultDoc {

	private String documentName;
	
	@Override
	public String getDocumentName() {
		return this.documentName;
	}

	@Override
	public void setDocumentName(String docName) {
		this.documentName = docName;
	}

}
