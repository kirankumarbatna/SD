package edu.asu.spring.mining.mongo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import edu.asu.spring.mining.domain.IComponent;
import edu.asu.spring.mining.domain.IDomain;
import edu.asu.spring.mining.mongo.service.IDBManagerComponent;

@Repository
public class DBManagerComponent implements IDBManagerComponent {

	private static final Logger logger = LoggerFactory
			.getLogger(DBManagerComponent.class);

	@Autowired
	private InterfaceImplFinder implFinder;

	@Autowired
	private MongoTemplate mongoTemplate;

	
	@Override
	public int addcomponent(IComponent component) {
		if (component == null)
			return FAILURE;

		try {
			if (!mongoTemplate.collectionExists(IComponent.class)) {
				mongoTemplate.createCollection(IComponent.class);
			}
			mongoTemplate.insert(component, COLLECTION_NAME);
			return SUCCESS;
		} catch (Exception e) {
			logger.info("Exception in adding a component", e);
			return FAILURE;
		}
		
	}

	@Override
	public List<IComponent> getAllcomponent() {
		try {
			return mongoTemplate.findAll(IComponent.class, COLLECTION_NAME);
		} catch (Exception e) {
			logger.info("Exception in retrieving all Component", e);
			return null;

		}
		
	}

	@Override
	public int deletecomponent(IComponent component) {
		if (component == null)
			return FAILURE;

		try {
			mongoTemplate.remove(component, COLLECTION_NAME);
			return SUCCESS;
		} catch (Exception e) {
			logger.info("Exception in deleting a component", e);

			return FAILURE;
		}
	}

	@Override
	public IComponent getcomponent(String componentid) {
		IComponent component = null;
		try {
			component = mongoTemplate.findById(componentid, IComponent.class,
					COLLECTION_NAME);
			if (component == null) {
				logger.info("domain not found");
			}
			return component;
		} catch (Exception e) {

			logger.debug("Exception in retrieving a component", e);
			return null;
		}
	}

	@Override
	public IComponent getComponentbyFileName(String filename)  {
		
		if(filename == null || filename.equals(""))
			return null;
		
		try
		{
			//Build the query
			Query query = Query.query(Criteria.where("filename").is(filename));
			
			//Find the target class for the interface.
			Class<?> targetClass = implFinder.getImplPackage(IComponent.class);
			
			if(targetClass!=null)
				return (IComponent) mongoTemplate.findOne(query, targetClass, COLLECTION_NAME);
			else
				return null;
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
			logger.debug("Exception in retrieving a domain",e);
			return null;
		}
	}


	

	

}