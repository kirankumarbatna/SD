package edu.asu.spring.herckules.mongo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import edu.asu.spring.herckules.mongo.service.IDBManagerRequirement;
import edu.asu.spring.mining.domain.IRequirement;

@Repository
public class DBManagerRequirement implements IDBManagerRequirement {

	private static final Logger logger = LoggerFactory
			.getLogger(DBManagerRequirement.class);

	@Autowired
	private InterfaceImplFinder implFinder;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public IRequirement getRequirement(String requirementid) 
	{
		IRequirement requirement = null;
		try
		{
			requirement= mongoTemplate.findById(requirementid, IRequirement.class, COLLECTION_NAME);
			if(requirement == null){
				logger.info("requirement not found");
			}
			return requirement;
		}
		catch(Exception e)
		{
			
			logger.debug("Exception in retrieving a user",e);
			return null;
		}
	}

	
	
	@Override
	public int addRequirement(IRequirement requirement) {
		if (requirement == null)
			return FAILURE;

		try {
			if (!mongoTemplate.collectionExists(IRequirement.class)) {
				mongoTemplate.createCollection(IRequirement.class);
			}
			mongoTemplate.insert(requirement, COLLECTION_NAME);
			return SUCCESS;
		} catch (Exception e) {
			logger.info("Exception in adding a Requirement", e);
			return FAILURE;
		}
	}

	@Override
	public List<IRequirement> getAllRequirement() {
		try {
			return mongoTemplate.findAll(IRequirement.class, COLLECTION_NAME);
		} catch (Exception e) {
			logger.info("Exception in retrieving all Requirements", e);
			return null;

		}
	}

	@Override
	public int deleteRequirement(IRequirement Requirement) {
		if (Requirement == null)
			return FAILURE;

		try {
			mongoTemplate.remove(Requirement, COLLECTION_NAME);
			return SUCCESS;
		} catch (Exception e) {
			logger.info("Exception in deleting a Requirement", e);

			return FAILURE;
		}
	}

}