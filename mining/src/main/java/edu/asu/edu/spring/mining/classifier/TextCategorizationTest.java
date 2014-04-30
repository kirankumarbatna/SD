package edu.asu.edu.spring.mining.classifier;
import java.io.File;
import java.util.Properties;

import org.springframework.stereotype.Service;

import edu.asu.spring.mining.web.saasprovidermanagement.AddComponentController;

import weka.classifiers.lazy.IBk;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.TextDirectoryLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;


@Service
public class TextCategorizationTest {

  public String classify() throws Exception {
    // convert the directory into a dataset
    TextDirectoryLoader loader = new TextDirectoryLoader();
    
    Properties p = new Properties();
	p.load(AddComponentController.class.getClassLoader()
			.getResourceAsStream("storage.properties"));

	String parent = (String) p.get("storagepath");
    
    loader.setDirectory(new File(parent+"/classfiles"));
    Instances dataRaw = loader.getDataSet();
    
    StringToWordVector filter = new StringToWordVector();
    filter.setInputFormat(dataRaw);
    Instances dataFiltered = Filter.useFilter(dataRaw, filter);

        IBk classifier = new IBk(3);
    classifier.buildClassifier(dataFiltered);

    for (int i = 0; i < dataFiltered.numInstances(); i++) {
    System.out.println(dataFiltered.classAttribute().value((int) classifier.classifyInstance(dataFiltered.instance(i))));
    }
    
    return dataFiltered.classAttribute().value((int) classifier.classifyInstance(dataFiltered.instance(dataFiltered.numInstances()-1)));
   
    
    
    
    
    
    
    

     }
}