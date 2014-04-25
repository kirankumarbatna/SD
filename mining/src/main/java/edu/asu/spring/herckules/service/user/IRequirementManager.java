package edu.asu.spring.herckules.service.user;

import java.util.List;

import edu.asu.spring.mining.domain.IRequirement;

public interface IRequirementManager {

	public abstract int insertRequirement(IRequirement requirement) ;
	
	public abstract List<IRequirement> getAllRequirement();

	public abstract int deleteUser(String requirement);
}