package edu.asu.spring.mining.converters;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.DBObject;

import edu.asu.spring.mining.HomeController;
import edu.asu.spring.mining.domain.impl.Requirement;

/**
 * 
 * @author : Bhargav Desai
 *
 */
public class RequirementsConverter implements Converter<DBObject, Requirement> {
	
	
	
	@Override
	public Requirement convert(DBObject arg0) {
			
		Requirement r = new Requirement();
		
		r.setDescription((String)arg0.get("description"));
		r.setName((String)arg0.get("name"));
		r.setKeyword((String)arg0.get("keyword"));
		r.setId(((ObjectId)arg0.get("_id")).toString());
		
		return r ;
	}

}
