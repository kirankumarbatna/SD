package edu.asu.spring.mining.mongo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import edu.asu.spring.mining.domain.IDomain;
import edu.asu.spring.mining.mongo.service.IDBManagerDomain;

public class DBManagerDomain implements IDBManagerDomain {

	private static final Logger logger = LoggerFactory
			.getLogger(DBManagerDomain.class);

	@Autowired
	private InterfaceImplFinder implFinder;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public int addDomain(IDomain domain) {
		if (domain == null)
			return FAILURE;

		try {
			if (!mongoTemplate.collectionExists(IDomain.class)) {
				mongoTemplate.createCollection(IDomain.class);
			}
			mongoTemplate.insert(domain, COLLECTION_NAME);
			return SUCCESS;
		} catch (Exception e) {
			logger.info("Exception in adding a domain", e);
			return FAILURE;
		}

	}

	@Override
	public List<IDomain> getAllDomain() {
		try {
			return mongoTemplate.findAll(IDomain.class, COLLECTION_NAME);
		} catch (Exception e) {
			logger.info("Exception in retrieving all Domain", e);
			return null;

		}
	}

	@Override
	public int deleteDomain(IDomain domain) {
		if (domain == null)
			return FAILURE;

		try {
			mongoTemplate.remove(domain, COLLECTION_NAME);
			return SUCCESS;
		} catch (Exception e) {
			logger.info("Exception in deleting a domain", e);

			return FAILURE;
		}
	}

	@Override
	public IDomain getDomain(String domainid) {
		IDomain domain = null;
		try {
			domain = mongoTemplate.findById(domainid, IDomain.class,
					COLLECTION_NAME);
			if (domain == null) {
				logger.info("domain not found");
			}
			return domain;
		} catch (Exception e) {

			logger.debug("Exception in retrieving a domain", e);
			return null;
		}

	}

}