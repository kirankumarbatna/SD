package edu.asu.spring.mining.mongo.service;

import java.util.List;

import edu.asu.spring.mining.domain.IComponent;

public interface IDBManagerComponent {

	public static final String COLLECTION_NAME = "component";
	public final static int SUCCESS = 1;
	public final static int FAILURE = 0;

	public abstract int addcomponent(IComponent component) ;

	public abstract List<IComponent> getAllcomponent();

	public abstract int deletecomponent(IComponent user) ;

	IComponent getcomponent(String componentid);



	

}