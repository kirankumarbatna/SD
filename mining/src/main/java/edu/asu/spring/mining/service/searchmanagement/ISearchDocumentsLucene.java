package edu.asu.spring.mining.service.searchmanagement;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.queryParser.ParseException;

import edu.asu.spring.mining.domain.impl.SearchResultDoc;

public interface ISearchDocumentsLucene 
{
	public abstract ArrayList<SearchResultDoc> findDocumentsBasedOnKeywords(String searchKeywords) throws IOException, ParseException;

	public abstract ArrayList<SearchResultDoc> findDocumentsBasedOnKeywordsInComponents(
			String searchKeywords) throws IOException, ParseException;
}
