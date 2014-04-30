package edu.asu.spring.mining.domain.impl;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.asu.spring.mining.domain.IComponent;
import edu.asu.spring.mining.domain.IDomain;

@Document(collection = "component")
@Service
public class Component  implements IComponent{

	
	
	@Id
	private String id;
	private String name;
	private MultipartFile file;
	private String filename;
	private String url;
	
	
	@DBRef
    private List<IDomain> domains;	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public List<IDomain> getDomains() {
		return domains;
	}
	public void setDomains(List<IDomain> domains) {
		this.domains = domains;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	

}