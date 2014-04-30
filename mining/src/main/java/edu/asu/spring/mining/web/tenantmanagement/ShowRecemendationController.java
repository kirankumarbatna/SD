package edu.asu.spring.mining.web.tenantmanagement;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import edu.asu.spring.mining.domain.IComponent;
import edu.asu.spring.mining.domain.IRequirement;
import edu.asu.spring.mining.domain.impl.Requirement;
import edu.asu.spring.mining.mongo.service.IDBManagerRequirement;
import edu.asu.spring.mining.service.component.IComponentManager;
import edu.asu.spring.mining.service.requirement.IRequirementManager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ShowRecemendationController {

	@Autowired
	IRequirementManager requirementManager;

	@Autowired
	IDBManagerRequirement dbManagerRequirement;

	@Autowired
	IComponentManager componentManager;

	
	private static final Logger logger = LoggerFactory
			.getLogger(ShowRecemendationController.class);

	@RequestMapping(value = "/auth/requirement/showrecommendations/{id}", method = RequestMethod.GET)
	public String addRequirement(ModelMap model,
			@ModelAttribute("requirement") Requirement requirement,
			@PathVariable("id") String id)
			throws Exception {

		IRequirement req = dbManagerRequirement.getRequirement(id);

		String domain = req.getDomain();

		
		
		List<IComponent> rList = new ArrayList<IComponent>(); 
		List<IComponent> cList = componentManager.getAllComponent();
		
		for(IComponent c: cList){
			String domains = c.getDomain();
			String[] domainarray = domains.split(",");
			
			for(int i=0;i<domainarray.length;i++){
				if(domainarray[i].equals(domain)){
					rList.add(c);
				}
			}
		}
		
		
		model.addAttribute("rec",rList);
		return "auth/tenant/showsuggestions";

	}

}
