package edu.asu.spring.mining.web.searchmanagement;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.asu.spring.mining.domain.impl.Document;
import edu.asu.spring.mining.service.index.IComputeDocumentTermFrequencyManager;
import edu.asu.spring.mining.service.searchmanagement.IFindSimialrDocuments;

@Controller
public class KeywordSearchController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(KeywordSearchController.class);
	
	@Autowired
	private IFindSimialrDocuments findSimDocs;
	
	private IComputeDocumentTermFrequencyManager computeDocTermFreqManager;
	

	@RequestMapping(value = "/auth/keywordsearchform", method = RequestMethod.GET)
	public String showsearchForm(ModelMap model) {

		return "keywordsearch";

	}
	
	@RequestMapping(value = "/auth/keywordsearch", method = RequestMethod.POST)
	public String keywordsearch(ModelMap model,@RequestParam("keyword") String keyword) {
		Map<Integer, Document> docMaps = computeDocTermFreqManager.computeDocumentTermFreqeuncies();
		LinkedHashMap<Integer, Document> similarDocs = findSimDocs.findSimialrRequirementsBasedOnKeywords(keyword, docMaps);
		//model.addAttribute("documents", );
		return "";

	}

}
