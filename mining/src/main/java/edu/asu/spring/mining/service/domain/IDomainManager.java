package edu.asu.spring.mining.service.domain;

import java.util.List;

import edu.asu.spring.mining.domain.IDomain;


public interface IDomainManager {


public abstract int insertDomain(IDomain domain) ;
	
	public abstract List<IDomain> getAllDomain();

	public abstract int deleteUser(String domainid);

	IDomain findbyDomainName(String arg0);
	
	
}