package edu.asu.spring.mining.web.tenantmanagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Properties;

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

import edu.asu.spring.mining.domain.impl.Requirement;
import edu.asu.spring.mining.service.index.IIndexManager;
import edu.asu.spring.mining.service.requirement.IRequirementManager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AddRequirementsController {

	@Autowired
	IRequirementManager requirementManager;
	
	@Autowired
	IIndexManager indexManager;

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

			Properties p = new Properties();
			p.load(AddRequirementsController.class.getClassLoader()
					.getResourceAsStream("storage.properties"));

			String parent = (String) p.get("storagepath");

			File f = new File(parent + "/index/requirement_index");

			if (!f.exists()) {
				f.mkdirs();
			}

			f = new File(parent + "/new_requirement");

			if (!f.exists()) {
				f.mkdirs();
			}

			File rf = new File(parent + "/new_requirement/"
					+ mpf.getOriginalFilename());
			mpf.transferTo(rf);

			requirement.setFilename(mpf.getOriginalFilename());
			requirement.setFile(null);

		}

		requirementManager.insertRequirement(requirement);
		indexManager.indexRequirementFiles();

		return "auth/home";

	}

}
