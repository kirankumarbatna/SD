package edu.asu.spring.mining.domain;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

@Document(collection = "component")
public interface IComponent {

	public String getId() ;
	public void setId(String id) ;
	
	public MultipartFile getFile() ;
	public void setFile(MultipartFile file); 
	
	public String getName() ;
	public void setName(String name); 

	public String getFilename() ;
	public void setFilename(String filename) ;
	public List<IDomain> getDomains() ;
	public void setDomains(List<IDomain> domains); 
	

}