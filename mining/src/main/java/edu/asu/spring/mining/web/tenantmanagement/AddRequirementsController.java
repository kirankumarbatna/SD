package edu.asu.spring.mining.web.tenantmanagement;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import edu.asu.spring.herckules.service.user.IRequirementManager;
import edu.asu.spring.mining.domain.impl.Requirement;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AddRequirementsController {

	@Autowired
	IRequirementManager requirementManager;

	private static final Logger logger = LoggerFactory
			.getLogger(AddRequirementsController.class);

	@RequestMapping(value = "/auth/tenant/addrequirement", method = RequestMethod.GET)
	public String login(ModelMap model) {

		model.addAttribute("requirement", new Requirement());
		return "addrequirements";

	}

	@RequestMapping(value = "/auth/tenant/addrequirement", method = RequestMethod.POST)
	public String addRequirement(ModelMap model,
			@ModelAttribute("requirement") Requirement requirement,
			@RequestParam("keyword") String keyword,
			@RequestParam("name") String price,
			@RequestParam("description") String description,
			MultipartHttpServletRequest request) throws IllegalStateException,
			IOException {

		MultipartFile mpf = null;
		Iterator<String> itr = request.getFileNames();

	
		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());

			String classPath = URLDecoder.decode(AddRequirementsController.class
					.getProtectionDomain().getCodeSource().getLocation().getPath(),
					"UTF-8");
			String path = classPath.substring(0, classPath.indexOf("classes"))
					+ "classes/" + mpf.getOriginalFilename();

			System.out.println(path);
			
			File f = new File(path);
			mpf.transferTo(f);

		requirement.setFilename(mpf.getName());	
		requirement.setFile(null);	
		
		}

		
		requirementManager.insertRequirement(requirement);

		return "auth/home";

	}

}
