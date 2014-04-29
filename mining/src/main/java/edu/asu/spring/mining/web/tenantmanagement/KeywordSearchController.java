package edu.asu.spring.mining.web.tenantmanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class KeywordSearchController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(KeywordSearchController.class);

	@RequestMapping(value = "/auth/keywordsearchform", method = RequestMethod.GET)
	public String showsearchForm(ModelMap model) {

		return "keywordsearch";

	}
	
	@RequestMapping(value = "/auth/keywordsearch", method = RequestMethod.POST)
	public String keywordsearch(ModelMap model) {

		return "";

	}

}
