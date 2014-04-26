package edu.asu.spring.mining.web.tenantmanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.asu.spring.mining.domain.impl.Requirement;
import edu.asu.spring.mining.service.requirement.IRequirementManager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ShowRequirementsController {

	@Autowired
	IRequirementManager requirementManager;

	private static final Logger logger = LoggerFactory
			.getLogger(ShowRequirementsController.class);

	@RequestMapping(value = "/auth/tenant/showrequirements", method = RequestMethod.GET)
	public String login(ModelMap model) {

		
		
		//System.out.println(requirementManager.getAllRequirement().size());
		
		model.addAttribute("requirements", requirementManager.getAllRequirement());
		return "showrequirements";

	}

}
