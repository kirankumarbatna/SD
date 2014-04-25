package edu.asu.spring.mining.domain.impl;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.BasicDBObject;

import edu.asu.spring.mining.domain.IRequirement;

@Document(collection = "requirement")
@Service
public class Requirement  implements IRequirement{

	
	
	@Id
	private String id;
	private String name;
	private MultipartFile file;
	private String description;
	private String keyword;
	private String filename;
	
	
	
	
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public MultipartFile getFile() {
		return file;
	}
	@Override
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	@Override
	public String getDescription() {
		return description;
	}
	@Override
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	

}