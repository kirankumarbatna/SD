package edu.asu.spring.mining.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

@Document(collection = "requirement")
public interface IRequirement {

	public MultipartFile getFile();
	public void setFile(MultipartFile file) ;
	public String getDescription() ;
	public void setDescription(String description) ;
	public String getKeyword() ;
	public void setKeyword(String domain) ;
	public String getName() ;
	public void setName(String name) ;
	public String getId();
	public void setId(String id);
	public String getFilename() ;
	public void setFilename(String filename) ;

}