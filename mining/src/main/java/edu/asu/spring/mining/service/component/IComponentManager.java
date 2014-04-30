package edu.asu.spring.mining.service.component;

import java.util.List;

import edu.asu.spring.mining.domain.IComponent;


public interface IComponentManager {


public abstract int insertComponent(IComponent component) ;
	
	public abstract List<IComponent> getAllComponent();

	public abstract int deleteUser(String componentid);
	
	
}