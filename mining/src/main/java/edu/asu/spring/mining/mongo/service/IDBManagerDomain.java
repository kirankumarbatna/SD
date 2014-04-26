package edu.asu.spring.mining.mongo.service;

import java.util.List;

import edu.asu.spring.mining.domain.IDomain;
import edu.asu.spring.mining.domain.IRequirement;

public interface IDBManagerDomain {

	public static final String COLLECTION_NAME = "domain";
	public final static int SUCCESS = 1;
	public final static int FAILURE = 0;

	public abstract int addDomain(IDomain domain) ;

	public abstract List<IDomain> getAllDomain();

	public abstract int deleteDomain(IDomain user) ;

	IDomain getDomain(String domainid);



	

}