package edu.asu.spring.mining.mongo.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import edu.asu.spring.mining.domain.IComponent;
import edu.asu.spring.mining.domain.IDomain;
import edu.asu.spring.mining.domain.IRequirement;
import edu.asu.spring.mining.domain.impl.Component;
import edu.asu.spring.mining.domain.impl.Domain;
import edu.asu.spring.mining.domain.impl.Requirement;


@Service
public class InterfaceImplFinder {

	public static Map<Class<?>,Class<?>> interfaceMap = new HashMap<Class<?>, Class<?>>();
	
	/**
	 * Initializes the interfaceMap with hardcoded Interface and Implemented class
	 * Any new domain classes inserted into MongoDB need to be added here in this {@link PostConstruct}
	 */
	@PostConstruct
	public synchronized void init() {
		interfaceMap.put(IRequirement.class, Requirement.class);
		interfaceMap.put(IDomain.class, Domain.class);
		interfaceMap.put(IComponent.class, Component.class);
		
	}
	
	/**
	 * Getter of implemented class for an interface
	 * @param interFace			interface to query for implemented class
	 * @return					returns the implemented class of the interface
	 */
	public Class<?> getImplPackage(Class<?> interFace){
		return interfaceMap.get(interFace);
	}

	
}
