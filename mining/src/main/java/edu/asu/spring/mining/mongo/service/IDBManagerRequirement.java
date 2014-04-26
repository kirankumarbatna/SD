package edu.asu.spring.mining.mongo.service;

import java.util.List;

import edu.asu.spring.mining.domain.IRequirement;

public interface IDBManagerRequirement {

	public static final String COLLECTION_NAME = "requirement";
	public final static int SUCCESS = 1;
	public final static int FAILURE = 0;

	public abstract int addRequirement(IRequirement user) ;

	public abstract List<IRequirement> getAllRequirement();

	public abstract int deleteRequirement(IRequirement user) ;

	IRequirement getRequirement(String requirementid);



	

}