package edu.asu.spring.herckules.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.spring.herckules.mongo.service.IDBManagerRequirement;
import edu.asu.spring.herckules.service.user.IRequirementManager;
import edu.asu.spring.mining.domain.IRequirement;

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
