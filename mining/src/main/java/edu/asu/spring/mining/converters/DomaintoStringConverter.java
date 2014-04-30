package edu.asu.spring.mining.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import edu.asu.spring.mining.domain.IDomain;
import edu.asu.spring.mining.service.domain.impl.DomainManager;

/**
 * 
 * @author : Bhargav Desai
 *
 */
public class DomaintoStringConverter implements Converter<IDomain, String> {
	
	
	@Autowired
	DomainManager domainManager;
	
	@Override
	public String convert(IDomain arg0) {
			
		
		
		return arg0.getDomains();
		
		
	}

}
