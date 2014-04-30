package edu.asu.spring.mining.web.saasprovidermanagement;

import java.io.File;
import java.io.IOException;
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

import edu.asu.spring.mining.domain.IComponent;
import edu.asu.spring.mining.domain.IDomain;
import edu.asu.spring.mining.domain.impl.Component;
import edu.asu.spring.mining.domain.impl.Domain;
import edu.asu.spring.mining.service.component.IComponentManager;
import edu.asu.spring.mining.service.domain.IDomainManager;
import edu.asu.spring.mining.service.index.IIndexManager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AddComponentController {

	@Autowired
	IComponentManager componentManager;
	
	@Autowired
	IDomainManager domainManager;
	
	
	@Autowired
	IIndexManager indexManager;

	private static final Logger logger = LoggerFactory
			.getLogger(AddComponentController.class);

	@RequestMapping(value = "/auth/component/addcomponent", method = RequestMethod.GET)
	public String login(ModelMap model) {

		
		IComponent c = new Component();
		model.addAttribute("component", c);
//		
//		IDomain d1 = new Domain();
//		d1.setDomains("news");
		
		//domainManager.insertDomain(d1);
		
		model.addAttribute("domains", domainManager.getAllDomain());
		
		
		return "addcomponents";

	}

	@RequestMapping(value = "/auth/component/addcomponent", method = RequestMethod.POST)
	public String addRequirement(ModelMap model,
			@ModelAttribute Component component,
			MultipartHttpServletRequest request) throws IllegalStateException,
			IOException {

		MultipartFile mpf = null;
		Iterator<String> itr = request.getFileNames();

		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());

			Properties p = new Properties();
			p.load(AddComponentController.class.getClassLoader()
					.getResourceAsStream("storage.properties"));

			String parent = (String) p.get("storagepath");

			File f = new File(parent + "/index/component_index");

			if (!f.exists()) {
				f.mkdirs();
			}

			f = new File(parent + "/new_component");

			if (!f.exists()) {
				f.mkdirs();
			}

			File rf = new File(parent + "/new_component/"
					+ mpf.getOriginalFilename());
			mpf.transferTo(rf);

			component.setFilename(mpf.getOriginalFilename());
			component.setFile(null);

		}

		componentManager.insertComponent(component);
		indexManager.indexComponentFiles();

		return "auth/home";

	}

}
