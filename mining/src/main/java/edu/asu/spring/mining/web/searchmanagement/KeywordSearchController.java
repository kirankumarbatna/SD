package edu.asu.spring.mining.web.searchmanagement;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.queryParser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.asu.spring.mining.domain.impl.SearchResultDoc;
import edu.asu.spring.mining.service.searchmanagement.ISearchDocumentsLucene;

@Controller
public class KeywordSearchController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(KeywordSearchController.class);
	
	/*@Autowired
	private IFindSimialrDocuments findSimDocs;*/
	
	@Autowired
	private ISearchDocumentsLucene findSimDocs;
	
	//private IComputeDocumentTermFrequencyManager computeDocTermFreqManager;
	

	@RequestMapping(value = "/auth/keywordsearchform", method = RequestMethod.GET)
	public String showsearchForm(ModelMap model) {

		return "keywordsearch";

	}
	
	@RequestMapping(value = "/auth/keywordsearch", method = RequestMethod.POST)
	public String keywordsearch(ModelMap model,@RequestParam("keyword") String keyword,@RequestParam("searchType") String searchType) throws IOException, ParseException {
		// Sowjanya 
		/*Map<Integer, Document> docMaps = computeDocTermFreqManager.computeDocumentTermFreqeuncies();
		LinkedHashMap<Integer, Document> similarDocs = findSimDocs.findSimialrRequirementsBasedOnKeywords(keyword, docMaps);*/
		  //model.addAttribute("documents", );
		if(searchType.equals("requirement") && keyword!=null && keyword.length()>0)
		{
			ArrayList<SearchResultDoc> resultDocs = findSimDocs.findDocumentsBasedOnKeywords(keyword);
			model.addAttribute("keywordSearchResults", resultDocs);
		}
		else if(searchType.equals("component") && keyword!=null && keyword.length()>0)
		{
			ArrayList<SearchResultDoc> resultDocs = findSimDocs.findDocumentsBasedOnKeywordsInComponents(keyword);
			model.addAttribute("keywordSearchResults", resultDocs);
		}
		model.addAttribute("searchType", searchType);
		return "keywordsearchResults";
	}
	
	/*@RequestMapping(value = "/auth/component/keywordsearch", method = RequestMethod.POST)
	public String componentKeywordsearch(ModelMap model,@RequestParam("keyword") String keyword) throws IOException, ParseException {
		
		if(keyword!=null&&keyword.length()>0)
		{
			ArrayList<SearchResultDoc> resultDocs = findSimDocs.findDocumentsBasedOnKeywordsInComponents(keyword);
			model.addAttribute("keywordSearchResults", resultDocs);
		}
		return "keywordsearchResults";
	}*/

}
