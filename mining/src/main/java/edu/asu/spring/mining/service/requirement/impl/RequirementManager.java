package edu.asu.spring.mining.service.requirement.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.spring.mining.domain.IRequirement;
import edu.asu.spring.mining.mongo.service.IDBManagerRequirement;
import edu.asu.spring.mining.service.requirement.IRequirementManager;

@Service
public class RequirementManager implements IRequirementManager {

	@Autowired
	private IDBManagerRequirement dbmanager;

	@Override
	public int insertRequirement(IRequirement requirement) {

		return (dbmanager.addRequirement(requirement));
	}

	@Override
	public List<IRequirement> getAllRequirement() {
		return dbmanager.getAllRequirement();
	}

	@Override
	public int deleteUser(String requirement) {

		IRequirement req = dbmanager.getRequirement(requirement);

		if (req != null) {
			return (dbmanager.deleteRequirement(req));
		} else
			return 0;

	}

}
