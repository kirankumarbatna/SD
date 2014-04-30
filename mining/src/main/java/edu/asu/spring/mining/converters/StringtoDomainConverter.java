package edu.asu.spring.mining.converters;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.DBObject;

import edu.asu.spring.mining.HomeController;
import edu.asu.spring.mining.domain.IDomain;
import edu.asu.spring.mining.domain.impl.Domain;
import edu.asu.spring.mining.domain.impl.Requirement;
import edu.asu.spring.mining.service.domain.impl.DomainManager;

/**
 * 
 * @author : Bhargav Desai
 *
 */
public class StringtoDomainConverter implements Converter<String, IDomain> {
	
	
	@Autowired
	DomainManager domainManager;
	
	@Override
	public IDomain convert(String arg0) {
			
		
		
		IDomain domain =  domainManager.findbyDomainName(arg0);
		if(domain==null){
			IDomain new_domain = new Domain();
			new_domain.setDomains(arg0);
			domainManager.insertDomain(new_domain);
			return new_domain;
		}
		else{
			return domain;
		}
		
		
	}

}
