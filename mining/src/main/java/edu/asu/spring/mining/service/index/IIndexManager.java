package edu.asu.spring.mining.service.index;

import java.io.IOException;


public interface IIndexManager {


	void indexRequirementFiles() throws IOException;

	void indexComponentFiles() throws IOException;
	
	
}