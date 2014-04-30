package edu.asu.spring.mining.web.saasprovidermanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.asu.spring.mining.service.component.IComponentManager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ShowComponentsController {

	@Autowired
	IComponentManager componentManager;

	private static final Logger logger = LoggerFactory
			.getLogger(ShowComponentsController.class);

	@RequestMapping(value = "/auth/component/showcomponents", method = RequestMethod.GET)
	public String login(ModelMap model) {

		
		
		//System.out.println(requirementManager.getAllRequirement().size());
		
		model.addAttribute("components", componentManager.getAllComponent());
		return "showcomponents";

	}

}
