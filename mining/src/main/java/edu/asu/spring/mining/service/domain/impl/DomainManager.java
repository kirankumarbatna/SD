package edu.asu.spring.mining.service.domain.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.spring.mining.domain.IDomain;
import edu.asu.spring.mining.mongo.service.IDBManagerDomain;
import edu.asu.spring.mining.service.domain.IDomainManager;

@Service
public class DomainManager implements IDomainManager {

	@Autowired
	IDBManagerDomain dbManagerDomain;

	@Override
	public int insertDomain(IDomain domain) {
		return (dbManagerDomain.addDomain(domain));
	}

	@Override
	public List<IDomain> getAllDomain() {
		return dbManagerDomain.getAllDomain();
	}

	@Override
	public int deleteUser(String domainid) {
		IDomain domain = dbManagerDomain.getDomain(domainid);
		return (dbManagerDomain.deleteDomain(domain));

	}

	@Override
	public IDomain findbyDomainName(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}