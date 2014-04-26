package edu.asu.spring.mining.web.tenantmanagement;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.asu.spring.mining.service.requirement.IRequirementManager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DeleteRequirementsController {

	@Autowired
	IRequirementManager requirementManager;

	private static final Logger logger = LoggerFactory
			.getLogger(DeleteRequirementsController.class);

	@RequestMapping(value = "/auth/tenant/deleterequirement", method = RequestMethod.POST)
	public String deleteInstance(HttpServletRequest req, ModelMap model,
			Principal principal) {

		String[] values = req.getParameterValues("selected");
		try {
			if (values != null) {
				for (String val : values) {
					logger.info(" selected project to be delete : " + val);

					// call method for delete project with dataset.
					requirementManager.deleteUser(val);
				}
			}
		} catch (Exception e) {
			logger.error("DB Error :", e);
		}

		return "redirect:/auth/tenant/showrequirements";
	}

}
