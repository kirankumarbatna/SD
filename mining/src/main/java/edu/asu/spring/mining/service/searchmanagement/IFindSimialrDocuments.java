package edu.asu.spring.mining.service.searchmanagement;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import edu.asu.spring.mining.domain.impl.Document;

public interface IFindSimialrDocuments {

	
	public abstract LinkedHashMap<Integer, Document> findSimialrRequirementsBasedOnKeywords(String keyword,Map<Integer, Document> docMaps);
		
	
}
