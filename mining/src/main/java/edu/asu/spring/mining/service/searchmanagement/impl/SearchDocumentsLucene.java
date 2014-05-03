package edu.asu.spring.mining.service.searchmanagement.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.spring.mining.domain.IComponent;
import edu.asu.spring.mining.domain.IDomain;
import edu.asu.spring.mining.domain.IRequirement;
import edu.asu.spring.mining.domain.impl.SearchResultDoc;
import edu.asu.spring.mining.mongo.service.IDBManagerComponent;
import edu.asu.spring.mining.mongo.service.IDBManagerRequirement;
import edu.asu.spring.mining.service.searchmanagement.ISearchDocumentsLucene;

@Service
public class SearchDocumentsLucene implements ISearchDocumentsLucene {
	public static final String FIELD_CONTENTS = "contents";
	public static final String FIELD_PATH = "path";
	public static final int MAX_RESULTS_NEEDED = 100;

	@Autowired
	private IDBManagerRequirement dbManager;

	@Autowired
	private IDBManagerComponent dbManagerComponent;

	@Override
	public ArrayList<SearchResultDoc> findDocumentsBasedOnKeywords(
			String searchKeywords) throws IOException, ParseException {
		Properties pObj = new Properties();
		pObj.load(SearchDocumentsLucene.class.getClassLoader()
				.getResourceAsStream("storage.properties"));
		String rootPath = (String) pObj.get("storagepath");
		String indexPath = rootPath.concat("/index/requirement_index");
		System.out.println("Searching for the keywords : " + searchKeywords);
		Directory dirObj = FSDirectory.open(new File(indexPath));
		IndexReader indexReaderObj = IndexReader.open(dirObj);
		IndexSearcher indexSearcherObj = new IndexSearcher(indexReaderObj);

		Version currentVersion = Version.LUCENE_36;
		Analyzer analyzer = new StandardAnalyzer(currentVersion);
		QueryParser qParser = new QueryParser(currentVersion, FIELD_CONTENTS,
				analyzer);
		Query searchQuery = qParser.parse(searchKeywords);
		TopDocs searchTopResults = indexSearcherObj.search(searchQuery,
				MAX_RESULTS_NEEDED);
		System.out.println("Total hits: " + searchTopResults.totalHits);
		ScoreDoc[] documentHits = searchTopResults.scoreDocs;
		System.out.println("------- Search Results -------");
		ArrayList<SearchResultDoc> allDocs = new ArrayList<SearchResultDoc>();
		HashSet<String> allUniqueDocs = new HashSet<String>();
		for (ScoreDoc hit : documentHits) {
			Document doc = indexSearcherObj.doc(hit.doc);
			allUniqueDocs.add(doc.get(FIELD_PATH));
		}

		for (String name : allUniqueDocs) {

			IRequirement requirement = dbManager.getRequirementbyFileName(name
					.substring(1));
			if (requirement != null) {
				// System.out.println(requirement.getName() + "   :name");
				// System.out.println(name.substring(1)+" *hello* "+requirement.getFilename());
				SearchResultDoc resultDoc = new SearchResultDoc();
				resultDoc.setDocumentName(name.substring(1));
				resultDoc.setName(requirement.getName());
				resultDoc.setDescription(requirement.getDescription());
				allDocs.add(resultDoc);
				System.out.println("Document name: " + name);
			}
		}
		indexSearcherObj.close();
		return allDocs;
	}

	@Override
	public ArrayList<SearchResultDoc> findDocumentsBasedOnKeywordsInComponents(
			String searchKeywords) throws IOException, ParseException {
		Properties pObj = new Properties();
		pObj.load(SearchDocumentsLucene.class.getClassLoader()
				.getResourceAsStream("storage.properties"));
		String rootPath = (String) pObj.get("storagepath");
		String indexPath = rootPath.concat("/index/component_index");
		System.out.println("Searching for the keywords : " + searchKeywords);
		Directory dirObj = FSDirectory.open(new File(indexPath));
		IndexReader indexReaderObj = IndexReader.open(dirObj);
		IndexSearcher indexSearcherObj = new IndexSearcher(indexReaderObj);

		Version currentVersion = Version.LUCENE_36;
		Analyzer analyzer = new StandardAnalyzer(currentVersion);
		QueryParser qParser = new QueryParser(currentVersion, FIELD_CONTENTS,
				analyzer);
		Query searchQuery = qParser.parse(searchKeywords);
		TopDocs searchTopResults = indexSearcherObj.search(searchQuery,
				MAX_RESULTS_NEEDED);
		System.out.println("Total hits: " + searchTopResults.totalHits);
		ScoreDoc[] documentHits = searchTopResults.scoreDocs;
		System.out.println("------- Search Results -------");
		ArrayList<SearchResultDoc> allDocs = new ArrayList<SearchResultDoc>();
		HashSet<String> allUniqueDocs = new HashSet<String>();
		for (ScoreDoc hit : documentHits) {
			Document doc = indexSearcherObj.doc(hit.doc);
			allUniqueDocs.add(doc.get(FIELD_PATH));
		}

		for (String name : allUniqueDocs) {
			IComponent comp = dbManagerComponent.getComponentbyFileName(name
					.substring(1));
			System.out.println(comp.getName() + "   :name");
			SearchResultDoc resultDoc = new SearchResultDoc();
			resultDoc.setDocumentName(comp.getFilename());
			resultDoc.setName(comp.getName());
			resultDoc.setComponentDomain(comp.getDomain());
			resultDoc.setUrl(comp.getUrl());
			allDocs.add(resultDoc);
			System.out.println("Document name: " + name);
		}
		indexSearcherObj.close();
		return allDocs;
	}
}
