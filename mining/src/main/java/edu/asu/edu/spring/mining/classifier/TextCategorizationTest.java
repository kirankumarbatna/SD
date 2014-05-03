package edu.asu.edu.spring.mining.classifier;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.springframework.stereotype.Service;

import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.core.*;
import weka.core.converters.TextDirectoryLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;
import edu.asu.spring.mining.web.saasprovidermanagement.AddComponentController;


@Service
public class TextCategorizationTest {

  public String classify(String fileName) throws Exception {
    // convert the directory into a dataset
    TextDirectoryLoader loader = new TextDirectoryLoader();
    
    Properties p = new Properties();
	p.load(AddComponentController.class.getClassLoader()
			.getResourceAsStream("storage.properties"));

	String parent = (String) p.get("storagepath");
    
    loader.setDirectory(new File(parent+"/classfiles"));
    Instances dataRaw = loader.getDataSet();
    
    StringToWordVector filter = new StringToWordVector();
    filter.setStopwords(new File("C:\\Users\\Bhargav\\Downloads\\sem3\\SD\\project\\stopwords.txt"));
    filter.setInputFormat(dataRaw);
    Instances dataFiltered = Filter.useFilter(dataRaw, filter);

        IBk classifier = new IBk();
    classifier.buildClassifier(dataFiltered);

    for (int i = 0; i < dataFiltered.numInstances(); i++) {
    System.out.println(dataFiltered.classAttribute().value((int) classifier.classifyInstance(dataFiltered.instance(i))));
    }
    
    String text = "";
    try {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line;
		
		while ((line = reader.readLine()) != null) {
            text = text + " " + line;
        }
		System.out.println("===== Loaded text data: " + fileName + " =====");
		reader.close();
		System.out.println(text);
	}
	catch (IOException e) {
		System.out.println("Problem found when reading: " + fileName);
	}
    
    
////    FastVector fvNominalVal = new FastVector(2);
////	fvNominalVal.addElement("bank");
////	fvNominalVal.addElement("hospital");
////	fvNominalVal.addElement("library");
////	Attribute attribute1 = new Attribute("class", fvNominalVal);
//	Attribute attribute2 = new Attribute("text",(FastVector) null);
//	// Create list of instances with one element
//	FastVector fvWekaAttributes = new FastVector(2);
////	fvWekaAttributes.addElement(attribute1);
//	fvWekaAttributes.addElement(attribute2);
//	Instances instances = new Instances("Test relation", fvWekaAttributes, 1);           
//	// Set class index
//	instances.setClassIndex(0);
//	// Create and add the instance
//	Instance instance = new Instance(2);
//	instance.setValue(attribute2, text);
//	// Another way to do it:
//	// instance.setValue((Attribute)fvWekaAttributes.elementAt(1), text);
//	instances.add(instance);
//		System.out.println("===== Instance created with reference dataset =====");
//	System.out.println(instances);
//    
//	try {
//		double pred = classifier.classifyInstance(instances.instance(0));
//		System.out.println("===== Classified instance =====");
//		System.out.println(pred);
//		System.out.println("Class predicted: " + instances.classAttribute().value((int) pred));
//		return instances.classAttribute().value((int) pred);
//	}
//	catch (Exception e) {
//		e.printStackTrace();
//		System.out.println("Problem found when classifying the text");
//		return null;
//	}		
//	
    
    
   return dataFiltered.classAttribute().value((int) classifier.classifyInstance(dataFiltered.instance(dataFiltered.numInstances()-1)));
   
    
    
    
    
    
    
    

     }
}