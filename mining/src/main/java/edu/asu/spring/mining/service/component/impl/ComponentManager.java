package edu.asu.spring.mining.service.component.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.spring.mining.domain.IComponent;
import edu.asu.spring.mining.mongo.service.impl.DBManagerComponent;
import edu.asu.spring.mining.service.component.IComponentManager;

@Service
public class ComponentManager implements IComponentManager {

	@Autowired
	DBManagerComponent dbManagerComponent;
	
	@Override
	public int insertComponent(IComponent component) {
		
	return dbManagerComponent.addcomponent(component);
	}

	@Override
	public List<IComponent> getAllComponent() {
		return dbManagerComponent.getAllcomponent();
	}

	@Override
	public int deleteUser(String componentid) {
		
		IComponent c = dbManagerComponent.getcomponent(componentid);
		return dbManagerComponent.deletecomponent(c);
	}

	
	


	
	
}